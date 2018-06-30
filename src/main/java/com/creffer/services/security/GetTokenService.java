package com.creffer.services.security;

import com.creffer.models.LoginModel;
import com.creffer.models.system.TokenModel;

public interface GetTokenService {
    TokenModel getToken(LoginModel model) throws Exception;
}
