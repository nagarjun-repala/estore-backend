package com.nagarjun.estorebackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @NonNull
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @NonNull
    @Column(nullable = false)
    private String description;

    @NotBlank(message = "Rating cannot be blank")    
    @NonNull
    @Column(nullable = false)
    private String rating;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumns({
        @JoinColumn(name = "user_id", referencedColumnName = "id"),
        @JoinColumn(name = "username", referencedColumnName = "username")
    })
    @JsonIgnore
    private User user;
   
}
