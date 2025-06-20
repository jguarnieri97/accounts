package ar.edu.unlam.tpi.accounts.mapper;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class LabelMapper {

    private static final Map<String, String> spanishToTag = Map.ofEntries(
        //CONTRACTOR
        Map.entry("Reparación de techos", "roof_repair"),
        Map.entry("Reparación de paredes", "wall_repair"),
        Map.entry("Pintura", "painting"),
        Map.entry("Reparación de infraestructura", "infrastructure_repair"),
        Map.entry("Construcciones generales", "infrastructure_build"),
        Map.entry("Revoques", "unplastered"),

        //ELECTRICIAN
        Map.entry("Reparación de tableros eléctricos", "electrical_wiring_repair"),
        Map.entry("Mantenimiento de tableros eléctricos", "electrical_maintenance"),
        Map.entry("Reparación de luminarias", "light_repair"),
        Map.entry("Sistemas lumínicos", "light_systems"),
        Map.entry("Fallas eléctricas", "electrical_failures"),
        Map.entry("Cableados eléctricos", "electrical_wiring"),
        Map.entry("Colocación de tomacorrientes", "power_outlets"),
     
        //CLEANING
        Map.entry("Limpieza de oficinas", "office_cleaning"),
        Map.entry("Limpieza de ventanas", "window_cleaning"),
        Map.entry("Limpieza de pisos", "floor_cleaning"),
        Map.entry("Limpieza post-construcción", "post_construct_cleaning"),
        Map.entry("Limpieza de sanitarios", "sanitary_cleaning")
    );

    public Optional<String> getLabelByWorkResume(String workResume) {
        return Optional.ofNullable(spanishToTag.get(workResume));
    }
}