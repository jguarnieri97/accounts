package ar.edu.unlam.tpi.accounts.utils;

import java.util.List;

import ar.edu.unlam.tpi.accounts.models.Geolocation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GeolocationDataHelper {

    public static List<Geolocation> getGeolocations(){
        return List.of(
            Geolocation.builder()
                .lat(40.7128f)
                .ln(-74.0060f)
                .build(),
            Geolocation.builder()
                .lat(34.0522f)
                .ln(-118.2437f)
                .build(),
            Geolocation.builder()
                .lat(51.5074f)
                .ln(-0.1278f)
                .build(),
            Geolocation.builder()
                .lat(48.8566f)
                .ln(2.3522f)
                .build(),
            Geolocation.builder()
                .lat(35.6895f)
                .ln(139.6917f)
                .build()
        );
    }
}
