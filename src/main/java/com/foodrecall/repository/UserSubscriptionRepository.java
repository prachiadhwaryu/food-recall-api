package com.foodrecall.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.foodrecall.entity.UserSubscription;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {

    List<UserSubscription> findByEmail(String email);

    boolean existsByEmailAndStateIgnoreCase(String email, String state);

}