package com.example.store.domain.mapper

import com.example.store.domain.dto.request.OrderItemRequestDTO
import com.example.store.domain.dto.response.OrderItemResponseDTO
import com.example.store.domain.model.OrderItem

fun OrderItemRequestDTO.toModel() =
    OrderItem(
        productID = productID,
        price = price
    )

fun OrderItem.toRequestDTO() =
    OrderItemRequestDTO(
        productID = productID,
        price = price
    )

fun OrderItem.toResponseDTO() =
    OrderItemResponseDTO(
        productID = productID,
        price = price
    )

fun OrderItemResponseDTO.toModel() =
    OrderItem(
        productID = productID,
        price = price
    )