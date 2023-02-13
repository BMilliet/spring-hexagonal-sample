package com.example.store.domain.mapper

import com.example.store.domain.dto.request.OrderRequestDTO
import com.example.store.domain.dto.response.OrderResponseDTO
import com.example.store.domain.model.Order

fun OrderRequestDTO.toModel() =
    Order(
        items = items.map { it.toModel() },
        price = price,
        status = status
    )

fun Order.toRequestDTO() =
    OrderRequestDTO(
        items = getOrders().map { it.toRequestDTO() },
        price = getPrice(),
        status = getStatus()
    )

fun Order.toResponseDTO() =
    OrderResponseDTO(
        id = id,
        items = getOrders().map { it.toResponseDTO() },
        price = getPrice(),
        status = getStatus()
    )

fun OrderResponseDTO.toModel() =
    Order(
        id = id,
        items = items.map { it.toModel() },
        price = price,
        status = status
    )