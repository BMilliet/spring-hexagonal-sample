package com.example.store.domain.model

import com.example.store.domain.exception.InvalidOrderOperation
import java.util.UUID

class Order(
    val id: UUID = UUID.randomUUID(),
    private var items: List<OrderItem> = emptyList(),
    private var price: Double = 0.0,
    private var status: OrderStatus = OrderStatus.OPEN
) {

    fun setStatus(status: OrderStatus) {
        validate()
        if (items.isEmpty())
            this.status = OrderStatus.CANCELLED
        else
            this.status = status
    }

    fun add(product: Product): Product {
        validate()

        val item = OrderItem(product.id, product.price)
        items = items.plus(item)
        price = price.plus(item.price)
        return product
    }

    fun remove(id: UUID) {
        validate()

        findOrderItemById(id)?.let {
            items = items.minus(it)
            price = price.minus(it.price)
        }
    }

    fun getPrice() = price
    fun getStatus() = status
    fun getOrders() = items

    private fun validate() {
        if (status != OrderStatus.OPEN) { throw InvalidOrderOperation() }
    }

    private fun findOrderItemById(id: UUID): OrderItem? =
        items.firstOrNull { it.productID == id }
}