package com.example.order_service.service;

import com.example.order_service.client.InventoryClient;
import com.example.order_service.client.ProductClient;
import com.example.order_service.dto.*;
import com.example.order_service.entity.Order;
import com.example.order_service.entity.OrderItem;
import com.example.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repo;
    private final ProductClient productClient;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository repo, ProductClient productClient, InventoryClient inventoryClient) {
        this.repo = repo;
        this.productClient = productClient;
        this.inventoryClient = inventoryClient;
    }

    @Transactional
    public Order placeOrder(PlaceOrderRequest request) {

        double total = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        List<StockItemRequest> stockItems = new ArrayList<>();

        for (OrderItemRequest itemReq : request.getItems()) {


            ProductResponse product = productClient.getProductById(itemReq.getProductId());
            if (product == null) throw new RuntimeException("Product not found: " + itemReq.getProductId());

            total += product.getPrice() * itemReq.getQuantity();

            OrderItem item = new OrderItem();
            item.setProductId(product.getId());
            item.setQuantity(itemReq.getQuantity());
            item.setPriceAtPurchase(product.getPrice());
            orderItems.add(item);

            stockItems.add(new StockItemRequest(product.getId(), itemReq.getQuantity()));
        }
        StockCheckRequest stockReq = new StockCheckRequest(stockItems);
        StockCheckResponse stockRes = inventoryClient.check(stockReq);

        if (stockRes == null || !stockRes.isAllAvailable()) {
            throw new IllegalStateException("Out of stock: " + (stockRes == null ? "" : stockRes.getOutOfStockProductIds()));
        }


        inventoryClient.decrease(stockReq);

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setTotalAmount(total);
        order.setStatus("PLACED");
        order.setCreatedAt(Instant.now());
        order.setItems(orderItems);

        for (OrderItem oi : orderItems) {
            oi.setOrder(order);
        }

        return repo.save(order);
    }

    public List<Order> getOrdersByUser(Long userId) {
        return repo.findByUserId(userId);
    }

}