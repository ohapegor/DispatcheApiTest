package ru.ohapegor.test.sender.proposal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.siblion.crm.sender.api.proposal.rest.ProposalRestApi;
import ru.siblion.crm.sender.api.proposal.rest.constants.PropRestEndpoints;
import ru.siblion.crm.sender.api.proposal.rest.constants.QueryParams;
import ru.siblion.crm.sender.api.proposal.rest.dto.ProposalDTO;
import ru.siblion.crm.sender.api.proposal.rest.response.CreateEntityResponse;
import ru.siblion.crm.sender.api.proposal.rest.response.GetAllProposalsResponse;
import ru.siblion.crm.sender.api.proposal.rest.response.GetAttachmentByIdResponse;
import ru.siblion.crm.sender.api.proposal.rest.response.GetProposalByIdResponse;
import ru.siblion.crm.sender.api.proposal.rest.response.ProposalResponse;

public class ProposalService implements ProposalRestApi {

    private static final Logger LOG = LoggerFactory.getLogger(ProposalService.class);

    private static final String PROP_ENDPOINT = "http://localhost:8082/"
            + PropRestEndpoints.CONTEXT + '/' + PropRestEndpoints.PROPOSAL_CONTR + '/';


    private static final RestTemplate restTemplate = new RestTemplate();


    @Override
    public CreateEntityResponse create(ProposalDTO proposalDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(PROP_ENDPOINT + PropRestEndpoints.CREATE)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        proposalDTO,
                        CreateEntityResponse.class).getBody();
    }

    @Override
    public ProposalResponse deleteById(Long aLong) {
        String URL = UriComponentsBuilder.fromHttpUrl(PROP_ENDPOINT + PropRestEndpoints.DELETE_BY_ID)
                .queryParam(QueryParams.PROP_ID, aLong)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .exchange(URL, HttpMethod.DELETE, null,
                        ProposalResponse.class).getBody();
    }

    @Override
    public ProposalResponse edit(ProposalDTO proposalDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(PROP_ENDPOINT + PropRestEndpoints.EDIT)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,
                        proposalDTO,
                        ProposalResponse.class).getBody();
    }

    @Override
    public GetAllProposalsResponse getAll(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(PROP_ENDPOINT + PropRestEndpoints.GET_ALL)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        GetAllProposalsResponse.class).getBody();
    }

    @Override
    public GetProposalByIdResponse getById(Long aLong) {
        String URL = UriComponentsBuilder.fromHttpUrl(PROP_ENDPOINT + PropRestEndpoints.GET_BY_ID)
                .queryParam(QueryParams.PROP_ID, aLong)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        GetProposalByIdResponse.class).getBody();
    }

    @Override
    public GetAttachmentByIdResponse getAttachmentById(Long aLong) {
        String URL = UriComponentsBuilder.fromHttpUrl(PROP_ENDPOINT + PropRestEndpoints.GET_ATT_BY_ID)
                .queryParam(QueryParams.ATT_ID, aLong)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        GetAttachmentByIdResponse.class).getBody();
    }

    @Override
    public ProposalResponse deleteAttachmentById(Long aLong) {
        String URL = UriComponentsBuilder.fromHttpUrl(PROP_ENDPOINT + PropRestEndpoints.DELETE_ATT_BY_ID)
                .queryParam(QueryParams.ATT_ID, aLong)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .exchange(URL, HttpMethod.DELETE, null,
                        ProposalResponse.class).getBody();
    }

    @Override
    public CreateEntityResponse assignProposalToCampaign(Long propId, Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(PROP_ENDPOINT + PropRestEndpoints.ASSIGN_CAMP)
                .queryParam(QueryParams.PROP_ID, propId)
                .queryParam(QueryParams.CAMP_ID, campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL, null,
                        CreateEntityResponse.class).getBody();
    }

}
