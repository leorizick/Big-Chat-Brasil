package leorizick.bigchatbrasil.services.domain.message;

import jakarta.transaction.Transactional;
import leorizick.bigchatbrasil.entities.costumer.Costumer;
import leorizick.bigchatbrasil.entities.costumer.TypePlan;
import leorizick.bigchatbrasil.entities.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import leorizick.bigchatbrasil.repositories.MessageRepository;
import leorizick.bigchatbrasil.services.domain.costumer.CostumerCrud;
import leorizick.bigchatbrasil.configs.exceptions.OutOfFoundsException;

@Service
@RequiredArgsConstructor
public class MessageCrud {

    private final MessageRepository messageRepository;
    private final CostumerCrud costumerCrud;
    @Value("bigchatbrasil.message.cost")
    private final double cost;

    @Transactional
    public Message sendMessage(Long senderId, Long receiverId, Message message) {
        Costumer sender = costumerCrud.findById(senderId);
        Costumer receiver = costumerCrud.findById(receiverId);

        if (sender.getType().equals(TypePlan.PRE_PAGO)) {
            chargeAPrePagoMessage(sender);
        }

        if (sender.getType().equals(TypePlan.POS_PAGO)) {
            chargeAPosPagoMessage(sender);
        }
        message.setCost(cost);
        return messageRepository.save(message);
    }

    private void chargeAPrePagoMessage(Costumer sender) {
        if (sender.getBalance() < cost) {
            throw new OutOfFoundsException("Saldo indisponivel");
        }
        sender.setBalance(sender.getBalance() - cost);
        costumerCrud.save(sender);
    }

    private void chargeAPosPagoMessage(Costumer sender) {
        sender.setUsedLimit(sender.getUsedLimit() + cost);
        if (sender.getUsedLimit() < sender.getLimit()) {
            throw new OutOfFoundsException("Limite indisponivel");
        }
        costumerCrud.save(sender);
    }
}
