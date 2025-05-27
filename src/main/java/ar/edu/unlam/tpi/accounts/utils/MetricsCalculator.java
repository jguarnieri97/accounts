package ar.edu.unlam.tpi.accounts.utils;

import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class MetricsCalculator {

    public Float calculateAvgPrice(SupplierCompanyEntity supplier, Integer price) {
        if (price == null) {
            throw new IllegalArgumentException("El precio no puede ser nulo");
        }
        float currentTotal = supplier.getAvgPrice() * (supplier.getCommentsCount() - 1);
        float newTotal = currentTotal + price;
        return newTotal / supplier.getCommentsCount();
    }

    public Float calculateAvgScore(SupplierCompanyEntity supplier, Double score) {
        float currentTotal = supplier.getScore() * (supplier.getCommentsCount() - 1);
        float newTotal = currentTotal + score.floatValue();
        return newTotal / supplier.getCommentsCount();
    }
}
