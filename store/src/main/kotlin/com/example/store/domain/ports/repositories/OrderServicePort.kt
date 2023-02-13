package com.example.store.domain.ports.repositories

import com.example.store.domain.dto.request.ProductRequestDTO
import com.example.store.domain.dto.response.OrderResponseDTO
import com.example.store.domain.dto.response.ProductResponseDTO
import com.example.store.domain.model.OrderStatus
import java.util.UUID

interface OrderServicePort {
    fun createOrder(product: ProductRequestDTO): OrderResponseDTO
    fun remove(order: UUID, product: UUID)
    fun add(order: UUID, product: ProductRequestDTO): ProductResponseDTO
    fun getById(id: UUID?): OrderResponseDTO
    fun complete(order: UUID)
    fun cancel(order: UUID)
    fun totalPrice(id: UUID): Double
    fun currentStatus(id: UUID): OrderStatus
}