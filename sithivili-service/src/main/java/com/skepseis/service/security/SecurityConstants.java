package com.skepseis.service.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000;// 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String[] AUTH_WHITELIST = {

            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/documentation/**",

            //login
            "/api/users/user/login",

            //signup
            "/api/users/user/new",

            //healthCheck
            "/healthCheck",

            //user verification
            "/api/users/user/verify",

            //password reset email
            "/api/users/user/email/reset",

            //password reset
            "/api/users/user/password/reset",

            //logout
            "/api/users/user/logout/**",

            //locations
            "/api/locations/**"



    };
}
