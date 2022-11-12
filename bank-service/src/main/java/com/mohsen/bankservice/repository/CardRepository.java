package com.mohsen.bankservice.repository;



import com.mohsen.bankservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    public  Card findByCardNo(String cardNo);

}