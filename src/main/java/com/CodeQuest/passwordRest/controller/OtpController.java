package com.CodeQuest.passwordRest.controller;

import com.CodeQuest.passwordRest.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    // Sends OTP for email verification
    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {
        String result = otpService.sendOtp(email);
        return ResponseEntity.ok(result);
    }

    // Verifies OTP
    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = otpService.verifyOtp(email, otp);
        return isValid ? ResponseEntity.ok("OTP verified!") : ResponseEntity.badRequest().body("Invalid OTP!");
    }

    // Verifies OTP and proceeds with user registration
    @PostMapping("/verify-and-register")
    public ResponseEntity<String> verifyAndRegister(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = otpService.verifyOtp(email, otp);
        if (!isValid) {
            return ResponseEntity.badRequest().body("Invalid OTP!");
        }

        // If OTP is valid, call User API to create account
        // You need to implement the logic here to register the user
        return ResponseEntity.ok("OTP verified! User can be registered.");
    }
}
