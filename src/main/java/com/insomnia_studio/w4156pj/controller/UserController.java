package com.insomnia_studio.w4156pj.controller;

import com.insomnia_studio.w4156pj.model.User;
import com.insomnia_studio.w4156pj.service.UserService;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
  private UserService userService;

  @PostMapping
  public User addUser(@RequestBody User user) throws Exception {
    return userService.addUser(user);
  }

  @GetMapping("/{userId}")
  public User getUser(@PathVariable UUID userId, @RequestBody User user) throws Exception {
    return userService.getUser(userId, user);
  }

  @PutMapping("/{userId}")
  public User updateUser(@PathVariable UUID userId, @RequestBody User user) throws Exception {
    return userService.updateUserById(userId, user);
  }

  @DeleteMapping("/{userId}")
  public Map<String, Boolean> deleteUser(@PathVariable UUID userId,
                                         @RequestBody User user) throws Exception {
    Map<String, Boolean> response = new HashMap<>();
    response.put("Deleted: ", userService.deleteUserById(userId, user));
    return response;
  }

}
