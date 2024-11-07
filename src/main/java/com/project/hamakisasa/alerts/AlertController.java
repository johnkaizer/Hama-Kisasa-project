package com.project.hamakisasa.alerts;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Alert>> getAlertsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(alertService.getAlertsForUser(userId));
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<Alert>> getUnreadAlertsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(alertService.getUnreadAlertsForUser(userId));
    }

    @PostMapping
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) {
        Alert createdAlert = alertService.createAlert(alert);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlert);
    }

    @PutMapping("/{alertId}/read")
    public ResponseEntity<Alert> markAlertAsRead(@PathVariable Long alertId) {
        Alert updatedAlert = alertService.markAsRead(alertId);
        return ResponseEntity.ok(updatedAlert);
    }

    @DeleteMapping("/{alertId}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long alertId) {
        alertService.deleteAlert(alertId);
        return ResponseEntity.noContent().build();
    }
}
