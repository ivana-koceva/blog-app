package com.app.blog.controller.impl;
import com.app.blog.controller.BlogPostRestApi;
import com.app.blog.model.dto.BlogPostDTO;
import com.app.blog.service.BlogPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequestMapping("/blogs")
@RequiredArgsConstructor
public class BlogPostRestRestController implements BlogPostRestApi {
    private final BlogPostService blogPostService;
    private final Logger logger = LoggerFactory.getLogger(BlogPostRestRestController.class);

    @GetMapping()
    public List<BlogPostDTO> getBlogPosts(@RequestParam (required = false) Integer limit,
                                       @RequestParam (required = false) String tagName,
                                       @RequestParam (required = false) Integer tagNumber) {
        logger.trace("BlogPostController - getBlogPosts");
        return blogPostService.findAllWithSpecifications(limit, tagName, tagNumber);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPostDTO> getBlogPostById(@PathVariable Long id) {
        logger.trace("BlogPostController - getBlogPostById");
        return ResponseEntity.ok().body(this.blogPostService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<BlogPostDTO> addBlogPost(@RequestHeader("Authorization") String token, @Valid @RequestBody BlogPostDTO blogPostDTO) {
        logger.trace("BlogPostController - addBlogPost");
        return ResponseEntity.ok().body(this.blogPostService.create(blogPostDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPostDTO> editBlogPost(@RequestHeader("Authorization") String token, @PathVariable Long id, @Valid @RequestBody BlogPostDTO blogPostDTO) {
        logger.trace("BlogPostController - editBlogPost");
        return ResponseEntity.ok().body(this.blogPostService.update(id, blogPostDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BlogPostDTO> patchBlogPost(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody BlogPostDTO blogPostDTO) {
        logger.trace("BlogPostController - patchBlogPost");
        return ResponseEntity.ok().body(this.blogPostService.patch(id, blogPostDTO));
    }

    @PostMapping("/{id}/tags")
    public ResponseEntity<BlogPostDTO> addTagToBlogPost(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestParam List<String> tags) {
        logger.trace("BlogPostController - addTagToBlogPost");
        return ResponseEntity.ok().body(this.blogPostService.addTagToBlogPost(id, tags));
    }

    @DeleteMapping("/{id}/tags")
    public ResponseEntity<BlogPostDTO> removeTagFromBlogPost(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestParam List<String> tags) {
        logger.trace("BlogPostController - removeTagFromBlogPost");
        return ResponseEntity.ok().body(this.blogPostService.removeTagFromBlogPost(id, tags));
    }

    @DeleteMapping("/{id}")
    public void deleteBlogPost(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        logger.trace("BlogPostController - deleteBlogPost");
        this.blogPostService.deleteById(id);
    }

}
