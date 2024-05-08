package com.example.apischedulefinancialtransfer.services;

import com.example.apischedulefinancialtransfer.dtos.BankAccountDTO;
import com.example.apischedulefinancialtransfer.models.BankAccountModel;
import com.example.apischedulefinancialtransfer.models.UserModel;
import com.example.apischedulefinancialtransfer.repositories.BankAccountRepository;
import com.example.apischedulefinancialtransfer.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<BankAccountModel>> getAllBankAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountRepository.findAll());
    }

    public ResponseEntity<Object> saveBankAccount(BankAccountDTO bankAccountDTO) {
        Optional<UserModel> user = userRepository.findById(bankAccountDTO.getUserId());
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found.");
        }
        BankAccountModel bankAccountModel = new BankAccountModel();
        BeanUtils.copyProperties(bankAccountDTO, bankAccountModel);
        bankAccountModel.setUserModel(user.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccountRepository.save(bankAccountModel));
    }

    public ResponseEntity<Object> getOneBankAccount(Integer id){
        Optional<BankAccountModel> bankAccount = bankAccountRepository.findById(id);
        if(bankAccount.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("bank account not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(bankAccount.get());
    }

    public ResponseEntity<Object> updateBankAccount(Integer id, BankAccountDTO bankAccountDTO){
        Optional<BankAccountModel> bankAccount = bankAccountRepository.findById(id);
        if(bankAccount.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("bank account not found.");
        }
        BankAccountModel bankAccountModel = bankAccount.get();
        BeanUtils.copyProperties(bankAccountDTO, bankAccountModel);
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountRepository.save(bankAccountModel));
    }

    public ResponseEntity<Object> deleteBankAccount(Integer id){
        Optional<BankAccountModel> bankAccount = bankAccountRepository.findById(id);
        if(bankAccount.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("bank account not found.");
        }
        bankAccountRepository.delete(bankAccount.get());
        return ResponseEntity.status(HttpStatus.OK).body("bank account deleted successfully.");
    }
}
