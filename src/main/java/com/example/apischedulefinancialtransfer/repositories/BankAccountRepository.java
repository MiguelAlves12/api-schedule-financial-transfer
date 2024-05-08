package com.example.apischedulefinancialtransfer.repositories;

import com.example.apischedulefinancialtransfer.models.BankAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountModel, Integer>  {
}
