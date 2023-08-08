package leorizick.bigchatbrasil.services.domain.message;

import jakarta.transaction.Transactional;
import leorizick.bigchatbrasil.entities.costumer.Costumer;
import leorizick.bigchatbrasil.entities.costumer.TypePlan;
import leorizick.bigchatbrasil.entities.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import leorizick.bigchatbrasil.repositories.MessageRepository;
import leorizick.bigchatbrasil.services.domain.costumer.CostumerCrud;
import leorizick.bigchatbrasil.configs.exceptions.OutOfCreditsException;

@Service
@RequiredArgsConstructor
public class MessageCrud {

    private final MessageRepository messageRepository;
    private final CostumerCrud costumerCrud;

    private final double cost = 0.25;

    @Transactional
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
