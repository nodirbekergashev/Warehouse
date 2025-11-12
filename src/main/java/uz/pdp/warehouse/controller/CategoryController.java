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
import uz.pdp.warehouse.model.dto.category.CategoryActionDto;
import uz.pdp.warehouse.model.dto.category.CategoryDto;
import uz.pdp.warehouse.model.entity.Category;
import uz.pdp.warehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Category Management", description = "APIs for managing product categories in the warehouse system")
public class CategoryController {

    private final CategoryService service;

    @Operation(
            summary = "Get all categories",
            description = "Retrieve a list of all active product categories from the warehouse system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of categories",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = service.findAll();
        return ResponseEntity.ok(categories);
    }

    @Operation(
            summary = "Get category by ID",
            description = "Retrieve detailed information about a specific category by its unique identifier"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Category found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found with the given ID",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(
            @Parameter(description = "ID of the category to be retrieved", required = true, example = "1")
            @PathVariable Long id) {
        Category category = service.findById(id);
        return ResponseEntity.ok(service.getDto(category));
    }

    @Operation(
            summary = "Create a new category",
            description = "Add a new product category to the warehouse system. Category name must be unique."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Category created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data or category name already exists",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @Parameter(description = "Category data for creation", required = true)
            @RequestBody CategoryActionDto dto) {
        CategoryDto savedCategory = service.save(dto);
        return ResponseEntity.ok(savedCategory);
    }

    @Operation(
            summary = "Update existing category",
            description = "Modify details of an existing category using its ID. Category name must remain unique."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Category updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found with the given ID",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @Parameter(description = "ID of the category to be updated", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated category data", required = true)
            @RequestBody CategoryActionDto dto) {
        CategoryDto updatedCategory = service.update(dto, id);
        return ResponseEntity.ok(updatedCategory);
    }

    @Operation(
            summary = "Delete category",
            description = "Soft delete an existing category from the system by its ID. The category will be marked as deleted but retained in the database."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Category deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found with the given ID",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(
            @Parameter(description = "ID of the category to be deleted", required = true, example = "1")
            @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Category deleted successfully");
    }
}