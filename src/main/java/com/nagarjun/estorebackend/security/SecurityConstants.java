package com.nagarjun.estorebackend.security;

public class SecurityConstants {
    public static final String SECRET_KEY = ")H@McQfTjWnZr4u7x!A%D*G-KaNdRgUkXp2s5v8y/B?E(H+MbQeShVmYq3t6w9z$";
    public static final int TOKEN_EXPIRATION = 720000;
    public static final String BEARER = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String REGISTER_PATH = "/user/register";
    public static final String LOGIN_PATH = "/user/login";
    public static final Long DEFAULT_ROLE = 2L;
    public static final Long ROLE_ADMIN_ID = 1L;
    public static final Long ROLE_USER_ID = 2L;
    public static final String CONTENT_TYPE = "application/json";
    public static final String CHAR_ENCOCDE = "UTF-8";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String USER_CART_PATH = "/cart/*/product/*";
}
