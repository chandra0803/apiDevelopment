package com.newprocess.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tblpos_rate_details")
public class RateDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rte_pk", nullable = false, length = 20)
    private BigInteger rtePk;

    @Column(name = "rte_slno", nullable = false, length = 20)
    private BigInteger rteSlno;//					-- Primary Key from 'item_batch_details_tab' Table

    @Column(name = "rte_itemcode", length = 100)
    private String rteItemCode;
    @Column(name = "rte_itemname", length = 100)
    private String rteItemName;
    @Column(name = "rte_rate1")
    private Long rteRate1;

    @Column(name = "rte_rate2")
    private Long rteRate2;

    @Column(name = "rte_rate3")
    private Long rteRate3;

    @Column(name = "rte_rate4")
    private Long rteRate4;

    @Column(name = "rte_rate5")
    private Long rteRate5;

    @Column(name = "rte_rate6")
    private Long rteRate6;

    @Column(name = "rte_created_by")
    private Long rteCreatedBy;

    @Column(name = "rte_modified_by")
    private Long rteModifiedBy;

    @Column(name = "rte_modified_date")
    private LocalDateTime rteModifiedDate;// DATETIME DEFAULT NULL,

    @Column(name = "rte_created_date", nullable = false, length = 20)
    private Timestamp rte_created_date;// TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    @Column(name = "rte_record_status")
    private Long rte_record_status;        //-- Not Used default value must be 0

    @Column(name = "rte_batchid")
    private Long rte_batchid;            //-- Primary Key from 'item_batch_details_tab' Table

    @Column(name = "rte_rate7")
    private Long rte_rate7;

    @Column(name = "rte_rate8")
    private Long rte_rate8;

    @Column(name = "rte_rate9")
    private Long rte_rate9;

    @Column(name = "rte_rate10")
    private Long rte_rate10;

    @Column(name = "rte_rate11")
    private Long rte_rate11;

    @Column(name = "rte_rate12")
    private Long rte_rate12;

    @Column(name = "rte_rate13")
    private Long rte_rate13;

    @Column(name = "rte_rate14")
    private Long rte_rate14;

    @Column(name = "rte_rate15")
    private Long rte_rate15;

    @Column(name = "rte_rate16")
    private Long rte_rate16;

    @Column(name = "rte_rate17")
    private Long rte_rate17;

    @Column(name = "rte_rate18")
    private Long rte_rate18;

    @Column(name = "rte_rate19")
    private Long rte_rate19;

    @Column(name = "rte_rate20")
    private Long rte_rate20;

    @Column(name = "rte_rate21")
    private Long rte_rate21;

    @Column(name = "rte_rate22")
    private Long rte_rate22;

    @Column(name = "rte_rate23")
    private Long rte_rate23;

    @Column(name = "rte_rate24")
    private Long rte_rate24;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemBatchDetails_id")
    private ItemBatchDetails itemBatchDetails;
}
