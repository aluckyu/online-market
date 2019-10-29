package com.thoughtworks.dddttt.onlinemarket.order.domain

import com.thoughtworks.dddttt.onlinemarket.order.domain.exception.SubtotalExceededLimitationException
import spock.lang.Specification

class OrderItemTest extends Specification {

    def "we have a limited order item price when create order item"() {
        given: "we have a product which price is greater than the order item subtotal price limitation"
          def superExpensiveProduct = new Product("A product", SubtotalLimitation.LIMITATION.add(new BigDecimal(10.00)))

        when: "we create an order item use this expensive product"
          new OrderItem(superExpensiveProduct, 1)

        then: "there will be an SubtotalExceededLimitation error"
          SubtotalExceededLimitationException error = thrown()
          error != null
    }

    def "we have a limited order item price when update order item"() {
        given: "we have a product which price is greater than the order item subtotal price limitation"
          def superExpensiveProduct = new Product("A product", SubtotalLimitation.LIMITATION)

        and: "we create an order item use this expensive product"
          OrderItem orderItem = new OrderItem(superExpensiveProduct, 1)

          orderItem != null

        when: "we increase the product numbers"
          orderItem.increaseProductNumber(1)

        then: "there will be an SubtotalExceededLimitation error"
          SubtotalExceededLimitationException error = thrown()
          error != null
    }


}
