package com.newprocess.repositories;

import com.newprocess.api.CompanyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Chandra on 1/10/17.
 */
public interface CompanyDetailsRepository extends JpaRepository<CompanyDetails, Long>, JpaSpecificationExecutor<CompanyDetails> {
}
