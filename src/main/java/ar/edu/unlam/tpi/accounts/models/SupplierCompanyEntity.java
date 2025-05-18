package ar.edu.unlam.tpi.accounts.models;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier_companies")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
public class SupplierCompanyEntity extends CompanyEntity {
    private Integer commentsCount;
    private Float avgPrice;
    private Float score;

    @OneToOne
    @JoinColumn(name = "company_type_id")
    private CompanyTypeEntity companyType;
    
    @OneToMany(mappedBy = "supplierCompany", orphanRemoval = true)
    private Set<CommentaryEntity> commentaries = new HashSet<>();
}
