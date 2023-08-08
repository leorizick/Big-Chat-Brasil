package entities.message;

import entities.costumer.Costumer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

}
