package com.nagarjun.estorebackend.exception;

import com.nagarjun.estorebackend.Constants;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Long id, String resource) {
        super(resource + Constants.ID_FORMAT  + id + Constants.NOT_FOUND);
    }
    
}
