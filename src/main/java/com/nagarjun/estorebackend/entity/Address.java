package com.nagarjun.estorebackend.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Pincode cannot be blank")
    @NonNull
    @Column
    private Integer pincode;

    @NotBlank(message = "Street cannot be blank")    
    @NonNull
    @Column
    private String street;

    @NotBlank(message = "City cannot be blank")   
    @NonNull
    @Column
    private String city;

    @NotBlank(message = "State cannot be blank")   
    @NonNull
    @Column
    private String state;

    @NotBlank(message = "Phone number cannot be blank")
    @NonNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(optional = false)
    @JoinColumns({
        @JoinColumn(name = "user_id", referencedColumnName = "id"),
        @JoinColumn(name = "username", referencedColumnName = "username")
    })

    @JsonIgnore
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Order> orders;
    
}
