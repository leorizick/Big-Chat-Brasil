package leorizick.bigchatbrasil.services.domain.mapping;

import leorizick.bigchatbrasil.DTO.CostumerResponse;
import leorizick.bigchatbrasil.entities.costumer.Costumer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CostumerResponseMapper {

    private final ModelMapper modelMapper;

    @PostConstruct
    public void configure() {
        createFromCostumer();
    }

    private void createFromCostumer() {
        modelMapper.createTypeMap(Costumer.class, CostumerResponse.class)
                .setConverter(mappingContext -> {
                    var src = mappingContext.getSource();

                    return CostumerResponse.builder()
                            .id(src.getId())
                            .balance(src.getBalance())
                            .cnpj(src.getCnpj())
                            .companyName(src.getCompanyName())
                            .cpf(src.getCpf())
                            .telephone(src.getTelephone())
                            .limit(src.getLimit())
                            .build();
                });
    }
}
