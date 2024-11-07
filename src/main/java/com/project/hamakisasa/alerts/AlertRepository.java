package com.project.hamakisasa.alerts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByUser_IdAndIsReadFalse(Long userId);

    List<Alert> findByUser_Id(Long userId);
}
