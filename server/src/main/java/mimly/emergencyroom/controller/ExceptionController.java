package mimly.emergencyroom.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Slf4j(topic = "** EXCEPTION CONTROLLER ** ")
public class ExceptionController {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleDataAccessException(Throwable throwable) {
        log.debug(throwable.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(new Object[]{}, headers, HttpStatus.NOT_FOUND);
    }
}
