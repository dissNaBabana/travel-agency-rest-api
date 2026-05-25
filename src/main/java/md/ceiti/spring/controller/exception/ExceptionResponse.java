package md.ceiti.spring.controller.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private final int status;
    private final String messege;
    private final LocalDateTime timestamp;

    public ExceptionResponse(int status, String messege, LocalDateTime timestamp) {
        this.status = status;
        this.messege = messege;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public String getMessege() {
        return messege;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
