package med.voll.api.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorRepositoryEntitiyNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity badRequest(MethodArgumentNotValidException e){
        var error = e.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(DadosRetornoBadRequest::new).toList());
    }

    private record DadosRetornoBadRequest(String campo, String mensagem){
        public DadosRetornoBadRequest(FieldError err){
            this(err.getField(), err.getDefaultMessage());
        }
    }

}
