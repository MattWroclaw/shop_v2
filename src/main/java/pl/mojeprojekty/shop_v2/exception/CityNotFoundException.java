package pl.mojeprojekty.shop_v2.exception;

public class CityNotFoundException extends RuntimeException{

    public CityNotFoundException(String city) {
        super("City " + city + " not found!");
    }
}
