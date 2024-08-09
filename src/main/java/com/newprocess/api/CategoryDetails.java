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
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
@Entity
@Table(name = "tblpos_category_details")
public class CategoryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "ctgry_type", length = 50)
    private String categoryType;

    @Column(name = "ctgry_name", length = 50)
    private String categoryName;

    @CreatedBy
    @Column(name = "ctgry_created_by", length = 20)
    private Long categoryCreatedBy;

    @LastModifiedBy
    @Column(name = "ctgry_modified_by", length = 20)
    private Long categoryModifiedBy;

    @LastModifiedDate
    @Column(name = "ctgry_modified_date", length = 50)
    private LocalDateTime categoryModifiedDate;

    @CreatedDate
    @Column(name = "ctgry_created_date", nullable = false, length = 50)
    private Timestamp categoryCreatedDate;

    @Column(name = "ctgry_record_status", length = 20)
    private Long categoryRecordStatus;

    @Column(name = "ctgry_HSN_code", length = 30)
    private String categoryHSNCode;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private ItemDetails itemDetails;

}
