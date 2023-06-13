package com.nagarjun.estorebackend.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class VerifyUser implements Serializable{

  private String message;

  
}
