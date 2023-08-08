package leorizick.bigchatbrasil.repositories;

import leorizick.bigchatbrasil.entities.costumer.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRepository extends JpaRepository<Costumer, Long> {
}
