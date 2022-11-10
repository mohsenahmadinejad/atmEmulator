package com.mohsen.bankservice.service;



import com.mohsen.bankservice.dto.AccountDto;
import com.mohsen.bankservice.entity.Account;
import com.mohsen.bankservice.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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


}
