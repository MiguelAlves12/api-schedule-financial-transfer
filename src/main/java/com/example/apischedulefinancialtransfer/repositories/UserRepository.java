package com.example.apischedulefinancialtransfer.repositories;

import com.example.apischedulefinancialtransfer.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserDetails findByEmail(String email);
}
