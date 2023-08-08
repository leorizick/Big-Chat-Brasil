package leorizick.bigchatbrasil.entities.message;

import leorizick.bigchatbrasil.entities.costumer.Costumer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tell;
    private boolean whatsapp;
    private String message;
    private double cost;

    @OneToOne(mappedBy = "message")
    @JoinColumn(name = "sender_id")
    private Costumer sender;

    @OneToOne(mappedBy = "message")
    @JoinColumn(name = "receiver_id")
    private Costumer receiver;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
