package com.nagarjun.estorebackend.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppStatusController {

  @GetMapping("/status")
  public ResponseEntity<String> status() {
      return new ResponseEntity<String>("app is running", HttpStatus.OK);
  }

  
  
}
