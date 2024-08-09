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
    private BigInteger rateId;

    @Column(name = "rte_slno", nullable = false, length = 20)
    private BigInteger rateSlNo;//

    @Column(name = "rte_itemcode", length = 100)
    private String rateItemCode;

    @Column(name = "rte_itemname", length = 100)
    private String rateItemName;

    @Column(name = "rte_rate1")
    private Long rateRate1;

    @Column(name = "rte_rate2")
    private Long rateRate2;

    @Column(name = "rte_rate3")
    private Long rateRate3;

    @Column(name = "rte_rate4")
    private Long rateRate4;

    @Column(name = "rte_rate5")
    private Long rateRate5;

    @Column(name = "rte_rate6")
    private Long rateRate6;

    @Column(name = "rte_created_by")
    private Long rateCreatedBy;

    @Column(name = "rte_modified_by")
    private Long rateModifiedBy;

    @Column(name = "rte_modified_date")
    private LocalDateTime rateModifiedDate;// DATETIME DEFAULT NULL,

    @Column(name = "rte_created_date", nullable = false, length = 20)
    private Timestamp rateCreatedDate;// TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    @Column(name = "rte_record_status")
    private Long rateRecordStatus;        //-- Not Used default value must be 0

    @Column(name = "rte_batchid")
    private Long rteBatchId;            //-- Primary Key from 'item_batch_details_tab' Table

    @Column(name = "rte_rate7")
    private Long rateRate7;

    @Column(name = "rte_rate8")
    private Long rateRate8;

    @Column(name = "rte_rate9")
    private Long rateRate9;

    @Column(name = "rte_rate10")
    private Long rateRate10;

    @Column(name = "rte_rate11")
    private Long rateRate11;

    @Column(name = "rte_rate12")
    private Long rateRate12;

    @Column(name = "rte_rate13")
    private Long rateRate13;

    @Column(name = "rte_rate14")
    private Long rateRate14;

    @Column(name = "rte_rate15")
    private Long rateRte15;

    @Column(name = "rte_rate16")
    private Long rateRate16;

    @Column(name = "rte_rate17")
    private Long rateRate17;

    @Column(name = "rte_rate18")
    private Long rateRate18;

    @Column(name = "rte_rate19")
    private Long rateRate19;

    @Column(name = "rte_rate20")
    private Long rateRate20;

    @Column(name = "rte_rate21")
    private Long rateRate21;

    @Column(name = "rte_rate22")
    private Long rateRate22;

    @Column(name = "rte_rate23")
    private Long rateRate23;

    @Column(name = "rte_rate24")
    private Long rateRate24;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemBatchId")
    private ItemBatchDetails itemBatchDetails;
}
