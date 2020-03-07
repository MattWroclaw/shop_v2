package pl.mojeprojekty.shop_v2.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.mojeprojekty.shop_v2.dto.ErrorDto;

@Slf4j
@ControllerAdvice
public class ExceptionHandlers {


    private ErrorDto handleException(Exception ex) {
        log.error("This is exception handler. Something went wrong...", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setExceptionClass(ex.getClass().getCanonicalName());
        errorDto.setMessage(ex.getMessage());
        return errorDto;
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        log.error("Something went wrong.. we have an error", ex);
        model.addAttribute("exception", ex);
        return "error-page";
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({BindException.class,
//            ConstraintViolationException.class,
//            MethodArgumentNotValidException.class})
//    public ErrorDto handleValidationException(Exception exc){
//        return handleException(exc);
//    }
}
