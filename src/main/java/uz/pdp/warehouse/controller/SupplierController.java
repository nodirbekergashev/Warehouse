package uz.pdp.warehouse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.model.dto.supplier.SupplierCreateDto;
import uz.pdp.warehouse.model.dto.supplier.SupplierDto;
import uz.pdp.warehouse.model.dto.supplier.SupplierUpdateDto;
import uz.pdp.warehouse.model.entity.Supplier;
import uz.pdp.warehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/v1/suppliers")
@RequiredArgsConstructor
@Tag(name = "Supplier Controller", description = "APIs for managing supplier information")
public class SupplierController {

    private final SupplierService supplierService;


    @Operation(summary = "Create a new supplier", description = "Register a new supplier with contact details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Supplier created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<SupplierDto> create(@RequestBody SupplierCreateDto createDto) {
        SupplierDto supplier = supplierService.save(createDto);
        return new ResponseEntity<>(supplier, HttpStatus.CREATED); // Use 201 Created
    }

    @Operation(summary = "Get all suppliers", description = "Retrieve a list of all suppliers")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved supplier list")
    @GetMapping
    public ResponseEntity<List<SupplierDto>> getAll() {
        List<SupplierDto> suppliers = supplierService.findAll();
        return ResponseEntity.ok(suppliers);
    }

    @Operation(summary = "Get supplier by ID", description = "Retrieve a specific supplier by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supplier found successfully"),
            @ApiResponse(responseCode = "404", description = "Supplier not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SupplierDto> getById(@PathVariable Long id) {
        Supplier supplier = supplierService.findById(id);
        return ResponseEntity
                .ok(supplierService.getDto(supplier));
    }

    @Operation(summary = "Update supplier details", description = "Modify contact details or other information for an existing supplier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supplier updated successfully"),
            @ApiResponse(responseCode = "404", description = "Supplier not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SupplierDto> update(@PathVariable Long id, @RequestBody SupplierUpdateDto updateDto) {
        SupplierDto supplier = supplierService.update(updateDto, id);
        return ResponseEntity.ok(supplier);
    }

    @Operation(summary = "Delete supplier", description = "Soft delete a supplier from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Supplier deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Supplier not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseEntity.noContent().build(); // Use 204 No Content for successful deletion
    }
}