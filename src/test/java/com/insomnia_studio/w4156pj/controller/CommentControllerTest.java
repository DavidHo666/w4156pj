package com.insomnia_studio.w4156pj.controller;

import com.insomnia_studio.w4156pj.entity.ClientEntity;
import com.insomnia_studio.w4156pj.entity.CommentEntity;
import com.insomnia_studio.w4156pj.entity.PostEntity;
import com.insomnia_studio.w4156pj.entity.UserEntity;
import com.insomnia_studio.w4156pj.model.Comment;
import com.insomnia_studio.w4156pj.service.CommentService;
import com.insomnia_studio.w4156pj.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebMvcTest(CommentControllerTest.class)
public class CommentControllerTest {
    @Mock
    private CommentService commentService;


    @InjectMocks
    private CommentController commentController;

    @Test
    void testAddCommentValid() throws Exception{
        //setup
        UUID postId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        ClientEntity client = new ClientEntity(clientId, "testClient");
        UserEntity user = new UserEntity(userId, "testUser", "testUser", client);
        PostEntity post = new PostEntity(postId, user, client);
        Comment comment = new Comment(clientId, userId, postId, 1, 0, "testComment");
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(comment, commentEntity);

        //when
        Mockito.when(commentService.addComment(comment, commentId)).thenReturn(comment);

        //test
        Comment addComment = commentController.addComment(comment, commentId);

        //assert
        Assertions.assertEquals(comment, addComment);
    }

    @Test
    void testAddCommentInvalid() throws Exception{
        //setup
        UUID postId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        ClientEntity client = new ClientEntity(clientId, "testClient");
        UserEntity user = new UserEntity(userId, "testUser", "testUser", client);
        PostEntity post = new PostEntity(postId, user, client);
        Comment comment = new Comment(clientId, userId, postId, 1, 0, "testComment");
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(comment, commentEntity);

        //when
        Mockito.when(commentService.addComment(comment, commentId)).thenReturn(null);

        //test
        Comment addComment = commentController.addComment(comment, commentId);

        //assert
        Assertions.assertEquals(null, addComment);
    }

    @Test
    void testGetCommentValid() throws Exception{
        //setup
        UUID postId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        ClientEntity client = new ClientEntity(clientId, "testClient");
        UserEntity user = new UserEntity(userId, "testUser", "testUser", client);
        PostEntity post = new PostEntity(postId, user, client);
        Comment comment = new Comment(clientId, userId, postId, 1, 0, "testComment");
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(comment, commentEntity);

        //when
        Mockito.when(commentService.getCommentById(commentId, comment)).thenReturn(comment);

        //test
        Comment foundComment = commentController.getCommentByCommentId(commentId, comment);

        //assert
        Assertions.assertEquals(comment, foundComment);
    }

    @Test
    void testGetCommentInvalid() throws Exception{
        //setup
        UUID postId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        ClientEntity client = new ClientEntity(clientId, "testClient");
        UserEntity user = new UserEntity(userId, "testUser", "testUser", client);
        PostEntity post = new PostEntity(postId, user, client);
        Comment comment = new Comment(clientId, userId, postId, 1, 0, "testComment");
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(comment, commentEntity);

        //when
        Mockito.when(commentService.getCommentById(commentId, comment)).thenReturn(null);

        //test
        Comment foundComment = commentController.getCommentByCommentId(commentId, comment);

        //assert
        Assertions.assertEquals(null, foundComment);
    }

    @Test
    void testUpdateCommentValid() throws Exception{
        //setup
        UUID postId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        ClientEntity client = new ClientEntity(clientId, "testClient");
        UserEntity user = new UserEntity(userId, "testUser", "testUser", client);
        PostEntity post = new PostEntity(postId, user, client);
        Comment comment = new Comment(clientId, userId, postId, 1, 0, "testComment");
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(comment, commentEntity);

        //when
        Mockito.when(commentService.updateCommentById(commentId, comment)).thenReturn(comment);

        //test
        Comment updateComment = commentController.updateCommentByCommentId(commentId, comment);

        //assert
        Assertions.assertEquals(comment, updateComment);
    }

    @Test
    void testUpdateCommentInvalid() throws Exception{
        //setup
        UUID postId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        ClientEntity client = new ClientEntity(clientId, "testClient");
        UserEntity user = new UserEntity(userId, "testUser", "testUser", client);
        PostEntity post = new PostEntity(postId, user, client);
        Comment comment = new Comment(clientId, userId, postId, 1, 0, "testComment");
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(comment, commentEntity);

        //when
        Mockito.when(commentService.updateCommentById(commentId, comment)).thenReturn(null);

        //test
        Comment updateComment = commentController.updateCommentByCommentId(commentId, comment);

        //assert
        Assertions.assertEquals(null, updateComment);
    }

    @Test
    void testDeleteCommentValid() throws Exception {
        //setup
        UUID postId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        ClientEntity client = new ClientEntity(clientId, "testClient");
        UserEntity user = new UserEntity(userId, "testUser", "testUser", client);
        PostEntity post = new PostEntity(postId, user, client);
        Comment comment = new Comment(clientId, userId, postId, 1, 0, "testComment");
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(comment, commentEntity);
        Map<String,Boolean> expectResponse = new HashMap<>();
        expectResponse.put("Deleted", Boolean.TRUE);

        //when
        Mockito.when(commentService.deleteCommentById(commentId, comment)).thenReturn(Boolean.TRUE);

        //test
        Map<String,Boolean> deleteResponse = commentController.deleteCommentByCommentId(commentId, comment);

        //assert
        Assertions.assertEquals(expectResponse, deleteResponse);
    }

    @Test
    void testDeleteCommentInvalid() throws Exception {
        //setup
        UUID postId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        ClientEntity client = new ClientEntity(clientId, "testClient");
        UserEntity user = new UserEntity(userId, "testUser", "testUser", client);
        PostEntity post = new PostEntity(postId, user, client);
        Comment comment = new Comment(clientId, userId, postId, 1, 0, "testComment");
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(comment, commentEntity);
        Map<String,Boolean> expectResponse = new HashMap<>();
        expectResponse.put("Deleted", Boolean.FALSE);

        //when
        Mockito.when(commentService.deleteCommentById(commentId, comment)).thenReturn(Boolean.FALSE);

        //test
        Map<String,Boolean> deleteResponse = commentController.deleteCommentByCommentId(commentId, comment);

        //assert
        Assertions.assertEquals(expectResponse, deleteResponse);
    }

}
