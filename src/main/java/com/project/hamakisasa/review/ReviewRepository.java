package com.project.hamakisasa.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByTenant_Id(Long tenantId);

    List<Review> findByProperty_Id(Long propertyId);
}
