package uz.pdp.warehouse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Tag(name = "Main Controller", description = "API for main page and application information")
public class MainController {

    @Operation(summary = "Get welcome message", description = "Returns welcome message for Warehouse Management System")
    @ApiResponse(responseCode = "200", description = "Welcome message retrieved successfully")
    @GetMapping
    public String getWelcomeMessage() {
        return "Welcome to Warehouse Management System API";
    }

    @Operation(summary = "Get application info", description = "Returns basic information about the application")
    @ApiResponse(responseCode = "200", description = "Application info retrieved successfully")
    @GetMapping("/info")
    public String getAppInfo() {
        return "Warehouse Management System v1.0 - Developed with Spring Boot";
    }
}