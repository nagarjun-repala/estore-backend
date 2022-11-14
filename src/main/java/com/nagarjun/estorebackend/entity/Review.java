package com.nagarjun.estorebackend.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.*;

@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String rating;
   
}
