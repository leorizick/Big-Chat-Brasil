package leorizick.bigchatbrasil.repositories;

import leorizick.bigchatbrasil.entities.costumer.Costumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CostumerRepository extends JpaRepository<Costumer, Long> {
    @Override
    @Query("select c from Costumer c where enabled = true")
    Page<Costumer> findAll(Pageable pageable);
}
