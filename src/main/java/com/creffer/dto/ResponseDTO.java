package com.creffer.dto;

import java.time.LocalDateTime;

public class ResponseDTO {
    private boolean isWin;
    private LocalDateTime time;

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public ResponseDTO(boolean isWin, LocalDateTime time) {
        this.isWin = isWin;
        this.time = time;
    }
}
