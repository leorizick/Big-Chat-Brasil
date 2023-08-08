package leorizick.bigchatbrasil.services.domain.costumer;

import jakarta.transaction.Transactional;
import leorizick.bigchatbrasil.entities.costumer.Costumer;
import leorizick.bigchatbrasil.configs.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import leorizick.bigchatbrasil.repositories.CostumerRepository;

@Service
@RequiredArgsConstructor
public class CostumerCrud {

    private final CostumerRepository costumerRepository;

    @Transactional
    public Costumer save(Costumer costumer){
        return costumerRepository.save(costumer);
    }

    public Costumer findById(Long id){
        return costumerRepository.findById(id).orElseThrow(() -> new NotFoundException("Costumer not found" + id));
    }

    public void delete(Long id){
        Costumer costumer = findById(id);
        costumer.setEnabled(false);
        costumerRepository.save(costumer);
    }
}
