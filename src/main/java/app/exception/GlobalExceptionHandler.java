package app.exception;

import app.exception.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponseDto> handleApiException(ApiException ex, WebRequest request) {
        ErrorResponseDto response = new ErrorResponseDto();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(ex.getStatus().value());
        response.setError(ex.getStatus().getReasonPhrase());
        response.setErrorCode(ex.getErrorCode());
        response.setMessage(ex.getMessage());
        response.setPath(request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorResponseDto response = new ErrorResponseDto();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.setErrorCode("INTERNAL_ERROR");
        response.setMessage("An unexpected error occurred");
        response.setPath(request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}