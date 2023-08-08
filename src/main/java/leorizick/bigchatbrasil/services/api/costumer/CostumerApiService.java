package leorizick.bigchatbrasil.services.api.costumer;

import leorizick.bigchatbrasil.DTO.CostumerCreationRequest;
import leorizick.bigchatbrasil.DTO.CostumerPatchRequest;
import leorizick.bigchatbrasil.DTO.CostumerResponse;
import leorizick.bigchatbrasil.entities.costumer.Costumer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import leorizick.bigchatbrasil.services.domain.costumer.CostumerCrud;

@Service
@RequiredArgsConstructor
public class CostumerApiService {
    /*A injeção de dependencia tambem poderia ser feita utilizado a anotação @AutoWired. Porem desta forma facilita na utilizacao
     para criacao de testes, alem da VM otimizar o uso das variaveis final*/
    private final CostumerCrud costumerCrud;
    private final ModelMapper modelMapper;

    public CostumerResponse findById(Long id){
        Costumer costumer = costumerCrud.findById(id);
        return modelMapper.map(costumer, CostumerResponse.class);
    }

    @Transactional
    public CostumerResponse create(CostumerCreationRequest costumerCreationRequest){
        Costumer costumer = modelMapper.map(costumerCreationRequest, Costumer.class);
        costumer = costumerCrud.save(costumer);
        return modelMapper.map(costumer, CostumerResponse.class);
    }

    @Transactional
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

    /*Geralmente costumo usar o PUT para atualizar a entidade, porem como foram solicitados a opção de alterar varios camps
    especificos no projeto, resolvi utilizar o patch para seguir a didatica*/
    @Transactional
    public CostumerResponse patch(Long id, CostumerPatchRequest costumerPatchRequest) {
        Costumer costumer = costumerCrud.findById(id);
        modelMapper.map(costumerPatchRequest, costumer);
        costumer = costumerCrud.save(costumer);
        return modelMapper.map(costumer, CostumerResponse.class);
    }
}
