package ar.edu.unlam.tpi.accounts.models;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Setter;
import lombok.Getter;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "company_type")
    private CompanyTypeEnum companyType;
    
    @OneToMany(mappedBy = "supplierCompany", orphanRemoval = true)
    private Set<CommentaryEntity> commentaries = new HashSet<>();
}
