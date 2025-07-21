package com.foodrecall.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.foodrecall.entity.UserSubscription;
import com.foodrecall.exception.SubscriptionNotFoundException;
import com.foodrecall.repository.UserSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import com.foodrecall.service.UserSubscriptionService;

@Service
@RequiredArgsConstructor

public class UserSubscriptionServiceImpl implements UserSubscriptionService {

    private final UserSubscriptionRepository repository;

    @Override
    public UserSubscription saveSubscription(UserSubscription subscription) {
        return repository.save(subscription);
    }

    @Override
    public List<UserSubscription> getAllSubscriptions() {
        return repository.findAll();
    }

    @Override
    public void deleteSubscriptionById(Long id) {
        if (!repository.existsById(id)) {
            throw new SubscriptionNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public boolean isAlreadySubscribed(String email, String state) {
        return repository.existsByEmailAndStateIgnoreCase(email, state);
    }

    @Override
    public String subscribeUser(UserSubscription subscription) {
        if (isAlreadySubscribed(subscription.getEmail(), subscription.getState())) {
            return "Already subscribed!";
        }

        try {
            saveSubscription(subscription);
            return subscription.getEmail() + " Successfully subscribed.";
        } catch (Exception e) {
            System.err.println("Error while saving subscription: " + e.getMessage());
            return "Subscription failed due to an internal error.";
        }
    }

}
