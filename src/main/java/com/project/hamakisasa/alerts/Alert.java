package com.project.hamakisasa.alerts;


import com.project.hamakisasa.users.User;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
@Data
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String message;

    @Enumerated(EnumType.STRING)
    private AlertType type;

    private boolean isRead = false;

    private LocalDateTime createdAt = LocalDateTime.now();
}

