package com.example.Car.Rental.enums;

import java.util.ArrayList;
import java.util.List;

public enum Authority {
    ADMIN,
    USER;

    public List<Authority> getAllAuthority() {
        List<Authority> authorities = new ArrayList<>();
        return authorities;
    }
}
