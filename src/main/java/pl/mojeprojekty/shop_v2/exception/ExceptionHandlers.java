package pl.mojeprojekty.shop_v2.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.mojeprojekty.shop_v2.dto.ErrorDto;

import javax.validation.ConstraintViolationException;
import java.net.BindException;

@Slf4j
@ControllerAdvice
public class ExceptionHandlers {

    private ErrorDto handleException(Exception ex){
        log.error("Exception handled", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setExceptionClass(ex.getClass().getCanonicalName());
        errorDto.setMessage(ex.getMessage());
        return errorDto;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BindException.class,
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class})
    public ErrorDto handleValidationEXception(Exception exc){
        return handleException(exc);
    }
}
