package ru.ohapegor.test.sources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.siblion.crm.source.api.ISourceOperations;
import ru.siblion.crm.source.api.model.CreateSourceRq;
import ru.siblion.crm.source.api.model.CreateSourceRs;
import ru.siblion.crm.source.api.model.DeleteSourceRs;
import ru.siblion.crm.source.api.model.FindSourceRs;

/**
 * @author Valeri Kirichenko.
 */
@Component
public class ISourceOperationsClient implements ISourceOperations {

    private RestTemplate restTemplate = new RestTemplate();


    @Override
    public CreateSourceRs createSource(CreateSourceRq createSourceRq) {
        HttpEntity<CreateSourceRq> httpEntity = new HttpEntity<>(createSourceRq);
        ResponseEntity<CreateSourceRs> responseEntity =
                restTemplate.exchange(
                        "http://localhost:8080/sources/create",
                        HttpMethod.POST,
                        httpEntity,
                        CreateSourceRs.class
                );
        return responseEntity.getBody();
    }

    @Override
    public FindSourceRs findSourceById(Long aLong) {
        return restTemplate.getForObject(
                "http://localhost:8080/sources/find/" + aLong,
                FindSourceRs.class
        );
    }

    @Override
    public DeleteSourceRs deleteSourceById(Long aLong) {
        ResponseEntity<DeleteSourceRs> responseEntity =
                restTemplate.exchange(
                        "http://localhost:8080/sources/delete/" + aLong,
                        HttpMethod.DELETE,
                        null,
                        DeleteSourceRs.class
                );
        return responseEntity.getBody();

    }

    @Override
    public FindSourceRs findAllSources() {
        return restTemplate.getForObject(
                "http://localhost:8080/sources/findAll",
                FindSourceRs.class
        );
    }
}
