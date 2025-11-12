package uz.pdp.warehouse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.model.dto.product.ProductCreateDto;
import uz.pdp.warehouse.model.dto.product.ProductDto;
import uz.pdp.warehouse.model.dto.product.ProductUpdateDto;
import uz.pdp.warehouse.model.entity.Product;
import uz.pdp.warehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "APIs for managing product definitions and details")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Create a new product", description = "Register a new product with its details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductCreateDto createDto) {
        ProductDto product = productService.save(createDto);
        return new ResponseEntity<>(product, HttpStatus.CREATED); // Use 201 Created
    }

    @Operation(summary = "Get all products", description = "Retrieve a list of all products")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved product list")
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Get product by ID", description = "Retrieve a specific product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity
                .ok(productService.getDto(product));
    }

    @Operation(summary = "Update product details", description = "Modify details for an existing product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductUpdateDto updateDto) {
        ProductDto product = productService.update(updateDto, id);
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "Delete product", description = "Soft delete a product from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build(); // Use 204 No Content for successful deletion
    }
}