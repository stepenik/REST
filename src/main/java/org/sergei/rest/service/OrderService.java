package org.sergei.rest.service;

import org.sergei.rest.dao.OrderDAO;
import org.sergei.rest.exceptions.RecordNotFoundException;
import org.sergei.rest.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    // Get all orders
    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    // Get order by number
    public List<Order> getOrderByNumber(Long orderNumber) {
        if (!orderDAO.existsByNumber(orderNumber)) {
            throw new RecordNotFoundException("No order with this ID found");
        }

        return orderDAO.findByNumber(orderNumber);
    }

    // Get order by customer and order numbers
    public List<Order> getOrderByCustomerAndOrderNumbers(Long customerNumber, Long orderNumber) {
        if (!orderDAO.existsByCustomerNumber(customerNumber)) {
            throw new RecordNotFoundException("No customer with this number found");
        } else if (!orderDAO.existsByNumber(orderNumber)) {
            throw new RecordNotFoundException("No order with this number found");
        }

        return orderDAO.findByCustomerAndOrderNumbers(customerNumber, orderNumber);
    }

    // Get all orders by customer number
    public List<Order> getAllOrdersByCustomerNumber(Long customerNumber) {
        if (!orderDAO.existsByCustomerNumber(customerNumber)) {
            throw new RecordNotFoundException("No orders for this customer found");
        }

        return orderDAO.findAllByCustomerNumber(customerNumber);
    }

    // Get all orders by product code
    public List<Order> getAllByProductCode(String productCode) {
        if (!orderDAO.existsByProductCode(productCode)) {
            throw new RecordNotFoundException("No order with this productCode code found");
        }

        return orderDAO.findAllByProductCode(productCode);
    }

    // Save order
    public void saveOrder(Long customerNumber, Order order) {
        order.setCustomerNumber(customerNumber);
        orderDAO.saveOrder(order);
    }

    // Update order by customer and order numbers
    public Order updateOrder(Long customerNumber, Long orderNumber, Order order) {
        if (!orderDAO.existsByCustomerNumber(customerNumber)) {
            throw new RecordNotFoundException("No customer with this ID found");
        } else if (!orderDAO.existsByNumber(orderNumber)) {
            throw new RecordNotFoundException("No order with this ID found");
        }
        order.setOrderNumber(orderNumber);
        order.setCustomerNumber(customerNumber);
//        orderDAO.updateRecord(customerNumber, orderNumber, order);
        return order;
    }

    /*public Order deleteOrderById(Long id) {
        Order order = orderDAO.findByNumber(id);
        if (!orderDAO.existsByNumber(id)) {
            throw new RecordNotFoundException("No order with this ID found");
        }
        order.setOrderNumber(id);
        orderDAO.delete(order);

        return order;
    }

    public Order deleteOrderByCustomerIdAndOrderId(Long customerId, Long orderId) {
        Order order = orderDAO.findByCustomerAndOrderNumbers(customerId, orderId);
        if (!orderDAO.existsByCustomerNumber(customerId)) {
            throw new RecordNotFoundException("No customer with this ID found");
        } else if (!orderDAO.existsByNumber(orderId)) {
            throw new RecordNotFoundException("No order with this ID found");
        }

        order.setOrderNumber(orderId);
        order.setCustomerNumber(customerId);
        orderDAO.delete(order);

        return order;
    }*/
}
