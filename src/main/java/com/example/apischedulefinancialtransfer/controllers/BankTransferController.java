package com.example.apischedulefinancialtransfer.controllers;

import com.example.apischedulefinancialtransfer.dtos.BankTransferDTO;
import com.example.apischedulefinancialtransfer.models.BankTransferModel;
import com.example.apischedulefinancialtransfer.models.UserModel;
import com.example.apischedulefinancialtransfer.services.BankTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("bank-transfer")
public class BankTransferController {
    private final BankTransferService bankTransferService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public BankTransferController(BankTransferService bankTransferService) {
        this.bankTransferService = bankTransferService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveBankTransfer(@RequestBody @Valid BankTransferDTO bankTransferDTO){
        return bankTransferService.saveBankTransfer(bankTransferDTO);
    }

    @GetMapping("")
    public ResponseEntity<List<BankTransferModel>> getAllBankTransfers(){
        return bankTransferService.getAllBankTransfers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBankTransfer(@PathVariable(value="id") Integer id){
        return bankTransferService.getOneBankTransfer(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBankTransfer(@PathVariable(value="id") Integer id, @RequestBody @Valid BankTransferDTO bankTransferDTO){
        return bankTransferService.updateBankTransfer(id, bankTransferDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBankTransfer(@PathVariable(value="id") Integer id){
        return bankTransferService.deleteBankTransfer(id);
    }
}
