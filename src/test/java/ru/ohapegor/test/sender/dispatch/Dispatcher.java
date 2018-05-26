package ru.ohapegor.test.sender.dispatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ohapegor.test.sender.proposal.ProposalService;
import ru.siblion.crm.sender.api.dispatch.DispatchApi;
import ru.siblion.crm.sender.api.dispatch.constants.DispatchRestEndpoints;
import ru.siblion.crm.sender.api.dispatch.request.DispatchResultRequest;
import ru.siblion.crm.sender.api.dispatch.request.DispatchSendRequest;
import ru.siblion.crm.sender.api.dispatch.response.DispatchResultResponse;
import ru.siblion.crm.sender.api.dispatch.response.DispatchSendResponse;
import ru.siblion.crm.sender.api.proposal.rest.constants.PropRestEndpoints;

public class Dispatcher implements DispatchApi {

    private static final Logger LOG = LoggerFactory.getLogger(Dispatcher.class);

    private static final String PROP_ENDPOINT = "http://localhost:8082/"
            + DispatchRestEndpoints.CONTEXT + '/' + DispatchRestEndpoints.DISPATCH_CONTR + '/';


    private static final RestTemplate restTemplate = new RestTemplate();

    @Override
    public DispatchSendResponse send(DispatchSendRequest dispatchSendRequest) {
        String URL = UriComponentsBuilder.fromHttpUrl(PROP_ENDPOINT + PropRestEndpoints.CREATE)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        dispatchSendRequest,
                        DispatchSendResponse.class).getBody();
    }

    @Override
    public DispatchResultResponse result(DispatchResultRequest dispatchResultRequest) {
        throw new UnsupportedOperationException();
    }
}
