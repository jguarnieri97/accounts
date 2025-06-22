package ar.edu.unlam.tpi.accounts.models;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(mappedBy = "labels")
    private Set<SupplierCompanyEntity> suppliers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
