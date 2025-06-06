package ar.edu.unlam.tpi.accounts.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "APPLICANT_COMPANY", schema = "USERS")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ApplicantCompanyEntity extends CompanyEntity {
}