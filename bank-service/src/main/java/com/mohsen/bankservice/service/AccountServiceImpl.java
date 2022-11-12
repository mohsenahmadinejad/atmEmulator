package com.mohsen.bankservice.service;



import com.mohsen.bankservice.model.entity.Account;
import com.mohsen.bankservice.model.entity.AccountTransaction;
import com.mohsen.bankservice.exception.NotEnoughBalanceException;
import com.mohsen.bankservice.repository.AccountRepository;
import com.mohsen.bankservice.repository.AccountTransactionRepository;
import com.mohsen.common.dto.AccountDto;
import com.mohsen.common.dto.TransactionType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;


@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Override
    public Long addAccount(AccountDto accountDto) {
        Account account = new Account();
        account=modelMapper.map(accountDto,Account.class);
        log.info("Account number {} added : ",account.getAccountNo());

        return accountRepository.save(account).getId();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Account cashDeposit(Account account, BigDecimal amount) {

        account.setBalance(account.getBalance().add(amount));
        AccountTransaction accountTransaction=AccountTransaction.builder().
                transactionType(TransactionType.DEPOSIT).
                amount(amount).
                balance(account.getBalance()).
                account(account).
                build();

        accountTransactionRepository.save(accountTransaction);
        log.info("The Balance of Account number {} updated to {} : ",account.getAccountNo(),account.getBalance());
        return accountRepository.save(account);
    }

    @Override
    public Account cashWithdrawal(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) < 0){
           throw  new NotEnoughBalanceException("Not enough balance "    );
        }

        account.setBalance(account.getBalance().subtract(amount));

        AccountTransaction accountTransaction=AccountTransaction.builder().
                transactionType(TransactionType.WITHDRAWAL).
                amount(amount).
                balance(account.getBalance()).
                account(account).
                build();

        accountTransactionRepository.save(accountTransaction);
        accountRepository.save(account);
        log.info("The Balance of Account number {} updated to {} : ",account.getAccountNo(),account.getBalance());
        return accountRepository.save(account);
    }



}
