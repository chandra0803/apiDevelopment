package com.newprocess.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
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
@DynamicInsert
@Entity
@Table(name = "item_batch_details_tab")
public class ItemBatchDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itb_id", nullable = false, length = 20)
    private Long itemBatchId;

    @Column(name = "item_id", nullable = false, length = 20)
    private Long itemId;

    @Column(name = "batchcode", length = 100)
    private String batchCode;

    @Column(name = "item_stock", length = 100)
    private Long itemStock;

    @Column(name = "mrp_rate")
    private Long mrpRate;

    @Column(name = "rate_a")
    private Long rateA;

    @Column(name = "rate_b", columnDefinition = "bit default 0")
    private Long rateB;

    @Column(name = "rate_c", columnDefinition = "bit default 0")

    private Long rateC;

    @Column(name = "rate_d", columnDefinition = "bit default 0")
    private Long rateD;

    @Column(name = "minsaleqty")
    private Long minSaleQuantity;

    @Column(name = "rateedit")
    private Long rateEdit;

    @CreatedDate
    @Column(name = "createddate")
    private LocalDateTime createDate;

    @CreatedBy
    @Column(name = "createdby")
    private Long createdBy;
}
