package ru.ohapegor.test.sources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.siblion.crm.source.api.ISourceOperations;

import static org.junit.jupiter.api.Assertions.*;

class ISourceOperationsClientTest {

    private ISourceOperations iSourceOperations = new ISourceOperationsClient();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createSource() {
    }

    @Test
    void findSourceById() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(1003L));
    }

    @Test
    void deleteSourceById() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(iSourceOperations.deleteSourceById(1003L)));
    }

    @Test
    void findAllSources() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(iSourceOperations.findAllSources()));
    }
}