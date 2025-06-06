package ar.edu.unlam.tpi.accounts.utils;

import java.util.List;

import ar.edu.unlam.tpi.accounts.models.Geolocation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GeolocationDataHelper {

    public static List<Geolocation> getGeolocations(){
        return List.of(
                // Florencio Varela, San justo - 0 - 1
            GeolocationEntity.builder()
                .lat(-34.67044f)
                .ln(-58.56209f)
                .build(),
                // Cerca San justo - 1
                GeolocationEntity.builder()
                        .lat(-34.6690f)
                        .ln(-58.5600f)
                        .build(),
// Cerca de 2 km san justo - 2
                GeolocationEntity.builder()
                        .lat(-34.6550f) // aproximadamente 2 km al norte
                        .ln(-58.5600f)
                        .build(),
// A unos 10 km de san justo - 3
                GeolocationEntity.builder()
                        .lat(-34.6100f) // aproximadamente 10 km al noreste (CABA)
                        .ln(-58.4450f)
                        .build(),
            // Av. Santa Fe 5000, C1425BHY, Ciudad Autónoma de Buenos Aires - 4
            GeolocationEntity.builder()
                .lat(-34.5770f)
                .ln(-58.4310f)
                .build(),
                // Cerca de Av. Santa Fe 5000 - 1 (a ~0.1 km)
                GeolocationEntity.builder()
                        .lat(-34.5765f)
                        .ln(-58.4305f)
                        .build(),
// Cerca de 2 km de Av. Santa Fe 5000 - 2
                GeolocationEntity.builder()
                        .lat(-34.5630f)
                        .ln(-58.4300f)
                        .build(),
// A unos 10 km de Av. Santa Fe 5000 - 3
                GeolocationEntity.builder()
                        .lat(-34.6500f)
                        .ln(-58.4660f)
                        .build(),
                // Esteban de Luca 2237, C1246ABS Cdad. Autónoma de Buenos Aires - 8
            GeolocationEntity.builder()
                .lat(-34.6340f)
                .ln(-58.4065f)
                .build(),

                // Cerca Esteban de Luca 2237 - 9 (a ~0.1 km)
                GeolocationEntity.builder()
                        .lat(-34.6331f)
                        .ln(-58.4060f)
                        .build(),

// Cerca de 2 km de Esteban de Luca 2237 - 10
                GeolocationEntity.builder()
                        .lat(-34.6175f)
                        .ln(-58.4065f)
                        .build(),

// A unos 10 km de Esteban de Luca 2237 - 11
                GeolocationEntity.builder()
                        .lat(-34.5500f)
                        .ln(-58.4500f)
                        .build(),
                // Paris - 12
                GeolocationEntity.builder()
                        .lat(48.8566f)
                        .ln(2.3522f)
                        .build()
        );
    }
}
