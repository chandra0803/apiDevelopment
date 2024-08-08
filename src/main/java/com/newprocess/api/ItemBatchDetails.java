package com.newprocess.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Entity
@Table(name = "item_batch_details_tab")
public class ItemBatchDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itb_id", nullable = false, length = 20)
    private Long itbId;

    @Column(name = "item_id", nullable = false, length = 20)
    private Long item_id;        // Primary Key from 'itemdetails_tab' Table

    @Column(name = "batchcode", length = 100)
    private String batchcode;

    @Column(name = "item_stock", length = 100)
    private Long item_stock;
    @Column(name = "mrp_rate")
    private Long mrp_rate;

    @Column(name = "rate_a")
    private Long rate_a;

    @Column(name = "rate_b")
    private Long rate_b;

    @Column(name = "rate_c")
    private Long rate_c;

    @Column(name = "rate_d")
    private Long rate_d;

    @Column(name = "minsaleqty")
    private Long minsaleqty;

    @Column(name = "rateedit")
    private Long rateedit;

    @CreatedDate
    @Column(name = "createddate")
    private LocalDateTime createddate;

    @CreatedBy
    @Column(name = "createdby")
    private Long createdby;
}
