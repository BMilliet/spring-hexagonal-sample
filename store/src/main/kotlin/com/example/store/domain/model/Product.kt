package com.example.store.domain.model

import java.util.UUID

data class Product(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val price: Double
)