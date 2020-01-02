package pl.mojeprojekty.shop_v2.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import pl.mojeprojekty.shop_v2.dto.WeatherDataDto;

@RequiredArgsConstructor
@Component
@Aspect
@Slf4j
public class WeatherDataCacheAspect {

    private final WeatherDataCache cache;

    @Pointcut("@annotation(pl.mojeprojekty.shop_v2.weather.WeatherDataCached)")
    public void cacheAnnotation(){}

    @Pointcut("args(java.lang.String, ..)")
    public void hasStringAsArgument(){}

    @Around("cacheAnnotation() && hasStringAsArgument()")
    public Object handleCache(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("CUSTOM LOG: Inside Aspect..");
        Object [] joinPointArgs = joinPoint.getArgs();
        String city = (String) joinPointArgs[0];
        WeatherDataDto cachedData = cache.find(city);
        if (cachedData != null){
            log.info("CUSTOM LOG: This weather data comes from cache, it is still valid");
            return cachedData;
        }
        WeatherDataDto weatherFromAPI = (WeatherDataDto) joinPoint.proceed();
        cache.cacheWeatherData(city, weatherFromAPI);
        log.info("CUSTOM LOG: Data from API. Either this is first request for that city or data in cache was simply old");
        return weatherFromAPI;
    }

}
