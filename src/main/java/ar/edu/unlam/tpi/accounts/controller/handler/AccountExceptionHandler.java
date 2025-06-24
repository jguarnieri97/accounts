package ar.edu.unlam.tpi.accounts.controller.handler;

import ar.edu.unlam.tpi.accounts.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.exceptions.GenericException;
import ar.edu.unlam.tpi.accounts.exceptions.InternalException;
import ar.edu.unlam.tpi.accounts.dto.response.ErrorResponseDto;

@ControllerAdvice
public class AccountExceptionHandler {
    
    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ErrorResponseDto> handleEmptyException(InternalException ex) {
        return ResponseEntity
        .status(ex.getCode())
        .body(ErrorResponseDto.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .detail(ex.getDetail())
                .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity
        .status(ex.getCode())
        .body(ErrorResponseDto.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .detail(ex.getDetail())
                .build());
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(GenericException ex) {
        return ResponseEntity
        .status(ex.getCode())
        .body(ErrorResponseDto.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .detail(ex.getDetail())
                .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto.builder()
                        .code(Constants.STATUS_BAD_REQUEST)
                        .message(Constants.BAD_REQUEST_ERROR)
                        .detail(ex.getMessage())
                        .build());
    }

}
