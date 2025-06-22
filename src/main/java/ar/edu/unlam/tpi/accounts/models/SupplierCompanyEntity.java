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

    @ManyToOne
    @JoinColumn(name = "company_type")
    private CategoryEntity companyType;
    
    @OneToMany(mappedBy = "supplierCompany", orphanRemoval = true)
    private Set<CommentaryEntity> commentaries = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "SUPPLIER_LABEL", schema = "USERS",
        joinColumns = @JoinColumn(name = "supplier_id"),
        inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private Set<LabelEntity> labels = new HashSet<>();
    
}
