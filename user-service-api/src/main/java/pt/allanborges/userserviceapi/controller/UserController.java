package pt.allanborges.userserviceapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import models.exceptions.StandardError;
import models.requests.CreateUserRequest;
import models.requests.UpdateUserRequest;
import models.responses.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "UserController", description = "Controller responsible for User Operations")
@RequestMapping("api/users")
public interface UserController {

    @Operation(summary = "Find User by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Found"),
            @ApiResponse(
                    responseCode = "404", description = "User not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(
            @Parameter(description = "User id", required = true, example = "659978f4a99ccf2287de3161")
            @PathVariable(name = "id") final String id
    );

    @Operation(summary = "Save New User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Created"),
            @ApiResponse(
                    responseCode = "409 ", description = "Data Integrity Violation",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))
            )
    })
    @PostMapping
    ResponseEntity<Void> save(@Valid @RequestBody final CreateUserRequest createUserRequest);

    @Operation(summary = "Find All Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users Found",
                content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = UserResponse.class))
                )),
            @ApiResponse(
                    responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class)
            ))
    })
    @GetMapping
    ResponseEntity<List<UserResponse>> findAll();

    @Operation(summary = "Update User")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User Updated",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "User not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))
            )
    })
    @PutMapping("/{id}")
    ResponseEntity<UserResponse> update(
            @Parameter(description = "User id", required = true, example = "659978f4a99ccf2287de3161")
            @PathVariable(name = "id") final String id,
            @Valid @RequestBody final UpdateUserRequest updateUserRequest
    );

}