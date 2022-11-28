package com.insomnia_studio.w4156pj.controller;

import com.insomnia_studio.w4156pj.controller.PostController;
import com.insomnia_studio.w4156pj.model.Post;
import com.insomnia_studio.w4156pj.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.*;
import static org.mockito.Mockito.when;


@WebMvcTest(PostControllerTest.class)

public class PostControllerTest {

    @Mock
    private PostService postService;


    @InjectMocks
    private PostController postController;



    @DisplayName("Test for AddPost Method")
    @Test
    public void testAddPost() throws Exception {

        UUID postId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Post post = new Post(postId, clientId, userId, "testPost", "testPost");
        when(postService.addPost(post)).thenReturn(post);
        Post addedpost = postController.addPost(post);
        assertEquals(post,addedpost);
    }



    @DisplayName("Test for getpostbyid Method non-null return")
    @Test
    public void testGetPostByPostIdTrue() throws Exception {
        //setup
        UUID postId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Post post = new Post(postId, clientId, userId, "testPost", "testPost");

        //when
        when(postService.getPostById(postId, post)).thenReturn(post);

        //test
        Post foundPost = postController.getPostByPostId(postId, post);

        //assert
        assertEquals(post, foundPost);
    }



    @DisplayName("Test for getpostbyid Method null return")
    @Test
    public void testgetPostByPostNull() throws Exception{
        //setup
        UUID postId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Post post = new Post(postId, clientId, userId, "testPost", "testPost");

        //when
        when(postService.getPostById(postId, post)).thenReturn(null);

        //test
        Post foundPost = postController.getPostByPostId(postId, post);

        //assert
        assertEquals(null, foundPost);

    }


    @DisplayName("Test for updatepostbyid Method non-null return")
    @Test
    public void testUpdatePostByPostIdTrue() throws Exception {
        //setup
        UUID postId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Post post = new Post(postId, clientId, userId, "testPost", "testPost");

        //when
        when(postService.updatePostById(postId,post)).thenReturn(post);

        //test
        Post updatePost = postController.updatePostByPostId(postId, post);

        //assert
        assertEquals(post, updatePost);
    }


    @DisplayName("Test for updatepostbyid Method null return")
    @Test
    public void testUpdatePostByPostNull() throws Exception {
        //setup
        UUID postId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Post post = new Post(postId, clientId, userId, "testPost", "testPost");

        //when
        when(postService.updatePostById(postId,post)).thenReturn(null);

        //test
        Post updatePost = postController.updatePostByPostId(postId, post);

        //assert
        assertEquals(null, updatePost);
    }


    @DisplayName("Test for deletePostByPostId method")
    @Test
    public void testDeletePostByPostId() throws Exception{
        //setup
        UUID postId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Post post = new Post(postId, clientId, userId, "testPost", "testPost");
        Map<String,Boolean> expectResponse = new HashMap<>();
        expectResponse.put("Deleted", Boolean.TRUE);

        //when
        when(postService.deletePostById(postId, post)).thenReturn(Boolean.TRUE);

        //test
        Map<String,Boolean> deleteResponse = postController.deletePostByPostId(postId, post);

        //assert
        assertEquals(expectResponse, deleteResponse);
    }

    @DisplayName("Test for deletePostByPostId method fail to delete")
    @Test
    public void testDeletePostByPostIdFalse() throws Exception{
        //setup
        UUID postId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Post post = new Post(postId, clientId, userId, "testPost", "testPost");
        Map<String,Boolean> expectResponse = new HashMap<>();
        expectResponse.put("Deleted", Boolean.FALSE);

        //when
        when(postService.deletePostById(postId, post)).thenReturn(Boolean.FALSE);

        //test
        Map<String,Boolean> deleteResponse = postController.deletePostByPostId(postId, post);

        //assert
        assertEquals(expectResponse, deleteResponse);
    }



}
