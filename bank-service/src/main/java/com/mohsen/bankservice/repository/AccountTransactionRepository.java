package com.mohsen.bankservice.repository;



import com.mohsen.bankservice.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,Long> {


}