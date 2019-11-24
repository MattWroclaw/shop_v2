package pl.mojeprojekty.shop_v2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherMainDataDto {

    private String temp;

    private String pressure;

    private String humidity;

    private String temp_min;

    private String temp_max;
}
