package leorizick.bigchatbrasil.services.domain.costumer;

import io.restassured.RestAssured;
import leorizick.bigchatbrasil.entities.costumer.Costumer;
import leorizick.bigchatbrasil.repositories.CostumerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class CostumerCrudTest {

    private static final Long ANY_ID = 1L;

    private CostumerRepository costumerRepository;
    private CostumerCrud costumerCrud;

    @BeforeEach()
    public void setUp() {
        costumerRepository = Mockito.mock(CostumerRepository.class);
        costumerCrud = new CostumerCrud(costumerRepository);
    }

    @Test
    public void shouldRegistryCostumerAsDeleted() {
        Costumer expected = Costumer.builder()
                .id(ANY_ID)
                .enabled(true)
                .build();

        Mockito.when(costumerRepository.findById(ANY_ID))
                .thenReturn(Optional.of(expected));

        Mockito.when(costumerRepository.save(expected)).thenAnswer(context -> {
            Costumer arg = context.getArgument(0, Costumer.class);
            Assertions.assertFalse(arg.isEnabled());
            Assertions.assertEquals(arg.getId(), ANY_ID);
            return expected;
        });

        costumerCrud.delete(ANY_ID);
        Mockito.verify(costumerRepository, Mockito.times(1)).findById(ANY_ID);
        Mockito.verify(costumerRepository, Mockito.times(1)).save(expected);
    }

}