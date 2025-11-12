package uz.pdp.warehouse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.model.dto.warehouse.WarehouseCreateDto;
import uz.pdp.warehouse.model.dto.warehouse.WarehouseDto;
import uz.pdp.warehouse.model.dto.warehouse.WarehouseUpdateDto;
import uz.pdp.warehouse.model.entity.Warehouse;
import uz.pdp.warehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/v1/warehouses")
@RequiredArgsConstructor
@Tag(name = "Warehouse Controller", description = "APIs for managing warehouse locations")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Operation(summary = "Create a new warehouse", description = "Register a new storage location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Warehouse created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<WarehouseDto> create(@RequestBody WarehouseCreateDto createDto) {
        WarehouseDto warehouse = warehouseService.save(createDto);
        return new ResponseEntity<>(warehouse, HttpStatus.CREATED); // Use 201 Created
    }

    @Operation(summary = "Get all warehouses", description = "Retrieve a list of all active warehouses")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved warehouse list")
    @GetMapping
    public ResponseEntity<List<WarehouseDto>> getAll() {
        List<WarehouseDto> warehouses = warehouseService.findAll();
        return ResponseEntity.ok(warehouses);
    }

    @Operation(summary = "Get warehouse by ID", description = "Retrieve a specific warehouse by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Warehouse found successfully"),
            @ApiResponse(responseCode = "404", description = "Warehouse not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<WarehouseDto> getById(@PathVariable Long id) {
        Warehouse warehouse = warehouseService.findById(id);
        return ResponseEntity
                .ok(warehouseService.getDto(warehouse));
    }

    @Operation(summary = "Update warehouse details", description = "Modify details for an existing warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Warehouse updated successfully"),
            @ApiResponse(responseCode = "404", description = "Warehouse not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<WarehouseDto> update(@PathVariable Long id, @RequestBody WarehouseUpdateDto updateDto) {
        WarehouseDto warehouse = warehouseService.update(updateDto, id);
        return ResponseEntity.ok(warehouse);
    }

    @Operation(summary = "Delete warehouse", description = "Soft delete a warehouse from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Warehouse deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Warehouse not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        warehouseService.delete(id);
        return ResponseEntity.noContent().build(); // Use 204 No Content for successful deletion
    }
}