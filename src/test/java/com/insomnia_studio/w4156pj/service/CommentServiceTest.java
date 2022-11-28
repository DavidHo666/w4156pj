package com.insomnia_studio.w4156pj.service;

import com.insomnia_studio.w4156pj.entity.ClientEntity;
import com.insomnia_studio.w4156pj.entity.CommentEntity;
import com.insomnia_studio.w4156pj.entity.PostEntity;
import com.insomnia_studio.w4156pj.entity.UserEntity;
import com.insomnia_studio.w4156pj.model.Comment;
import com.insomnia_studio.w4156pj.repository.ClientEntityRepository;
import com.insomnia_studio.w4156pj.repository.CommentEntityRepository;
import com.insomnia_studio.w4156pj.repository.PostEntityRepository;
import com.insomnia_studio.w4156pj.repository.UserEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentServiceTest {
    // Class to be tested
    @Autowired
    private CommentServiceImpl commentService;

    // Dependencies
    private CommentEntityRepository commentEntityRepository;
    private PostEntityRepository postEntityRepository;
    private UserEntityRepository userEntityRepository;
    private ClientEntityRepository clientEntityRepository;

    @BeforeEach
    void setUp() {
        commentEntityRepository = Mockito.mock(CommentEntityRepository.class);
        postEntityRepository = Mockito.mock(PostEntityRepository.class);
        userEntityRepository = Mockito.mock(UserEntityRepository.class);
        clientEntityRepository = Mockito.mock(ClientEntityRepository.class);

        commentService.setCommentRepository(commentEntityRepository);
        commentService.setPostEntityRepository(postEntityRepository);
        commentService.setClientRepository(clientEntityRepository);
        commentService.setUserEntityRepository(userEntityRepository);
    }

    @Test
    void testCreateCommentValidPost() {
        //PostEntity post = new PostEntity();
        //post.setTitle("a");
        //post.setPostId(UUID.randomUUID());
        //UserEntity user = new UserEntity();
       //user.setUserId("123");
        //ClientEntity client = new ClientEntity(UUID.randomUUID(), "c");
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

        Mockito.when(postEntityRepository.findByPostId(postId)).thenReturn(post);
        Mockito.when(userEntityRepository.findByUserId(userId)).thenReturn(user);
        Mockito.when(clientEntityRepository.findByClientId(clientId)).thenReturn(client);
        Mockito.when(commentEntityRepository.save(commentEntity)).thenReturn(commentEntity);

        Comment addedComment = commentService.addComment(comment, postId);
        Assertions.assertEquals(comment, addedComment);
    }


    @Test
    void testCreateCommentInvalidPost() {
        //PostEntity post = new PostEntity();
        //post.setTitle("a");
        //post.setPostId(UUID.randomUUID());
        //UserEntity user = new UserEntity();
        //user.setUserId("123");
        //ClientEntity client = new ClientEntity(UUID.randomUUID(), "c");
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

        Mockito.when(postEntityRepository.findByPostId(postId)).thenReturn(null);
        Mockito.when(userEntityRepository.findByUserId(userId)).thenReturn(user);
        Mockito.when(clientEntityRepository.findByClientId(clientId)).thenReturn(client);
        Mockito.when(commentEntityRepository.save(commentEntity)).thenReturn(commentEntity);

        try {
            Comment addedComment = commentService.addComment(comment, postId);
        } catch (ResponseStatusException e) {
            Assertions.assertEquals(e.getStatus(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    void testCreateCommentInvalidClient() {
        //PostEntity post = new PostEntity();
        //post.setTitle("a");
        //post.setPostId(UUID.randomUUID());
        //UserEntity user = new UserEntity();
        //user.setUserId("123");
        //ClientEntity client = new ClientEntity(UUID.randomUUID(), "c");
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

        Mockito.when(postEntityRepository.findByPostId(postId)).thenReturn(post);
        Mockito.when(userEntityRepository.findByUserId(userId)).thenReturn(user);
        Mockito.when(clientEntityRepository.findByClientId(clientId)).thenReturn(null);
        Mockito.when(commentEntityRepository.save(commentEntity)).thenReturn(commentEntity);

        try {
            Comment addedComment = commentService.addComment(comment, postId);
        } catch (ResponseStatusException e) {
            Assertions.assertEquals(e.getStatus(), HttpStatus.FORBIDDEN);
        }
    }


    @Test
    void testGetCommentByIdValidId() {
        //PostEntity post = new PostEntity();
        //post.setTitle("a");
        //post.setPostId(UUID.randomUUID());
        //UserEntity user = new UserEntity();
        //user.setUserId("123");
        UUID postId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        ClientEntity client = new ClientEntity(clientId, "testClient");
        UserEntity user = new UserEntity(userId, "testUser", "testUser", client);
        PostEntity post = new PostEntity(postId, user, client);
        Comment comment = new Comment(clientId, userId, postId, 1, 0, "testComment");
        comment.setCommentId(commentId);
        CommentEntity commentEntity = new CommentEntity(commentId, user, client, post);
        commentEntity.setLikesNum(1);
        commentEntity.setDislikesNum(0);
        commentEntity.setContent("testComment");

        Mockito.when(commentEntityRepository.findByCommentId(commentId)).thenReturn(commentEntity);

        Comment foundComment = commentService.getCommentById(commentId, comment);
        Assertions.assertEquals(comment, foundComment);
    }


    @Test
    void testGetCommentByIdInvalidId() {
        //PostEntity post = new PostEntity();
        //post.setTitle("a");
        //post.setPostId(UUID.randomUUID());
        //UserEntity user = new UserEntity();
        //user.setUserId("123");
        UUID postId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        ClientEntity client = new ClientEntity(clientId, "testClient");
        UserEntity user = new UserEntity(userId, "testUser", "testUser", client);
        PostEntity post = new PostEntity(postId, user, client);
        Comment comment = new Comment(clientId, userId, postId, 1, 0, "testComment");
        comment.setCommentId(commentId);
        CommentEntity commentEntity = new CommentEntity(commentId, user, client, post);
        commentEntity.setLikesNum(1);
        commentEntity.setDislikesNum(0);
        commentEntity.setContent("testComment");

        Mockito.when(commentEntityRepository.findByCommentId(commentId)).thenReturn(null);

        try {
            Comment foundComment = commentService.getCommentById(commentId, comment);
        } catch (ResponseStatusException e) {
            Assertions.assertEquals(e.getStatus(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    void testGetCommentByIdInvalidClient() {
        //PostEntity post = new PostEntity();
        //post.setTitle("a");
        //post.setPostId(UUID.randomUUID());
        //UserEntity user = new UserEntity();
        //user.setUserId("123");
        UUID postId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        UUID fakeClientId = UUID.randomUUID();
        ClientEntity client = new ClientEntity(clientId, "testClient");
        ClientEntity fakeClient = new ClientEntity(fakeClientId, "fakeClient");
        UserEntity user = new UserEntity(userId, "testUser", "testUser", client);
        PostEntity post = new PostEntity(postId, user, client);
        Comment comment = new Comment(clientId, userId, postId, 1, 0, "testComment");
        comment.setCommentId(commentId);
        CommentEntity commentEntity = new CommentEntity(commentId, user, fakeClient, post);
        commentEntity.setLikesNum(1);
        commentEntity.setDislikesNum(0);
        commentEntity.setContent("testComment");

        Mockito.when(commentEntityRepository.findByCommentId(commentId)).thenReturn(commentEntity);

        try {
            Comment foundComment = commentService.getCommentById(commentId, comment);
        } catch (ResponseStatusException e) {
            Assertions.assertEquals(e.getStatus(), HttpStatus.FORBIDDEN);
        }
    }

}
