package uz.pdp.warehouse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.model.dto.customer.CustomerCreateDto;
import uz.pdp.warehouse.model.dto.customer.CustomerDto;
import uz.pdp.warehouse.model.dto.customer.CustomerUpdateDto;
import uz.pdp.warehouse.model.entity.Customer;
import uz.pdp.warehouse.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
@Tag(name = "Customer Management", description = "APIs for managing customers in the warehouse system")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(
            summary = "Create new customer",
            description = "Register a new customer in the system. Phone number and email must be unique."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data or phone/email already exists",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<CustomerDto> create(@Parameter(description = "Customer data for creation", required = true)
                                              @RequestBody
                                              CustomerCreateDto createDto) {
        CustomerDto customer = customerService.save(createDto);
        return ResponseEntity.ok(customer);
    }

    @Operation(
            summary = "Get all customers",
            description = "Retrieve a list of all active customers from the system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of customers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class))
            )
    })
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        List<CustomerDto> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @Operation(
            summary = "Get customer by ID",
            description = "Retrieve detailed information about a specific customer by their unique identifier"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Customer not found with the given ID",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(
            @Parameter(description = "ID of the customer to be retrieved", required = true, example = "1")
            @PathVariable Long id) {
        Customer customer = customerService.findById(id);

        return ResponseEntity.ok(customerService.getDto(customer));
    }

    @Operation(
            summary = "Update customer information",
            description = "Modify details of an existing customer using their ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Customer not found with the given ID",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(
            @Parameter(description = "ID of the customer to be updated", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated customer data", required = true)
            @RequestBody CustomerUpdateDto updateDto) {
        CustomerDto customer = customerService.update(updateDto, id);
        return ResponseEntity.ok(customer);
    }

    @Operation(
            summary = "Delete customer",
            description = "Soft delete a customer from the system by their ID. The customer record will be marked as deleted but retained in the database."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Customer deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Customer not found with the given ID",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the customer to be deleted", required = true, example = "1")
            @PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}