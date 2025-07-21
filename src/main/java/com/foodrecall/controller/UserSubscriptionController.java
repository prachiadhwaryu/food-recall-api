package com.foodrecall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecall.dto.UserSubscriptionRequest;
import com.foodrecall.entity.UserSubscription;
import com.foodrecall.service.UserSubscriptionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
@Validated
public class UserSubscriptionController {

    private final UserSubscriptionService userSubscriptionService;

    @PostMapping
    public ResponseEntity<String> subscribeUser(@Valid @RequestBody UserSubscriptionRequest request) {
        UserSubscription subscription = new UserSubscription();
        subscription.setEmail(request.getEmail());
        subscription.setState(request.getState());

        String response = userSubscriptionService.subscribeUser(subscription);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserSubscription>> getAllSubscriptions() {
        return ResponseEntity.ok(userSubscriptionService.getAllSubscriptions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubscription(@PathVariable Long id) {
        userSubscriptionService.deleteSubscriptionById(id);
        return ResponseEntity.ok("Subscription removed successfully!");
    }

}
