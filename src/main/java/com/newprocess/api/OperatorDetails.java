package com.newprocess.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tblpos_operator_details")
public class OperatorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "oprtr_pk")
    private Long operatorId;

    @Column(name = "oprtr_code", nullable = true, length = 50)
    private String operatorCode;

    @Column(name = "oprtr_name", nullable = true, length = 50)
    private String operatorName;

    @Column(name = "oprtr_password", length = 50)
    private String operatorPassword;

    @Column(name = "oprtr_privilage", length = 50)
    private Integer operatorPrivilege;

    @Column(name = "oprtr_phoneno", length = 50)
    private String operatorPhoneNo;

    @Column(name = "oprtr_email", length = 50)
    private String operatorEmail;

    @Column(name = "oprtr_branch_id", nullable = false, length = 50)
    private Long operatorBranchId;

    @Column(name = "oprtr_company_id", nullable = false, length = 50)
    private Long operatorCompanyId;

    @Column(name = "oprtr_created_by", length = 50)
    private Long operatorCreatedBy;

    @Column(name = "oprtr_modified_by", length = 50)
    private Long operatorModifiedBy;

    @Column(name = "oprtr_modified_date", length = 50)
    private LocalDateTime operatorModifiedDate;

    @Column(name = "oprtr_created_date", nullable = false, length = 50)
    private Timestamp operatorCreatedDate;

    @Column(name = "oprtr_record_status", length = 50)
    private Integer operatorRecordStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cmpny_pk")
    private CompanyDetails companyDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_pk")
    private BranchDetails branchDetails;
}
