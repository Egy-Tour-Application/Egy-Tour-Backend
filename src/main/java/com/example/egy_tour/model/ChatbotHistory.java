package com.example.egy_tour.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "chatbot_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatbotHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "msg_content", nullable = false, columnDefinition = "TEXT")
    private String msgContent;

    @Column(name = "flag", nullable = false)
    private boolean flag;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time", nullable = false)
    private Date time;
}
