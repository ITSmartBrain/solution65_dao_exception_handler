package app.exception.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String errorCode;
    private String message;
    private String path;
}