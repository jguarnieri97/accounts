package ar.edu.unlam.tpi.accounts.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "commentaries")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentaryEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String comment;
    private Double score;

    @ManyToOne
    @JoinColumn(name = "supplier_company_id")
    private SupplierCompanyEntity supplierCompany;

    @ManyToOne
    @JoinColumn(name = "applicant_company_id")
    private ApplicantCompanyEntity applicantCompany;
}