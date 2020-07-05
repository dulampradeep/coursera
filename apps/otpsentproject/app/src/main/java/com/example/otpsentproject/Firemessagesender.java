package com.example.otpsentproject;

import androidx.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

public class Firemessagesender extends FirebaseMessagingService {
    private String token;

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        token = s;
    }
}

