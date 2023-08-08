package services.domain.services.costumer;

import entities.costumer.Costumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repositories.CostumerRepository;
import services.exceptions.NotFoundException;

@Service
@RequiredArgsConstructor
public class CostumerCrud {

    private final CostumerRepository costumerRepository;

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
