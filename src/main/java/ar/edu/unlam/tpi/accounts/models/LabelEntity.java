package ar.edu.unlam.tpi.accounts.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "LABEL", schema = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tag;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierCompanyEntity supplier;
}
