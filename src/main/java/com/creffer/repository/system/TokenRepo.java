package com.creffer.repository.system;

import com.creffer.models.system.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tokenRepository")
public interface TokenRepo extends JpaRepository<TokenModel,Long>{
    TokenModel save(TokenModel token);
    TokenModel findByUserId(long id);
    //TokenModel update(TokenModel token);
}
