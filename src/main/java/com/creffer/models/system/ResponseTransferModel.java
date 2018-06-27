package com.creffer.models.system;

public class ResponseTransferModel {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ResponseTransferModel(String text) {
        this.text = text;
    }
}
