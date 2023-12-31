package leorizick.bigchatbrasil.web;

import leorizick.bigchatbrasil.DTO.CostumerCreationRequest;
import leorizick.bigchatbrasil.DTO.CostumerPatchRequest;
import leorizick.bigchatbrasil.DTO.CostumerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import leorizick.bigchatbrasil.services.api.costumer.CostumerApiService;

@RestController
@RequiredArgsConstructor
public class CostumerController {

    private final CostumerApiService costumerApiService;

    @GetMapping(value = "/api/costumer/{id}")
    public ResponseEntity<CostumerResponse> findById(@PathVariable Long id) {
        CostumerResponse costumerResponse = costumerApiService.findById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(costumerResponse);
    }

    @GetMapping(value = "/api/costumers")
    public ResponseEntity<Page<CostumerResponse>> findAll(Pageable pageable) {
        Page<CostumerResponse> recipePage = costumerApiService.findAll(pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recipePage);
    }

    @PostMapping(value = "/api/costumer")
    public ResponseEntity<CostumerResponse> create(@RequestBody CostumerCreationRequest costumerCreationRequest) {
        CostumerResponse costumerResponse = costumerApiService.create(costumerCreationRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(costumerResponse);
    }

    @PutMapping(value = "/api/costumer/{id}")
    public ResponseEntity<CostumerResponse> update(@RequestBody CostumerCreationRequest costumerCreationRequest, @PathVariable Long id) {
        CostumerResponse costumerResponse = costumerApiService.update(id, costumerCreationRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(costumerResponse);
    }

    @PatchMapping(value = "/api/costumer/{id}")
    public ResponseEntity<CostumerResponse> patch(@RequestBody CostumerPatchRequest costumerPatchRequest, @PathVariable Long id){
        CostumerResponse costumerResponse = costumerApiService.patch(id,costumerPatchRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(costumerResponse);
    }

    @DeleteMapping(value = "/api/costumer/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        costumerApiService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
