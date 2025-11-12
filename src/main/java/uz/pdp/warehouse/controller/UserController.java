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
import uz.pdp.warehouse.model.dto.customer.CustomerDto;
import uz.pdp.warehouse.model.dto.customer.CustomerUpdateDto;
import uz.pdp.warehouse.model.dto.user.UserDto;
import uz.pdp.warehouse.model.dto.user.UserUpdateDto;
import uz.pdp.warehouse.model.entity.Customer;
import uz.pdp.warehouse.model.entity.User;
import uz.pdp.warehouse.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "APIs for managing user information")
public class UserController {

    private final UserService service;

    @Operation(
            summary = "Get all users for super admin",
            description = "Retrieve a list of all active users from the system to Super Admin"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully retrieved list of customers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))
            )
    })
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> allUsers = service.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @Operation(
            summary = "Get User by ID",
            description = "Retrieve information about a specific user by their unique identifier"
    )

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found or nonactive"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(
            @Parameter(description = "ID of the user to be retrieved", required = true, example = "13")
            @PathVariable Long id) {
        User user = service.findById(id);
        return ResponseEntity.ok(service.getDto(user));
    }


    @Operation(
            summary = "Update customer information",
            description = "Modify details of an existing customer using their ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "USer updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found with the given ID",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(
            @Parameter(description = "ID of the user to be updated", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated customer data", required = true)
            @RequestBody UserUpdateDto updateDto) {
        return ResponseEntity.ok(service.update(updateDto, id));
    }


    @Operation(
            summary = "Delete user",
            description = "Soft delete a user from the system by their ID. The user record will be marked as deleted but retained in the database."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "user deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "user not found with the given ID",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the user to be deleted", required = true, example = "1")
            @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
