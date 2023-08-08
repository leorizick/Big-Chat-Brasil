package services.api.services.costumer;

import entities.DTO.CostumerCreationRequest;
import entities.DTO.CostumerResponse;
import entities.costumer.Costumer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import services.domain.services.costumer.CostumerCrud;

@Service
@RequiredArgsConstructor
public class CostumerApiService {

    private final CostumerCrud costumerCrud;
    private final ModelMapper modelMapper;


    public CostumerResponse findById(Long id){
        Costumer costumer = costumerCrud.findById(id);
        return modelMapper.map(costumer, CostumerResponse.class);
    }

    public CostumerResponse create(CostumerCreationRequest costumerCreationRequest){
        Costumer costumer = modelMapper.map(costumerCreationRequest, Costumer.class);
        costumer = costumerCrud.save(costumer);
        return modelMapper.map(costumer, CostumerResponse.class);
    }

    public CostumerResponse update(Long id, CostumerCreationRequest costumerCreationRequest){
        Costumer costumer = costumerCrud.findById(id);
        modelMapper.map(costumerCreationRequest, costumer);
        costumer = costumerCrud.save(costumer);
        return modelMapper.map(costumer, CostumerResponse.class);
    }

    @Transactional
    public void delete(Long id){
        costumerCrud.delete(id);
    }
}
