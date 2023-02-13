package com.example.store.domain.dto.request

import java.util.UUID

data class OrderItemRequestDTO(
    val productID: UUID,
    val price: Double
)