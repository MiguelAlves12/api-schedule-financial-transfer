package com.example.apischedulefinancialtransfer.controllers;

import com.example.apischedulefinancialtransfer.dtos.AuthenticationDTO;
import com.example.apischedulefinancialtransfer.dtos.TokenResponseDTO;
import com.example.apischedulefinancialtransfer.models.UserModel;
import com.example.apischedulefinancialtransfer.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
       UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(), authenticationDTO.getPassword());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new TokenResponseDTO(token));
    }
}
