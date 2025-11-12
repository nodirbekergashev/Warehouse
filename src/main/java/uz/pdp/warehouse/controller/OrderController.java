package uz.pdp.warehouse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uz.pdp.warehouse.exceptions.exception.InsufficientStockException;
import uz.pdp.warehouse.model.dto.order.OrderCreateDto;
import uz.pdp.warehouse.model.dto.order.OrderDto;
import uz.pdp.warehouse.model.dto.order.OrderUpdateDto;
import uz.pdp.warehouse.model.entity.Order;
import uz.pdp.warehouse.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Order Controller", description = "APIs for managing customer orders and order items")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Create a new order", description = "Submit a new order with customer details and a list of items. Performs stock checks.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data or insufficient stock"),
            @ApiResponse(responseCode = "404", description = "Customer or product not found")
    })
    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderCreateDto createDto) {
        try {
            OrderDto order = orderService.save(createDto);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (InsufficientStockException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Get all orders", description = "Retrieve a list of all orders")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved order list")
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        List<OrderDto> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @Operation(summary = "Get order by ID", description = "Retrieve a specific order by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return ResponseEntity
                .ok(orderService.getDto(order));
    }

    @Operation(summary = "Update order status/customer", description = "Modify an existing order's status or associated customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated successfully"),
            @ApiResponse(responseCode = "404", description = "Order or Customer not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> update(@PathVariable Long id, @RequestBody OrderUpdateDto updateDto) {
        OrderDto order = orderService.update(updateDto, id);
        return ResponseEntity.ok(order);
    }

    @Operation(summary = "Delete order", description = "Soft delete an order from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order deleted successfully (No Content)"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}