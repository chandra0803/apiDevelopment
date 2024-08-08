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

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tblpos_state_details")
public class StateDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stat_statepk", nullable = false, length = 20)
    private Long statStatepk;

    @Column(name = "stat_country_id", nullable = false, length = 50)
    private Long statCountryId;

    @Column(name = "stat_code", length = 50)
    private String statCode;

    @Column(name = "stat_name", length = 50)
    private String statName;

    @LastModifiedDate
    @Column(name = "stat_branch_id", nullable = false, length = 50)
    private Long statBranchId;

    @CreatedDate
    @Column(name = "stat_company_id", nullable = false, length = 50)
    private Long statCompanyId;

    @Column(name = "stat_status", nullable = false)
    private Integer statStatus;

    @CreatedBy
    @Column(name = "stat_created_by", length = 20)
    private Long statCreatedBy;

    @LastModifiedBy
    @Column(name = "stat_modified_by", length = 20)
    private Long statModifiedBy;

    @LastModifiedDate
    @Column(name = "stat_modified_date", length = 50)
    private LocalDateTime staModifiedDate;

    @CreatedDate
    @Column(name = "stat_created_date", nullable = false, length = 50)
    private Timestamp statCreatedDate;
}
