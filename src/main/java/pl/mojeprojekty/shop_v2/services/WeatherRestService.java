package pl.mojeprojekty.shop_v2.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.mojeprojekty.shop_v2.dto.WeatherDataDto;
import pl.mojeprojekty.shop_v2.dto.WeatherMainDataDto;
import pl.mojeprojekty.shop_v2.exception.CityNotFoundException;

@Slf4j
@RequiredArgsConstructor
@Component
public class WeatherRestService {

    private final RestTemplate restTemplate;

    @Value("${api.weather.key}")
    private String apiKey;

    @Value("${api.weather.current.url}")
    private String url;


    public WeatherDataDto fetchWeather(String city){
        String fullUrl = url.replace("{city}", city)
                .replace("{appId}", apiKey);
        try{
            ResponseEntity<WeatherDataDto> dtoResponseEntity =
                    restTemplate.getForEntity(fullUrl, WeatherDataDto.class);
            WeatherDataDto body = dtoResponseEntity.getBody();
            return body;
        } catch (Exception error){
        }

        return null;
    }

    public String[] weatherData(String city){
        WeatherDataDto weatherDataDto = fetchWeather(city);
        if(weatherDataDto != null) {
            WeatherMainDataDto main = weatherDataDto.getMain();

            String[] rawWeatherData = {main.getTemp(),
                    main.getHumidity(),
                    main.getPressure(),
                    main.getTemp_max(),
                    main.getTemp_min()
            };
            return rawWeatherData;
        }
       return null;
    }
}
