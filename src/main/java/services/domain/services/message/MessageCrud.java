package services.domain.services.message;

import entities.DTO.MessageCreationRequest;
import entities.costumer.Costumer;
import entities.costumer.enums.TypePlan;
import entities.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.MessageRepository;
import services.domain.services.costumer.CostumerCrud;
import services.exceptions.OutOfCreditsException;

@Service
@RequiredArgsConstructor
public class MessageCrud {

    private final MessageRepository messageRepository;
    private final CostumerCrud costumerCrud;

    private final double cost = 0.25;

    public Message sendMessage(Long senderId, Long receiverId, Message message) {
        Costumer sender = costumerCrud.findById(senderId);
        Costumer receiver = costumerCrud.findById(receiverId);

        if (sender.getType().equals(TypePlan.PRE_PAGO)) {
            if (sender.getBalance() < cost) {
                throw new OutOfCreditsException("Saldo indisponivel");
            }
            sender.setBalance(sender.getBalance() - cost);
            costumerCrud.save(sender);
        }

        if (sender.getType().equals(TypePlan.POS_PAGO)) {
            sender.setBalance(sender.getBalance() + cost);
            if (sender.getBalance() < sender.getLimit()) {
                throw new OutOfCreditsException("Limite indisponivel");
            }
            costumerCrud.save(receiver);
        }
        message.setCost(cost);
        return messageRepository.save(message);
    }
}
