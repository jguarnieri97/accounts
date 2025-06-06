package ar.edu.unlam.tpi.accounts.models;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.Table;

@Entity
@Table(name = "SUPPLIER_COMPANY", schema = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
public class SupplierCompanyEntity extends CompanyEntity {

    @Column(name = "comments_count")
    private Integer commentsCount;

    @Column(name = "avg_price")
    private Float avgPrice;

    @Column(name = "avg_score")
    private Float score;

    @Column(name = "company_type")
    private CompanyTypeEnum companyType;
    
    @OneToMany(mappedBy = "supplierCompany", orphanRemoval = true)
    private Set<CommentaryEntity> commentaries = new HashSet<>();
}
