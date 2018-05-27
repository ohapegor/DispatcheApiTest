package ru.ohapegor.test.sender.dispatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.siblion.crm.sender.api.dispatch.DispatchApi;
import ru.siblion.crm.sender.api.dispatch.constants.DispatchRestEndpoints;
import ru.siblion.crm.sender.api.dispatch.request.DispatchSendRequest;
import ru.siblion.crm.sender.api.dispatch.request.DispatchStatusRequest;
import ru.siblion.crm.sender.api.dispatch.response.DispatchSendResponse;
import ru.siblion.crm.sender.api.dispatch.response.DispatchStatusResponse;

public class Dispatcher implements DispatchApi {

    private static final Logger LOG = LoggerFactory.getLogger(Dispatcher.class);

    private static final String DISPATCH_ENDPOINT = "http://localhost:8082/"
            + DispatchRestEndpoints.CONTEXT + '/' + DispatchRestEndpoints.DISPATCH_CONTR + '/';


    private static final RestTemplate restTemplate = new RestTemplate();

    @Override
    public DispatchSendResponse send(DispatchSendRequest request) {
        String URL = UriComponentsBuilder.fromHttpUrl(DISPATCH_ENDPOINT + DispatchRestEndpoints.SEND)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        request,
                        DispatchSendResponse.class).getBody();
    }

    @Override
    public DispatchStatusResponse result(DispatchStatusRequest request) {
        String URL = UriComponentsBuilder.fromHttpUrl(DISPATCH_ENDPOINT + DispatchRestEndpoints.RECIEVE)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        request,
                        DispatchStatusResponse.class).getBody();
    }
}
