package ru.ohapegor.test.sender.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.siblion.crm.sender.api.proposal.rest.SenderAdminRestApi;
import ru.siblion.crm.sender.api.proposal.rest.constants.AdminEndpoints;
import ru.siblion.crm.sender.api.proposal.rest.constants.PropRestEndpoints;
import ru.siblion.crm.sender.api.proposal.rest.response.GetAdminDataResponse;
import ru.siblion.crm.sender.api.proposal.rest.response.ProposalResponse;

public class Adminka implements SenderAdminRestApi {

    private static final Logger LOG = LoggerFactory.getLogger(Adminka.class);

    private static final String ADMIN_ENDPOINT = "http://localhost:8082/"
            + PropRestEndpoints.CONTEXT + '/' + PropRestEndpoints.ADMIN_CONTR + '/';


    private static final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ProposalResponse changeRestApiKey(String s) {
        String URL = UriComponentsBuilder.fromHttpUrl(ADMIN_ENDPOINT + AdminEndpoints.CHANGE_API_KEY)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        s,
                        ProposalResponse.class).getBody();
    }

    @Override
    public ProposalResponse changeSenderName(String s) {
        return null;
    }

    @Override
    public ProposalResponse changeSenderEmail(String s) {
        return null;
    }

    @Override
    public ProposalResponse changeDevinoSender(String s) {
        return null;
    }

    @Override
    public ProposalResponse changeDevinoLogin(String s) {
        return null;
    }

    @Override
    public ProposalResponse changeDevinoPassword(String s) {
        return null;
    }


    @Override
    public GetAdminDataResponse getAdminData() {
        String URL = UriComponentsBuilder.fromHttpUrl(ADMIN_ENDPOINT + AdminEndpoints.GET)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        GetAdminDataResponse.class).getBody();
    }
}
