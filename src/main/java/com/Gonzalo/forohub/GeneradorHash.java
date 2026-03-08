package com.Gonzalo.forohub;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneradorHash {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("forohub123"));
    }
}