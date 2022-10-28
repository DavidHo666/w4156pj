package com.insomnia_studio.w4156pj.service;

import com.insomnia_studio.w4156pj.entity.PostEntity;
import com.insomnia_studio.w4156pj.entity.UserEntity;
import com.insomnia_studio.w4156pj.model.Post;
import com.insomnia_studio.w4156pj.repository.ClientRepository;
import com.insomnia_studio.w4156pj.repository.PostEntityRepository;
import com.insomnia_studio.w4156pj.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostEntityRepository postEntityRepository;
    private ClientRepository clientRepository;

    private UserEntityRepository userEntityRepository;

    @Override
    public Post addPost(Post post) throws Exception {
        try {
            // TO BE FIXED: should return error message if client is not valid
            if (post.getClientId() != null && clientRepository.existsByClientId(post.getClientId())) {
                PostEntity postEntity = new PostEntity();
                BeanUtils.copyProperties(post, postEntity);
                UserEntity userEntity = userEntityRepository.findByUserId(post.getUserId());
                postEntity.setUser(userEntity);
                postEntity = postEntityRepository.save(postEntity);
                post.setPostId(postEntity.getPostId());
                post.setPostCreatedTime(postEntity.getPostCreatedTime());
                post.setPostUpdatedTime(postEntity.getPostUpdatedTime());
            }
        } catch (Exception e) {
            throw new Exception("Could not save Post: " + e);
        }
        return post;
    }

    @Override
    public Post getPostById(UUID postId) {
        PostEntity postEntity = postEntityRepository.findByPostId(postId);
        Post post = new Post();
        BeanUtils.copyProperties(postEntity, post);
        return post;
    }

    @Override
    public Post updatePostById(UUID postId, Post post) throws Exception {
        try {
            PostEntity postEntity = postEntityRepository.findByPostId(postId);
            postEntity.setTitle(post.getTitle());
            postEntity.setContent(post.getContent());
            postEntity.setTags(post.getTags());
            postEntity = postEntityRepository.save(postEntity);
            BeanUtils.copyProperties(postEntity, post);
        } catch (Exception e) {
            throw new Exception("Could not update Post: " + e);
        }
        return post;
    }

    @Override
    public Boolean deletePostById(UUID postId) {
        Boolean is_deleted = (postEntityRepository.deletePostEntityByPostId(postId) == 1);
        return is_deleted;
    }


}
