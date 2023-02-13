package com.example.store.utils.spy

import com.example.store.domain.model.Order
import com.example.store.domain.ports.services.OrderRepositoryPort
import java.util.*

class OrderRepositorySpy: OrderRepositoryPort {

    private var orders = listOf<Order>()

    override fun findById(id: UUID?): Order? =
        orders.firstOrNull { it.id == id }

    override fun add(order: Order) {
        orders = orders.plus(order)
    }

    override fun update(order: Order) {
        findById(order.id)?.let {
            orders = orders.minus(it)
            add(order)
        }
    }
}