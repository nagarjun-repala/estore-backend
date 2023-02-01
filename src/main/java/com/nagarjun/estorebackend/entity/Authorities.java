// package com.nagarjun.estorebackend.entity;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.JoinColumns;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.NonNull;
// import lombok.RequiredArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @RequiredArgsConstructor
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// @Table(name = "authorities")
// public class Authorities {

//     //TODO::

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id")
//     private Long id;

//     @NonNull
//     @Column
//     private String resource;

//     @NonNull
//     @Column
//     private String access;

//     @ManyToOne(optional = false)
//     @JoinColumns({
//         @JoinColumn(name = "user_id", referencedColumnName = "id"),
//         @JoinColumn(name = "username", referencedColumnName = "username")
//     })
//     @JsonIgnore
//     private User user;
    
// }
