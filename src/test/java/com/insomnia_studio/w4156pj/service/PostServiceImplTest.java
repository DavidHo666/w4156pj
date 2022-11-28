package com.insomnia_studio.w4156pj.service;


import com.insomnia_studio.w4156pj.entity.ClientEntity;
import com.insomnia_studio.w4156pj.entity.PostEntity;
import com.insomnia_studio.w4156pj.entity.UserEntity;
import com.insomnia_studio.w4156pj.model.Post;
import com.insomnia_studio.w4156pj.repository.ClientEntityRepository;
import com.insomnia_studio.w4156pj.repository.PostEntityRepository;

import com.insomnia_studio.w4156pj.repository.UserEntityRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;



@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {
    @Mock
    private PostEntityRepository postRepository;
    @Mock
    private ClientEntityRepository clientEntityRepository;
    @Mock
    private UserEntityRepository userRepository;

    @InjectMocks
    private PostServiceImpl postserviceimpl;


    private ClientEntity client;
    private UserEntity user;
    private Post post;

    private Post postclient;
    private PostEntity postEntity;
    private Post expectedpost;

    private Post newpost;
    private PostEntity newPostEntity;
    private Post updatePost;

    @BeforeEach
    public void setup(){
        //setup client
        client = new ClientEntity();
        client.setClientId(UUID.randomUUID());
        //setup user
        user = new UserEntity();
        user.setUserId(UUID.randomUUID());
        user.setClient(client);
        //setup post
        post = new Post();
        post.setClientId(client.getClientId());
        post.setUserId(user.getUserId());
        post.setTitle("title");
        post.setContent("content");
        post.setTags(new HashSet<>(Arrays.asList("tag")));
        post.setUserId(user.getUserId());
        //setup post_get
        postclient = new Post();
        postclient.setClientId(client.getClientId());
        //setup postEntity
        postEntity = new PostEntity();
        BeanUtils.copyProperties(post,postEntity);
        postEntity.setUser(user);
        postEntity.setClient(client);
        postEntity.setPostId(UUID.randomUUID());
        postEntity.setPostCreatedTime(new Date());
        postEntity.setPostUpdatedTime(new Date());
        //setup expected post to add
        expectedpost = new Post();
        BeanUtils.copyProperties(postEntity,expectedpost);
        expectedpost.setUserId(postEntity.getUser().getUserId());
        expectedpost.setClientId(postEntity.getClient().getClientId());

        //set update
        newpost = new Post();
        newpost.setPostId(postEntity.getPostId());
        newpost.setTags(new HashSet<>(Arrays.asList("newtag")));
        newpost.setTitle("newtitle");
        newpost.setContent("newcontent");
        newPostEntity = new PostEntity();
        BeanUtils.copyProperties(postEntity,newPostEntity);
        newPostEntity.setTags(newpost.getTags());
        newPostEntity.setTitle(newpost.getTitle());
        newPostEntity.setContent(newpost.getContent());
        updatePost = new Post();
        BeanUtils.copyProperties(newPostEntity,updatePost);
        updatePost.setUserId(newPostEntity.getUser().getUserId());
        updatePost.setClientId(newPostEntity.getClient().getClientId());


    }



    //JUnit Test for addPostsuccess
    @Test
    public void testAddPostsuccess() throws Exception {
        // when
        when(userRepository.findByUserId(post.getUserId())).thenReturn(user);
        when(clientEntityRepository.existsByClientId(post.getClientId())).thenReturn(true);
        when(postRepository.save(Mockito.any(PostEntity.class))).thenReturn(postEntity);
        //test
        Post addedpost = postserviceimpl.addPost(post);
        //assertion
        assertEquals(expectedpost,addedpost);
    }


    //JUnit Test for addPostInvalidClient
    @Test
    public void testAddPostInvalidClient() throws Exception {

        //ResponseStatusException e = new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid Client ID");
        // when
        try {
            when(clientEntityRepository.existsByClientId(post.getClientId())).thenReturn(false);
        }
        catch (ResponseStatusException e){
            assertEquals(e.getStatus(), HttpStatus.FORBIDDEN);
        }
        //when(clientEntityRepository.existsByClientId(post.getClientId())).thenReturn(false);
        try{
            when(userRepository.findByUserId(post.getUserId())).thenReturn(user);
        }
        catch (ResponseStatusException e){
            assertEquals(e.getStatus(), HttpStatus.NOT_FOUND);
        }
        //when(userRepository.findByUserId(post.getUserId())).thenReturn(user);
        when(postRepository.save(Mockito.any(PostEntity.class))).thenReturn(postEntity);
        postserviceimpl.addPost(post);
        //assertion

        //Exception exception = assertThrows(Exception.class, () ->
                //postserviceimpl.addPost(post));

        //assertEquals(e);

    }



    //JUnit Test for getPostByIdsuccess
    @Test
    public void testgetPostByIdsuccess() throws Exception {
        // when
        when(postRepository.findByPostId(post.getPostId())).thenReturn(postEntity);
        //test
        Post foundpost = postserviceimpl.getPostById(post.getPostId(), postclient);
        //assertion
        assertEquals(expectedpost,foundpost);
    }
/*
    //JUnit Test for getPostByIdNotFound
    @Test
    public void testgetPostByIdNotFound() throws Exception {
        NullPointerException e = new NullPointerException();
        // when
        when(postRepository.findByPostId(post.getPostId())).thenThrow(e);

        //assertion
        Exception exception = assertThrows(Exception.class, () ->
                postserviceimpl.getPostById(post.getPostId()));

        assertEquals("Could not find postId: " + e, exception.getMessage());

    }

    //JUnit Test for updatePostByIdsuccess
    @Test
    public void testUpdatePostByIdsuccess() throws Exception {

        // when
        when(postRepository.findByPostId(Mockito.any(UUID.class))).thenReturn(postEntity);
        when(postRepository.save(Mockito.any(PostEntity.class))).thenReturn(newPostEntity);
        //test
        Post updatedpost = postserviceimpl.updatePostById(newpost.getPostId(),newpost);

        //assertion
        assertEquals(updatePost,updatedpost);

    }

    //JUnit Test for updatePostByIdFailed
    @Test
    public void testUpdatePostByIdFailed() throws Exception {

        NullPointerException e = new NullPointerException();
        // when
        when(postRepository.findByPostId(newpost.getPostId())).thenThrow(e);

        //assertion
        Exception exception = assertThrows(Exception.class, () ->
                postserviceimpl.updatePostById(newpost.getPostId(),newpost));

        assertEquals("Could not update Post: " + e, exception.getMessage());

    }

    //JUnit Test for deletePostById
    @Test
    public void testDeletePostById(){

        // when true
        when(postRepository.deletePostEntityByPostId(post.getPostId())).thenReturn(1);

        Boolean deletetrue = postserviceimpl.deletePostById(post.getPostId(), post);
        assertTrue(deletetrue);
        // when false
        when(postRepository.deletePostEntityByPostId(post.getPostId())).thenReturn(0);

        Boolean deletefalse = postserviceimpl.deletePostById(post.getPostId(), post);
        assertFalse(deletefalse);
    }

     */

}

