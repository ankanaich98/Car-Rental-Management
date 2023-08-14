package com.example.Car.Rental.entities;

import com.example.Car.Rental.enums.Authority;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 25)
    private String name;
    @Column(nullable = false, length = 25)
    private String password;
    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private Branch branch;
    @Enumerated(EnumType.STRING)
    private Authority role;
}
