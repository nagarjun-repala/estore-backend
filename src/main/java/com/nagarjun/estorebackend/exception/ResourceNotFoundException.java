package com.nagarjun.estorebackend.exception;

import com.nagarjun.estorebackend.Constants;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resource) {
        super(resource + Constants.NOT_FOUND);
    }

    public ResourceNotFoundException(Long id, String resource) {
        super(resource + Constants.ID_FORMAT  + id + Constants.NOT_FOUND);
    }

    public ResourceNotFoundException(Long id, String resource, Long id2, String resource2) {
        super(resource + Constants.ID_FORMAT + id + " " + resource2 + Constants.ID_FORMAT + Constants.NOT_FOUND);
    }
    
}
