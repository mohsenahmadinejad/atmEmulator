package com.mohsen.bankservice.repository;



import com.mohsen.bankservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    public User findByUserName(String userName);
}