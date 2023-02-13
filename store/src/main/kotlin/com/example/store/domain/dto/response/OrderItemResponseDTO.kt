package com.example.store.domain.dto.response

import java.util.UUID

data class OrderItemResponseDTO(
    val productID: UUID,
    val price: Double
)