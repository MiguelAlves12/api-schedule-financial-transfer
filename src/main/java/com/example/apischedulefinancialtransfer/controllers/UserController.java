package com.example.apischedulefinancialtransfer.controllers;

import com.example.apischedulefinancialtransfer.dtos.UserDTO;
import com.example.apischedulefinancialtransfer.models.UserModel;
import com.example.apischedulefinancialtransfer.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getOneUsers(@PathVariable(value="id") Integer id){
        return userService.getOneUser(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value="id") Integer id, @RequestBody @Valid UserDTO userDTO){
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value="id") Integer id){
        return userService.deleteUser(id);
    }

}
