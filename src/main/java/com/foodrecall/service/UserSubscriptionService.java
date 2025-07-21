package com.foodrecall.service;

import java.util.List;

import com.foodrecall.entity.UserSubscription;

public interface UserSubscriptionService {

    UserSubscription saveSubscription(UserSubscription subscription);

    List<UserSubscription> getAllSubscriptions();

    void deleteSubscriptionById(Long id);

    boolean isAlreadySubscribed(String email, String state);

    String subscribeUser(UserSubscription subscription);

}
