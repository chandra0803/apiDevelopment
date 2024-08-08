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
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
@Entity
@Table(name = "itemdetails_tab")
public class ItemDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "item_id", nullable = false, length = 20)
    private Long itemId;

    @Column(name = "item_code", length = 100)
    private Long item_code;

    @Column(name = "item_name", length = 200)
    private String itemName;

    @Column(name = "hsn_code", length = 100)
    private Long hsnCode;

    @Column(name = "barcode", length = 100)
    private Long barCode;

    @Column(name = "unit_id", length = 20)
    private Long unitId;

    @Column(name = "category_id", length = 20)
    private Long categoryId;

    @Column(name = "item_status", length = 4)
    private Integer itemStatus;

    @CreatedDate
    @Column(name = "createddate")
    private LocalDateTime createdDate;

    @CreatedBy
    @Column(name = "createdby")
    private Long createdBy;

    @LastModifiedDate
    @Column(name = "modifieddate")
    private LocalDateTime modifiedBate;

    @LastModifiedBy
    @Column(name = "modifiedby")
    private Integer modifiedBy;

    @Column(name = "typepicture")
    private Long typePicture;

    @Column(name = "typepicture_name", length = 100)
    private Long typePictureName;

    @Column(name = "typepicture_path", length = 300)
    private String typepicturePath;


    @OneToOne(mappedBy = "itemDetails_unit", cascade = CascadeType.ALL)
    private UnitDetails unitDetails;

    @OneToOne(mappedBy = "itemDetails", cascade = CascadeType.ALL)
    private CategoryDetails categoryDetails;

}
