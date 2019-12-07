package pl.mojeprojekty.shop_v2.dto;

import lombok.Data;

@Data
public class ErrorDto {

    private String message;
    private String exceptionClass;
}
