package re.edu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.request.OrderRequest;
import re.edu.dto.request.StatusUpdateRequest;
import re.edu.entity.Order;
import re.edu.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Order> createOrder(@AuthenticationPrincipal UserDetails userDetails,
                                             @RequestBody OrderRequest request) {
        Order order = orderService.createOrder(userDetails.getUsername(), request);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<Order>> getMyOrders(@AuthenticationPrincipal UserDetails userDetails) {
        List<Order> orders = orderService.getMyOrders(userDetails.getUsername());
        return ResponseEntity.ok(orders);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Order> updateStatus(@PathVariable Long id,
                                              @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(orderService.updateStatus(id, request));
    }
}
