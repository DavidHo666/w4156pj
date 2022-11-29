package com.insomnia_studio.w4156pj.service;

import com.insomnia_studio.w4156pj.entity.ClientEntity;
import com.insomnia_studio.w4156pj.entity.UserEntity;
import com.insomnia_studio.w4156pj.model.User;
import com.insomnia_studio.w4156pj.repository.ClientEntityRepository;
import com.insomnia_studio.w4156pj.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private UserEntityRepository userEntityRepository;
  private ClientEntityRepository clientEntityRepository;

  @Override
  public User addUser(User user) throws ResponseStatusException {
    if (user.getClientId() != null && clientEntityRepository.existsByClientId(user.getClientId())) {
      UserEntity userEntity = new UserEntity();
      BeanUtils.copyProperties(user, userEntity);
      ClientEntity clientEntity = clientEntityRepository.findByClientId(user.getClientId());
      userEntity.setClient(clientEntity);
      userEntity = userEntityRepository.save(userEntity);
      user.setUserCreatedTime(userEntity.getUserCreatedTime());
      user.setUserId(userEntity.getUserId());
      return user;
    } else {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid Client ID");
    }
  }

  @Override
  public User getUser(UUID userId, User user) throws ResponseStatusException {
    UserEntity userEntity = userEntityRepository.findByUserId(userId);
    if (userEntity != null) {
      if (userEntity.getClient().getClientId().compareTo(user.getClientId()) != 0) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid Client ID");
      }
      User responseUser = new User();
      BeanUtils.copyProperties(userEntity, responseUser);
      responseUser.setClientId(userEntity.getClient().getClientId());
      return responseUser;
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ID not found");
    }
  }

  @Override
  public User updateUserById(UUID userId, User user) throws ResponseStatusException {
    //need_TODO: add client authentication or other client distinguishing method
    UserEntity userEntity = userEntityRepository.findByUserId(userId);
    if (userEntity != null) {
      if (userEntity.getClient().getClientId().compareTo(user.getClientId()) != 0) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid Client ID");
      }
      userEntity.setFirstName(user.getFirstName());
      userEntity.setLastName(user.getLastName());
      userEntity = userEntityRepository.save(userEntity);
      BeanUtils.copyProperties(userEntity, user);
      return user;
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ID not found");
    }
  }

  @Override
  @Transactional
  public Boolean deleteUserById(UUID userId, User user) throws ResponseStatusException {
    UserEntity userEntity = userEntityRepository.findByUserId(userId);
    if (userEntity != null) {
      if (userEntity.getClient().getClientId().compareTo(user.getClientId()) != 0) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid Client ID");
      }
      if (userEntity.getPosts().size() > 0 || userEntity.getComments().size() > 0) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "The user has post or comment, can't be deleted.");
      }
      Boolean is_deleted = (userEntityRepository.deleteUserEntityByUserId(userId) == 1);
      return is_deleted;
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ID not found");
    }
  }
}
