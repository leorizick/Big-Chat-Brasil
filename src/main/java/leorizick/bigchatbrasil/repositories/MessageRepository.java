package leorizick.bigchatbrasil.repositories;

import leorizick.bigchatbrasil.entities.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
