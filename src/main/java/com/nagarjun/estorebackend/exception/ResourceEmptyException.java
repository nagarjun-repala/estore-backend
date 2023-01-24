package com.nagarjun.estorebackend.exception;

import com.nagarjun.estorebackend.Constants;

public class ResourceEmptyException extends RuntimeException{

    public ResourceEmptyException(String resource) {
        super(resource + Constants.EMPTY);
    }

}
