package pl.mojeprojekty.shop_v2.weather;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.mojeprojekty.shop_v2.dto.WeatherDataDto;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Component
public class WeatherDataCache {

    private static final long  ONE_HOUR = 3600000;
    private final Map<String, WeatherDataDto> weatherDataCache = new HashMap<>();

    public WeatherDataDto find(String city){
        WeatherDataDto cachedWeather = weatherDataCache.get(city.toLowerCase());
        long now = System.currentTimeMillis();
        log.info("CUSTOM LOG: inside caching method");
        if(cachedWeather == null || (now - cachedWeather.getTimestamp() > ONE_HOUR)) {
            weatherDataCache.remove(city.toLowerCase());
            log.info("CUSTOM LOG: data from cache is old, method will not return anything");
            return null;
        }
        return cachedWeather;
    }

    public void cacheWeatherData(String city, WeatherDataDto dataDto){
        weatherDataCache.put(city.toLowerCase(), dataDto);
        log.info("CUSTOM LOG: successfully cached new weather data");
    }
}
