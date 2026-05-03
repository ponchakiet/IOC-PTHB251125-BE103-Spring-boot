package re.edu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.edu.dto.request.OrderItemRequest;
import re.edu.dto.request.OrderRequest;
import re.edu.dto.request.StatusUpdateRequest;
import re.edu.entity.Order;
import re.edu.entity.OrderItem;
import re.edu.entity.Product;
import re.edu.entity.User;
import re.edu.repository.OrderItemRepository;
import re.edu.repository.OrderRepository;
import re.edu.repository.ProductRepository;
import re.edu.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order createOrder(String email, OrderRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + email));

        Order order = Order.builder()
                .user(user)
                .createdDate(LocalDateTime.now())
                .status("PENDING")
                .totalMoney(BigDecimal.ZERO)
                .items(new ArrayList<>())
                .build();

        order = orderRepository.save(order);

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> items = new ArrayList<>();

        for (OrderItemRequest itemReq : request.getItems()) {
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + itemReq.getProductId()));

            BigDecimal priceBuy = product.getPrice();
            OrderItem item = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemReq.getQuantity())
                    .priceBuy(priceBuy)
                    .build();

            items.add(orderItemRepository.save(item));
            total = total.add(priceBuy.multiply(BigDecimal.valueOf(itemReq.getQuantity())));
        }

        order.getItems().addAll(items);
        order.setTotalMoney(total);
        return orderRepository.save(order);
    }

    public List<Order> getMyOrders(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + email));
        return orderRepository.findByUser(user);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order updateStatus(Long orderId, StatusUpdateRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
        order.setStatus(request.getStatus());
        return orderRepository.save(order);
    }
}
