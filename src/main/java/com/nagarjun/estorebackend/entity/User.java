package com.nagarjun.estorebackend.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long id;

    @NotBlank(message = "Firstname cannot be blank")
    @NonNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Lastname cannot be blank")
    @NonNull
    @Column(name = "last_name", nullable = false)    
    private String lastName;

    @NotBlank(message = "Username cannot be blank")
    @NonNull
    @Column(nullable = false)    
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @NonNull
    @Column(nullable = false)    
    @Getter(onMethod = @__(@JsonIgnore))
    @Setter(onMethod = @__(@JsonProperty))
    private String password;

    @NotBlank(message = "Email cannot be blank")
    @NonNull
    @Column(nullable = false)
    private String email;

    @NonNull
    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
