package com.creffer.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "request_table")
public class RequestTable {
    @Column
    private int realNum;
    @Column
    private int guess;
    @Column(name = "request_id")
    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    private UUID chanceId;

    public int getRealNum() {
        return realNum;
    }

    public void setRealNum(int realNum) {
        this.realNum = realNum;
    }

    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public UUID getChanceId() {
        return chanceId;
    }

    public void setChanceId(UUID chanceId) {
        this.chanceId = chanceId;
    }

    public RequestTable() {
    }

    public RequestTable(int realNum, int guess, UUID chanceId) {
        this.realNum = realNum;
        this.guess = guess;
        this.chanceId = chanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestTable that = (RequestTable) o;
        return realNum == that.realNum &&
                guess == that.guess &&
                Objects.equals(chanceId, that.chanceId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(realNum, guess, chanceId);
    }
}
