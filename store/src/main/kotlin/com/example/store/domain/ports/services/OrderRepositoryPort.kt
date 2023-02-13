package com.example.store.domain.ports.services

import com.example.store.domain.model.Order
import java.util.UUID

interface OrderRepositoryPort {
    fun findById(id: UUID?): Order?
    fun add(order: Order)
    fun update(order: Order)
}