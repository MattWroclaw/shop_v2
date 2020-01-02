package pl.mojeprojekty.shop_v2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDataDto {

    private WeatherMainDataDto main;

    private long timestamp;
}
