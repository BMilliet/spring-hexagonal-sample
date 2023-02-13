package com.example.store.domain.services

import com.example.store.domain.adapters.OrderServiceAdapter
import com.example.store.domain.dto.request.ProductRequestDTO
import com.example.store.domain.mapper.toModel
import com.example.store.domain.model.OrderStatus
import com.example.store.domain.ports.services.OrderRepositoryPort
import com.example.store.utils.spy.OrderRepositorySpy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertEquals

class OrderServiceAdapterTests {

    private lateinit var orderRepositoryPort: OrderRepositoryPort
    private lateinit var orderServiceAdapter: OrderServiceAdapter

    @BeforeEach
    fun setup() {
        orderRepositoryPort = OrderRepositorySpy()
        orderServiceAdapter = OrderServiceAdapter(orderRepositoryPort)
    }

    @Test
    fun `service operations`() {
        val product = ProductRequestDTO(name = "a", price = 250.0)
        val order = orderServiceAdapter.createOrder(product).toModel()
        val id = order.id

        assertNotNull(order)
        assertNotNull(orderServiceAdapter.getById(id))

        val product1 = orderServiceAdapter.add(id, ProductRequestDTO(name = "b", price = 1500.0))
        assertEquals(orderServiceAdapter.getById(id).price, 1750.0)

        orderServiceAdapter.add(id, ProductRequestDTO(name = "c", price = 50.0))
        assertEquals(orderServiceAdapter.getById(id).price, 1800.0)

        orderServiceAdapter.remove(id, product1.id)
        assertEquals(orderServiceAdapter.getById(id).price, 300.0)

        assertEquals(orderServiceAdapter.getById(id).status, OrderStatus.OPEN)
        orderServiceAdapter.complete(id)
        assertEquals(orderServiceAdapter.getById(id).status, OrderStatus.COMPLETED)
    }
}