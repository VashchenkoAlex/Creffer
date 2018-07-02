package com.creffer.services.security;

public interface GetTokenService {
    String getToken(String email, String password) throws Exception;
}
