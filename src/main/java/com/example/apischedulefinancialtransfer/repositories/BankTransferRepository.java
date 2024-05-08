package com.example.apischedulefinancialtransfer.repositories;

import com.example.apischedulefinancialtransfer.models.BankTransferModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankTransferRepository extends JpaRepository<BankTransferModel, Integer> {
    List<BankTransferModel> findAllBySourceBankAccount(String sourceBankAccount);
}
