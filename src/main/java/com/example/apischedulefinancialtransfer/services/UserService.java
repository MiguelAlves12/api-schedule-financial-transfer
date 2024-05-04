package com.example.apischedulefinancialtransfer.services;

import com.example.apischedulefinancialtransfer.dtos.UserDTO;
import com.example.apischedulefinancialtransfer.models.UserModel;
import com.example.apischedulefinancialtransfer.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    public ResponseEntity<UserModel> saveUser(UserDTO userDTO) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
    }

    public ResponseEntity<Object> getOneUser(Integer id){
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    public ResponseEntity<Object> updateUser(Integer id, UserDTO userDTO){
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found.");
        }
        UserModel userModel = user.get();
        BeanUtils.copyProperties(userDTO, userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userModel));
    }

    public ResponseEntity<Object> deleteUser(Integer id){
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found.");
        }
        userRepository.delete(user.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }
}
