package com.creffer.models.system;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tokens")
public class TokenModel {
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private long countId;*/
    @Id
    @Column(name = "user_id")
    @JoinTable(name = "users", joinColumns = @JoinColumn(name = "user_id"))
    private int userId;

    @Column(name = "token")
    private String token;

    public TokenModel(String token, int userId) {
        this.token = token;
        this.userId = userId;
    }

    public TokenModel() {
    }

    /*public long getCountId() {
        return countId;
    }

    public void setCountId(long countId) {
        this.countId = countId;
    }*/

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenModel that = (TokenModel) o;
        return //countId == that.countId &&
                userId == that.userId &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {

        return Objects.hash(/*countId,*/ userId, token);
    }
}
