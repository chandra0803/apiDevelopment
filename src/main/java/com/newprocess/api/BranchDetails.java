package com.newprocess.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
@Entity
@Table(name = "tblpos_branch_details")
public class BranchDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_pk", nullable = false, length = 20)
    private Long branchDetailsId;

    @NotNull(message = "BranchCompanyId is required")
    @Column(name = "branch_company_id", nullable = false, length = 50)
    private String branchCompanyId;

    @Column(name = "branch_code", length = 50)
    private String branchCode;

    @Column(name = "branch_name", length = 50)
    private String branchName;

    @LastModifiedDate
    @Column(name = "branch_modified_date", nullable = false, length = 50)
    private Timestamp branchModifiedDate;

    @CreatedDate
    @Column(name = "branch_created_date", nullable = false, length = 50)
    private LocalDateTime branchCreatedDate;

    @Column(nullable = false, columnDefinition = "bit default 0", length = 1)
    private int branchStatus;

    @OneToOne(mappedBy = "branchDetails", cascade = CascadeType.ALL)
    private CompanyDetails companyDetails;
}