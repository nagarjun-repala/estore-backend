package com.nagarjun.estorebackend.entity;
import java.sql.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String email;

    @NonNull
    private Date createdOn;

    private List<Product> orders;


    private List<Product> cart;
    
}
