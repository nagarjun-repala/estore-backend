package com.nagarjun.estorebackend.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NonNull
    @Column(name = "last_name", nullable = false)    
    private String lastName;

    @NonNull
    @Column(nullable = false)    
    private String username;

    @NonNull
    @Column(nullable = false)    
    private String password;

    @NonNull
    @Column(nullable = false)
    private String email;

    @NonNull
    @Column(name = "created_on", nullable = false)
    private LocalDate createdOn;
    
}
