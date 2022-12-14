package com.nagarjun.estorebackend.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private Product product;

    @NonNull
    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @Range(min=0, message = "Min: 0")
    @NotNull(message = "Price cannot be blank")    
    @NonNull
    @Column
    private Integer quantity;

    @NonNull
    @Column(name = "updated_on", nullable = false)
    private LocalDateTime updatedOn;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    
}
