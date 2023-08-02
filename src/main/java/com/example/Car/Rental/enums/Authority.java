package com.example.Car.Rental.enums;

import java.util.ArrayList;
import java.util.List;

public enum Authority {
    Admin,
    User;

    public List<Authority> getAllAuthority() {
        List<Authority> authorities = new ArrayList<>();
        return authorities;
    }
}
