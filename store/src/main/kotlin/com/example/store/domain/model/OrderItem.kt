package com.example.store.domain.model

import java.util.UUID

data class OrderItem(
    val productID: UUID,
    val price: Double
)