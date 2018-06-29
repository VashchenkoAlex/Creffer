package com.creffer.models.system;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tokens")
public class TokenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private long countId;

    @Column(name = "user_email")
    private String email;

    @Column(name = "token")
    private String token;

    public TokenModel(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public TokenModel() {
    }

    public long getCountId() {
        return countId;
    }

    public void setCountId(long countId) {
        this.countId = countId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenModel that = (TokenModel) o;
        return countId == that.countId &&
                Objects.equals(email, that.email) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {

        return Objects.hash(countId, email, token);
    }
}
