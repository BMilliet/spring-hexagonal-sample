package com.example.store.domain.dto.request

import com.example.store.domain.model.OrderStatus

data class OrderRequestDTO(
    val items: List<OrderItemRequestDTO>,
    val price: Double,
    val status: OrderStatus
)
