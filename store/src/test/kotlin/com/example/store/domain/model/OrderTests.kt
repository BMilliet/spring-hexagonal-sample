package com.example.store.domain.model

import com.example.store.domain.exception.InvalidOrderOperation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OrderTests {

    @Test
    fun `create order with items`() {
        val product1 = Product(name = "a", price = 200.0)
        val product2 = Product(name = "b", price = 20.0)

        val order = Order()
        order.add(product1)

        assertEquals(order.getPrice(), 200.0)
        assertEquals(order.getStatus(), OrderStatus.OPEN)

        order.add(product2)
        assertEquals(order.getPrice(), 220.0)
        assertEquals(order.getStatus(), OrderStatus.OPEN)

        order.remove(product1.id)
        assertEquals(order.getPrice(), 20.0)
        assertEquals(order.getStatus(), OrderStatus.OPEN)

        order.setStatus(OrderStatus.COMPLETED)
        assertEquals(order.getStatus(), OrderStatus.COMPLETED)

        assertThrows<InvalidOrderOperation> { order.setStatus(OrderStatus.OPEN) }
        assertThrows<InvalidOrderOperation> { order.setStatus(OrderStatus.CANCELLED) }
        assertThrows<InvalidOrderOperation> { order.setStatus(OrderStatus.COMPLETED) }
        assertThrows<InvalidOrderOperation> { order.add(product1) }
        assertThrows<InvalidOrderOperation> { order.remove(product2.id) }
    }
}