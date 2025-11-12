package uz.pdp.warehouse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.model.dto.inventory.InventoryCreateDto;
import uz.pdp.warehouse.model.dto.inventory.InventoryDto;
import uz.pdp.warehouse.model.dto.inventory.InventoryUpdateDto;
import uz.pdp.warehouse.model.entity.Inventory;
import uz.pdp.warehouse.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/v1/inventories")
@RequiredArgsConstructor
@Tag(name = "Inventory Controller", description = "APIs for managing inventory stock levels")
public class InventoryController {

    private final InventoryService inventoryService;

    @Operation(summary = "Create new inventory record", description = "Add stock for a product in a specific warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Product or warehouse not found")
    })
    @PostMapping
    public ResponseEntity<InventoryDto> create(@RequestBody InventoryCreateDto createDto) {
        InventoryDto inventory = inventoryService.save(createDto);
        return ResponseEntity.ok(inventory);
    }

    @Operation(summary = "Get all inventory records", description = "Retrieve list of all inventory records")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved inventory list")
    @GetMapping
    public ResponseEntity<List<InventoryDto>> getAll() {
        List<InventoryDto> inventories = inventoryService.findAll();
        return ResponseEntity.ok(inventories);
    }

    @Operation(summary = "Get inventory by ID", description = "Retrieve specific inventory record by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory found successfully"),
            @ApiResponse(responseCode = "404", description = "Inventory not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<InventoryDto> getById(@PathVariable Long id) {
        Inventory inventory = inventoryService.findById(id);
        return ResponseEntity
                .ok(inventoryService.getDto(inventory));
    }

    @Operation(summary = "Update inventory record", description = "Modify stock quantity or warehouse for existing inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory updated successfully"),
            @ApiResponse(responseCode = "404", description = "Inventory or warehouse not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> update(@PathVariable Long id, @RequestBody InventoryUpdateDto updateDto) {
        InventoryDto inventory = inventoryService.update(updateDto, id);
        return ResponseEntity.ok(inventory);
    }

    @Operation(summary = "Delete inventory record", description = "Soft delete an inventory record from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inventory deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Inventory not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}