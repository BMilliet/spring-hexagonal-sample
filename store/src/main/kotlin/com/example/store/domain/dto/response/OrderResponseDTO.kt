package com.example.store.domain.dto.response

import com.example.store.domain.model.OrderStatus
import java.util.UUID

data class OrderResponseDTO(
    val id: UUID,
    val items: List<OrderItemResponseDTO>,
    val price: Double,
    val status: OrderStatus
)