package com.mohsen.bankservice.service;



import com.mohsen.bankservice.dto.AccountDto;
import com.mohsen.bankservice.entity.Account;
import com.mohsen.bankservice.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Long addAccount(AccountDto accountDto) {
        Account account = new Account();
        account=modelMapper.map(accountDto,Account.class);
        log.info("Account number {} added : ",account.getAccountNo());

        return accountRepository.save(account).getId();
    }

    @Override
    public Account cashDeposit(Account account, BigDecimal amount) {
        Account accountToUpdate =accountRepository.findById(account.getId())
                .orElseThrow(()->new RuntimeException("Can not find account "    ));
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        log.info("The Balance of Account number {} updated to {} : ",account.getAccountNo(),account.getBalance());
        return accountRepository.save(account);
    }


    public Account cashWithdrawal(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) < 0){
           throw  new RuntimeException("no enough balance "    );
        }

        Account accountToUpdate =accountRepository.findById(account.getId())
                .orElseThrow(()->new RuntimeException("Can not find account "    ));

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
        log.info("The Balance of Account number {} updated to {} : ",account.getAccountNo(),account.getBalance());
        return accountRepository.save(account);
    }

}
