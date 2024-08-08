package com.newprocess.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.sql.Timestamp;


//@AllArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
@Data
@Builder
@Entity
@Table(name = "tblpos_company_details")
public class CompanyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmpny_pk", nullable = false, length = 50)
    private Long companyId;

    @Column(name = "cmpny_code", length = 50)
    private String companyCode;

    @Column(name = "cmpny_name", length = 50)
    private String companyName;

    @Column(name = "cmpny_registeration_number", nullable = false)
    @ColumnDefault("0")
    private int companyRegistrationNumber;

    @Column(name = "cmpny_no_of_license", nullable = false)
    @ColumnDefault("0")
    private int companyNoOfLicense;

    @Column(name = "cmpny_modified_date", nullable = false, length = 50)
    private Timestamp companyModifiedDate;

    @Column(name = "cmpny_created_date", nullable = false, length = 50)
    private Timestamp companyCreatedDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cmpny_pk")
    private BranchDetails branchDetails;

}
