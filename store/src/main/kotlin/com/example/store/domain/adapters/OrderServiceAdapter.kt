package com.example.store.domain.adapters

import com.example.store.domain.dto.request.ProductRequestDTO
import com.example.store.domain.dto.response.OrderResponseDTO
import com.example.store.domain.dto.response.ProductResponseDTO
import com.example.store.domain.exception.OrderNotFound
import com.example.store.domain.mapper.toModel
import com.example.store.domain.mapper.toResponseDTO
import com.example.store.domain.model.Order
import com.example.store.domain.model.OrderStatus
import com.example.store.domain.ports.repositories.OrderServicePort
import com.example.store.domain.ports.services.OrderRepositoryPort
import java.util.UUID

class OrderServiceAdapter(private val orderRepositoryPort: OrderRepositoryPort): OrderServicePort {

    override fun createOrder(product: ProductRequestDTO): OrderResponseDTO {
        val order = Order()
        order.add(product.toModel())
        orderRepositoryPort.add(order)
        return getById(order.id)
    }

    override fun remove(order: UUID, product: UUID) {
        val target = getById(order).toModel()
        target.remove(product)
        orderRepositoryPort.update(target)
    }

    override fun add(order: UUID, product: ProductRequestDTO): ProductResponseDTO {
        val target = getById(order).toModel()
        val addedProduct = target.add(product.toModel())
        orderRepositoryPort.update(target)
        return addedProduct.toResponseDTO()
    }

    override fun getById(id: UUID?): OrderResponseDTO =
        orderRepositoryPort.findById(id)?.toResponseDTO() ?: throw OrderNotFound()

    override fun complete(order: UUID) =
        setState(order, OrderStatus.COMPLETED)


    override fun cancel(order: UUID) =
        setState(order, OrderStatus.CANCELLED)

    override fun totalPrice(id: UUID): Double =
        getById(id).toModel().getPrice()

    override fun currentStatus(id: UUID): OrderStatus =
        getById(id).toModel().getStatus()

    private fun setState(order: UUID, status: OrderStatus) {
        orderRepositoryPort.findById(order)?.let {
            it.setStatus(status)
            orderRepositoryPort.update(it)
        }
    }
}