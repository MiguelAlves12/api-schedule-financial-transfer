package com.example.apischedulefinancialtransfer.services;

import com.example.apischedulefinancialtransfer.dtos.BankTransferDTO;
import com.example.apischedulefinancialtransfer.models.BankTransferModel;
import com.example.apischedulefinancialtransfer.models.UserModel;
import com.example.apischedulefinancialtransfer.repositories.BankTransferRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BankTransferService {
    private final BankTransferRepository bankTransferRepository;

    @Autowired
    public BankTransferService(BankTransferRepository bankTransferRepository) {
        this.bankTransferRepository = bankTransferRepository;
    }

    public ResponseEntity<List<BankTransferModel>> getAllBankTransfers() {
        UserModel user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(bankTransferRepository.findAllBySourceBankAccount(user.getBankAccount().getBankAccountDefault()));
    }

    public ResponseEntity<Object> saveBankTransfer(BankTransferDTO bankTransferDTO) {
        UserModel user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String fee = calculateTransferFee(bankTransferDTO.getTransferValue(), bankTransferDTO.getTransferDate());
        if(fee == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is not transfer fee for the chosen date.");
        }
        BankTransferModel bankTransferModel = new BankTransferModel();
        BeanUtils.copyProperties(bankTransferDTO, bankTransferModel);
        bankTransferModel.setTransferFee(Double.valueOf(fee));
        bankTransferModel.setSourceBankAccount(user.getBankAccount().getBankAccountDefault());
        return ResponseEntity.status(HttpStatus.CREATED).body(bankTransferRepository.save(bankTransferModel));
    }

    public ResponseEntity<Object> getOneBankTransfer(Integer id){
        Optional<BankTransferModel> bankTransfer = bankTransferRepository.findById(id);
        if(bankTransfer.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("bank transfer not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(bankTransfer.get());
    }

    public ResponseEntity<Object> updateBankTransfer(Integer id, BankTransferDTO bankTransferDTO){
        Optional<BankTransferModel> bankTransfer = bankTransferRepository.findById(id);
        if(bankTransfer.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("bank transfer not found.");
        }
        BankTransferModel bankTransferModel = bankTransfer.get();
        BeanUtils.copyProperties(bankTransferDTO, bankTransferModel);
        return ResponseEntity.status(HttpStatus.OK).body(bankTransferRepository.save(bankTransferModel));
    }

    public ResponseEntity<Object> deleteBankTransfer(Integer id){
        Optional<BankTransferModel> bankTransfer = bankTransferRepository.findById(id);
        if(bankTransfer.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("bank transfer not found.");
        }
        bankTransferRepository.delete(bankTransfer.get());
        return ResponseEntity.status(HttpStatus.OK).body("bank transfer deleted successfully.");
    }

    private String calculateTransferFee(Double value, LocalDate transferDate){
        LocalDate now = LocalDate.now();
        Long intervalDays = now.until(transferDate, ChronoUnit.DAYS);
        DecimalFormat df = new DecimalFormat("#.##");
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(decimalFormatSymbols);
        if(intervalDays == 0){
            return df.format((value * 0.025) + 3);
        } else if(intervalDays <= 10){
            return "12.00";
        }else if(intervalDays <= 20){
            return df.format((value * 0.082));
        }else if(intervalDays <= 30){
            return df.format((value * 0.069));
        }else if(intervalDays <= 40){
            return df.format((value * 0.047));
        }else if(intervalDays <= 50){
            return df.format((value * 0.017));
        }
        return null;
    }
}
