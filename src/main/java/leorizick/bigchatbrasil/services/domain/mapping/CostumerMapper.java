package leorizick.bigchatbrasil.services.domain.mapping;

import leorizick.bigchatbrasil.DTO.CostumerCreationRequest;
import leorizick.bigchatbrasil.DTO.CostumerPatchRequest;
import leorizick.bigchatbrasil.entities.costumer.Costumer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CostumerMapper {

    private final ModelMapper modelMapper;

    @PostConstruct
    public void configure() {
        createFromCostumerCreationRequest();
        createFromCostumerPatchRequest();
    }

    private void createFromCostumerCreationRequest() {
        modelMapper.createTypeMap(CostumerCreationRequest.class, Costumer.class)
                .setConverter(mappingContext -> {
                    var src = mappingContext.getSource();
                    var costumer = mappingContext.getDestination();

                    if (costumer == null) {
                        costumer = new Costumer();
                        costumer.setEnabled(true);
                    }
                    costumer.setBalance(src.getBalance());
                    costumer.setCnpj(src.getCnpj());
                    costumer.setCpf(src.getCpf());
                    costumer.setCompanyName(src.getCompanyName());
                    costumer.setTelephone(src.getTelephone());
                    costumer.setLimit(src.getLimit());
                    return costumer;

                });
    }

    private void createFromCostumerPatchRequest() {
        modelMapper.createTypeMap(CostumerPatchRequest.class, Costumer.class)
                .setConverter(mappingContext -> {
                    var src = mappingContext.getSource();
                    var costumer = mappingContext.getDestination();

                    costumer.setEnabled(true);

                    if(src.getBalance() != null){
                        costumer.setBalance(src.getBalance());
                    }
                    if(src.getCnpj() != null){
                        costumer.setCnpj(src.getCnpj());
                    }
                    if(src.getCpf() != null){
                        costumer.setCpf(src.getCpf());
                    }
                    if(src.getCompanyName() != null){
                        costumer.setCompanyName(src.getCompanyName());
                    }
                    if(src.getTelephone() != null){
                        costumer.setTelephone(src.getTelephone());
                    }
                    if(src.getLimit() != null){
                        costumer.setLimit(src.getLimit());
                    }
                    return costumer;

                });
    }
}
