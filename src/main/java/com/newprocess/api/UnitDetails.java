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


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tblpos_unit_details")
public class UnitDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_pk", nullable = false, length = 20)
    private Long unitPk;

    @Column(name = "unit_unit", length = 50)
    private String unitUnit;

    @Column(name = "unit_unitname", length = 50)
    private String unitUnitname;

    @CreatedBy
    @Column(name = "unit_created_by", length = 20)
    private Long unitCreatedBy;

    @LastModifiedBy
    @Column(name = "unit_modified_by", length = 20)
    private Long unitModifiedBy;

    @LastModifiedDate
    @Column(name = "unit_modified_date")
    private Long unitModifiedDate;

    @CreatedDate
    @Column(name = "unit_created_date", nullable = false)
    private Timestamp unitCreatedDate;

    @Column(name = "unit_record_status")
    private Long unitRecordStatus;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private ItemDetails itemDetails_unit;
}
