package ar.edu.unlam.tpi.accounts.utils;

import java.util.List;

import ar.edu.unlam.tpi.accounts.models.GeolocationEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GeolocationDataHelper {

    public static List<GeolocationEntity> getGeolocations(){
        return List.of(
                // Nueva York - 0 - 1
            GeolocationEntity.builder()
                .lat(40.7128f)
                .ln(-74.0060f)
                .build(),
                // Cerca de 40.7128, -74.0060 (Nueva York) - 1
                GeolocationEntity.builder()
                        .lat(40.7135f)
                        .ln(-74.0050f)
                        .build(),
                // Cerca de 2 km de 40.7128, -74.0060 (Nueva York) - 2
                GeolocationEntity.builder()
                        .lat(40.7308f) // +0.018
                        .ln(-74.0060f)
                        .build(),
                // A unos 10 km de 40.7128, -74.0060 (Nueva York) - 3
                GeolocationEntity.builder()
                        .lat(40.8028f) // +0.09
                        .ln(-74.0060f)
                        .build(),
            // Los Angeles - 4
            GeolocationEntity.builder()
                .lat(34.0522f)
                .ln(-118.2437f)
                .build(),
                // Cerca de 34.0522, -118.2437 (Los Ángeles) - 5
                GeolocationEntity.builder()
                        .lat(34.0530f)
                        .ln(-118.2445f)
                        .build(),
                // Cerca de 2 km de 34.0522, -118.2437 (Los Ángeles) - 6
                GeolocationEntity.builder()
                        .lat(34.0522f)
                        .ln(-118.2257f) // +0.018
                        .build(),
                // A unos 10 km de 34.0522, -118.2437 (Los Ángeles) - 7
                GeolocationEntity.builder()
                        .lat(34.0522f)
                        .ln(-118.1537f) // +0.09
                        .build(),
                // Londres - 8
            GeolocationEntity.builder()
                .lat(51.5074f)
                .ln(-0.1278f)
                .build(),

                // Cerca de 51.5074, -0.1278 (Londres) - 9
                GeolocationEntity.builder()
                        .lat(51.5080f)
                        .ln(-0.1285f)
                        .build(),

                // Cerca de 2 km de 51.5074, -0.1278 (Londres) - 10
                GeolocationEntity.builder()
                        .lat(51.5074f)
                        .ln(-0.1098f) // +0.018
                        .build(),

                // A unos 10 km de 51.5074, -0.1278 (Londres) - 11
                GeolocationEntity.builder()
                        .lat(51.5074f)
                        .ln(-0.0378f) // +0.09
                        .build(),
                // Paris - 12
                GeolocationEntity.builder()
                        .lat(48.8566f)
                        .ln(2.3522f)
                        .build()
        );
    }
}
