package com.creffer.services.security;

import com.creffer.models.system.TokenModel;

public interface GetTokenService {
    TokenModel getToken(String email, String password, String ip) throws Exception;
}
