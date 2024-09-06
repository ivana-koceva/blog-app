package com.app.blog.controller;

import com.app.blog.model.dto.BlogPostDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Tag(name = "Blog Posts", description = "All The Blog Post Endpoints")
public interface BlogPostRestApi {

    @Operation(
            summary = "Get all blog posts",
            description = "Gets all blog post DTOs and their data from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    List<BlogPostDTO> getBlogPosts(Integer limit, String tagName,Integer tagNumber);

    @Operation(
            summary = "Get blog post by id",
            description = "Gets one blog post DTO and its data from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    ResponseEntity<BlogPostDTO> getBlogPostById(@PathVariable Long id);

    @Operation(
            summary = "Add a blog post",
            description = "Adds a blog post to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    ResponseEntity<BlogPostDTO> addBlogPost(@RequestHeader("Authorization") String token, @Valid @RequestBody BlogPostDTO blogPostDTO);

    @Operation(
            summary = "Edit a blog post",
            description = "Edits a blog posts data and saves it to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    ResponseEntity<BlogPostDTO> editBlogPost(@RequestHeader("Authorization") String token, @PathVariable Long id, @Valid @RequestBody BlogPostDTO blogPostDTO);

    @Operation(
            summary = "Partially edit a blog post",
            description = "Partially edits a blog posts data and saves it to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    ResponseEntity<BlogPostDTO> patchBlogPost(@RequestHeader("Authorization") String token, @PathVariable Long id, BlogPostDTO blogPostDTO);

    @Operation(
            summary = "Add tags to a blog post",
            description = "Adds an array of tags to a blog post and saves it to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    ResponseEntity<BlogPostDTO> addTagToBlogPost(@RequestHeader("Authorization") String token, @PathVariable Long id, List<String> tags);

    @Operation(
            summary = "Remove tags from a blog post",
            description = "Removes an array of tags from a blog post and saves it to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    ResponseEntity<BlogPostDTO> removeTagFromBlogPost(@RequestHeader("Authorization") String token, @PathVariable Long id, List<String> tags);

    @Operation(
            summary = "Delete a blog post",
            description = "Deletes a blog post from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    void deleteBlogPost(@RequestHeader("Authorization") String token, @PathVariable Long id);
}
