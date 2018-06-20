package com.creffer.dto;

public class RequestDTO {
    private int number;
    private String username;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //конструктор по умолчанию обязателен
    public RequestDTO() {
    }

    public RequestDTO(int number, String username) {
        this.number = number;
        this.username = username;
    }
}
