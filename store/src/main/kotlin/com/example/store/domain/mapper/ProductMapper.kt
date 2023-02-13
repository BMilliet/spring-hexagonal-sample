package com.example.store.domain.mapper

import com.example.store.domain.dto.request.ProductRequestDTO
import com.example.store.domain.dto.response.ProductResponseDTO
import com.example.store.domain.model.Product

fun Product.toRequestDTO() =
    ProductRequestDTO(
        name = name,
        price = price
    )

fun ProductRequestDTO.toModel() =
    Product(
        name = name,
        price = price
    )

fun Product.toResponseDTO() =
    ProductResponseDTO(
        id = id,
        name = name,
        price = price
    )