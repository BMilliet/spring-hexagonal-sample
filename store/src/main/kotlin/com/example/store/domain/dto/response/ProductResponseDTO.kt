package com.example.store.domain.dto.response

import java.util.UUID

data class ProductResponseDTO(
    val id: UUID,
    val name: String,
    val price: Double
)