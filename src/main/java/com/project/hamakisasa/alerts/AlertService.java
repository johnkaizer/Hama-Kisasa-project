package com.project.hamakisasa.alerts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;

    public List<Alert> getAlertsForUser(Long userId) {
        return alertRepository.findByUser_Id(userId);
    }

    public List<Alert> getUnreadAlertsForUser(Long userId) {
        return alertRepository.findByUser_IdAndIsReadFalse(userId);
    }

    public Alert createAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    public Alert markAsRead(Long alertId) {
        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new RuntimeException("Alert not found"));
        alert.setRead(true);
        return alertRepository.save(alert);
    }

    public void deleteAlert(Long alertId) {
        alertRepository.deleteById(alertId);
    }
}

