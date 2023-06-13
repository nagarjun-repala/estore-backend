package com.nagarjun.estorebackend.entity;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @NonNull
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @NonNull
    @Column(nullable = false)
    private String description;

    @Range(min = 0, message = "Min: 0")
    @NotNull(message = "Price cannot be blank")
    @NonNull
    @Column(nullable = false)
    private Integer price;

    @NotBlank(message = "imageURL cannot be blank")
    @NonNull
    @Column(nullable = false)
    private String imageUrl;

    @JsonIgnore   
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> review;
    
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    @JsonIgnore
    @ManyToMany(mappedBy = "products", fetch= FetchType.EAGER, cascade = {CascadeType.REFRESH})
    private Set<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderProductQuantity> orderProductQuantities;
}
