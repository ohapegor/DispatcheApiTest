package ru.ohapegor.test.campaign.participants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.siblion.crm.campaign.manager.api.CMParticipantsRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestParticipantsEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.dto.BehavioralFilterRequest;
import ru.siblion.crm.campaign.manager.api.dto.participant.ParticipationDTO;
import ru.siblion.crm.campaign.manager.api.dto.participant.enums.Queues;
import ru.siblion.crm.campaign.manager.api.dto.participant.enums.TransportStatus;
import ru.siblion.crm.campaign.manager.api.request.ClientParticipationsRequest;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;
import ru.siblion.crm.campaign.manager.api.response.CallCardResponse;
import ru.siblion.crm.campaign.manager.api.response.ClientsListResponse;
import ru.siblion.crm.campaign.manager.api.response.ParticipationsListResponse;

import java.util.Set;

public class ParticipantsService implements CMParticipantsRestApi {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOG = LoggerFactory.getLogger(ParticipantsService.class);

    private static final String CM_PARTICIPANTS_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.PARTICIPANTS_CONTR + '/';


    @Override
    public ParticipationsListResponse getParticipants(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.GET_FOR_CAMPAIGN)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        ParticipationsListResponse.class).getBody();
    }

    @Override
    public ParticipationsListResponse getClientCallRecords(Long clientId, TransportStatus transportStatus) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.GET_CLIENT_CALLS)
                .queryParam(QueryParams.CLIENT_ID,clientId)
                .queryParam(QueryParams.TRANSPORT_STATUS,transportStatus)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        ParticipationsListResponse.class).getBody();
    }

    @Override
    public ParticipationsListResponse getClientParticipations(ClientParticipationsRequest clientPatricipationsRequest) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.GET_CLIENT_PARTICIPATIONS)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,clientPatricipationsRequest,
                        ParticipationsListResponse.class).getBody();
    }

    @Override
    public ClientsListResponse getParticipations(BehavioralFilterRequest fBehavioralFilterRequest) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.FILTER_PARTICIPATNS)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,fBehavioralFilterRequest,
                        ClientsListResponse.class).getBody();
    }

    @Override
    public ParticipationsListResponse getAllCallRecords(Set<Queues> set) {
        return null;
    }

    @Override
    public ParticipationsListResponse getActiveCallRecords(String user, Set<Queues> queues) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.EDIT)
                .queryParam(QueryParams.USER,user)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,queues,
                        ParticipationsListResponse.class).getBody();
    }

    @Override
    public CMResponse assignCallParticipatipationToUser(Long participationId, String user) {
        return null;
    }

    @Override
    public CMResponse unassignCallParticipatipationToUser(Long participationId) {
        return null;
    }



    @Override
    public CMResponse editParticipation(ParticipationDTO participationDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.EDIT)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,participationDTO,
                        ParticipationsListResponse.class).getBody();
    }

    @Override
    public CallCardResponse getCallCard(Long partId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.GET_CALL_CARD)
                .queryParam(QueryParams.PARTICIPATION_ID,partId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CallCardResponse.class).getBody();
    }

}
