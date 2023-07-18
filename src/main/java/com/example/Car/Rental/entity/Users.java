package com.example.Car.Rental.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 25)
    private String name;
    @Column(nullable = false, length = 25)
    private String password;
    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private Branches branch;
}
