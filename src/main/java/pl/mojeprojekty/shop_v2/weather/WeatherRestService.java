package pl.mojeprojekty.shop_v2.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.mojeprojekty.shop_v2.dto.WeatherDataDto;
import pl.mojeprojekty.shop_v2.dto.WeatherMainDataDto;

@Slf4j
@RequiredArgsConstructor
@Component
public class WeatherRestService {

    private final RestTemplate restTemplate;

    @Value("${api.weather.key}")
    private String apiKey;

    @Value("${api.weather.current.url}")
    private String url;


    @WeatherDataCached
    public WeatherDataDto fetchWeather(String city){
        String fullUrl = url.replace("{city}", city)
                .replace("{appId}", apiKey);
        try{
            log.info("CUSTOM LOG: Inside fetchWeather, before restTemplate.getForEntity");
            ResponseEntity<WeatherDataDto> dtoResponseEntity =
                    restTemplate.getForEntity(fullUrl, WeatherDataDto.class);
            WeatherDataDto body = dtoResponseEntity.getBody();
            body.setTimestamp(System.currentTimeMillis());
            log.info("CUSTOM LOG: Inside fetchWeather, after timeStamp set");
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
