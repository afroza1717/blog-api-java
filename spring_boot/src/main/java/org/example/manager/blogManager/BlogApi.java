package org.example.manager.blogManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.model.db.Blog;
import org.example.util.HelperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class BlogApi {
    @Autowired
    BlogRepository blogRepository;
    private static final Logger logger = LoggerFactory.getLogger(BlogApi.class);

    @GetMapping(path = "/blog_list/")
    @ResponseBody
    public List<Blog> getAll() {
        Timestamp startTime = HelperUtils.nowTimestamp();
        Timestamp endTime = HelperUtils.nowTimestamp();
        logger.info("Getting all records takes: " + Long.valueOf(endTime.getTime() - startTime.getTime()).toString()
                + " milli seconds. ");

        List<Blog> blogList = blogRepository.findAll();

        return blogList;
    }

    @GetMapping(path = "/blog_list/{blogId}/")
    @ResponseBody
    public Optional<Blog> getSingleBlog(@PathVariable(value = "blogId") Long blogId) {
        Timestamp startTime = HelperUtils.nowTimestamp();
        Timestamp endTime = HelperUtils.nowTimestamp();
        logger.info("Getting all records takes getAllParent: " + Long.valueOf(endTime.getTime() - startTime.getTime()).toString()
                + " milli seconds. ");

        logger.info("blog id:{} ", blogId);

        return blogRepository.findById(blogId);
    }

    @GetMapping(path = "/compelete_update/{blogId}/")
    @ResponseBody
    public Optional<Blog> getBlog(@PathVariable(value = "blogId") Long blogId) {
        logger.info("blogId {}", blogId);
        return blogRepository.findById(blogId);
    }


    @PostMapping(value = "/create_blog", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody Blog createBlogs(@RequestParam String title,
                                         @RequestParam String author,
                                         @RequestParam String paragraph,
                                         @RequestParam String is_publish,
                                         @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {
        // Create a new Blog object and set the fields
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setAuthor(author);
        blog.setParagraph(paragraph);
        blog.setIs_publish(is_publish);

        // Handle file as before
        if (file != null && !file.isEmpty()) {
            blog.setImage(file.getBytes());
        }

        Blog savedBlog = blogRepository.save(blog);
        return savedBlog;
    }

    @PutMapping(value = "/partial_update/{blogId}/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody Blog updateBlog(@PathVariable(value = "blogId") Long blogId,
                                         @RequestParam String title,
                                         @RequestParam String author,
                                         @RequestParam String paragraph,
                                         @RequestParam String is_publish,
                                         @RequestParam(value = "image", required = false) MultipartFile file) {
        Timestamp startTime = HelperUtils.nowTimestamp();
        Map<String, Object> responseMap = new HashMap<>();
        ArrayList<Object> error = new ArrayList<Object>();
        ObjectMapper mapper = new ObjectMapper();

        Optional<Blog> blogToEdit = blogRepository.findById(blogId);
        try {

            if (blogToEdit.isPresent()) {
                Blog updatedBlog = blogToEdit.get();
                updatedBlog.setTitle(title);
                updatedBlog.setAuthor(author);
                updatedBlog.setParagraph(paragraph);
                updatedBlog.setIs_publish(is_publish);
                if (Objects.nonNull(file)) {
                    updatedBlog.setImage(file.getBytes());
                }

                updatedBlog = blogRepository.save(updatedBlog);
                logger.info("blog updated!");

                return updatedBlog;
            }
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }

        return null;
    }

    @DeleteMapping(value = "/delete/{blogId}/")
    @ResponseBody
    public String deleteItem(@PathVariable(value = "blogId") Long blogId) {
        Timestamp startTime = HelperUtils.nowTimestamp();
        blogRepository.deleteById(blogId);
        Timestamp endTime = HelperUtils.nowTimestamp();
        logger.info("Deleting data takes: " + Long.valueOf(endTime.getTime() - startTime.getTime()).toString()
                + " milli seconds. ");

        return HelperUtils.SUCCESS_MESSAGE;
    }


}






