package pl.mojeprojekty.shop_v2.weather;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class WeatherDataCache {

    private static final long DURATION_OF_VALID_WEATHER_DATA = 1000 * 60 * 60;
    private final Map<String, String[]> weatherTable = new HashMap<>();

    public String[] find(String city) {
        String[] cachedWeather = weatherTable.get(city.toLowerCase());
        long now = System.currentTimeMillis();

        log.info("CUSTOM LOG: inside caching method");

        if (cachedWeather == null || (now - Long.parseLong(cachedWeather[5]) > DURATION_OF_VALID_WEATHER_DATA)) {
            weatherTable.remove(city.toLowerCase());
            log.info("CUSTOM LOG: data from cache is old, method will not return anything");
            return null;
        }
        return cachedWeather;
    }

    public void cacheWeatherData(String city, String[] weatherFromAPI) {
        weatherTable.put(city.toLowerCase(), weatherFromAPI);
        log.info("CUSTOM LOG: successfully cached new weather data");
    }
}
