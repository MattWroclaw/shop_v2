package pl.mojeprojekty.shop_v2.dto;

import lombok.Data;
import pl.mojeprojekty.shop_v2.entity.Product;

import java.lang.reflect.Field;

@Data
public class ErrorDto {

    private String message;
    private String exceptionClass;

}
