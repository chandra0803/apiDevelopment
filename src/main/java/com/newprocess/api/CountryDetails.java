package com.newprocess.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
@Entity
@Table(name = "tblpos_country_details")
public class CountryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ctry_countryid", nullable = false, length = 20)
    private Long countryCountryId;

    @Column(name = "ctry_code", length = 50)
    private String countryCode;

    @Column(name = "ctry_name", length = 50)
    private String countryName;

    @Column(name = "ctry_branch_id", length = 50)
    private Long countryBranchId;

    @Column(name = "ctry_company_id", length = 50)
    private Long countryCompanyId;

    @Column(name = "ctry_status", length = 50)
    private Integer countryStatus;

    @CreatedBy
    @Column(name = "ctry_created_by", length = 50)
    private Long countryCreatedBy;

    @LastModifiedBy
    @Column(name = "ctry_modified_by", length = 50)
    private Long countryModifiedBy;

    @LastModifiedDate
    @Column(name = "ctry_modified_date", length = 50)
    private Timestamp countryModifiedDate;

    @CreatedDate
    @Column(name = "ctry_created_date", length = 50)
    private Timestamp countryCreatedDate;

}
