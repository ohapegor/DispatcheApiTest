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
import ru.siblion.crm.campaign.manager.api.request.GetCallRecordsRequest;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;

import java.util.List;
import java.util.Set;

public class ParticipantsService implements CMParticipantsRestApi {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOG = LoggerFactory.getLogger(ParticipantsService.class);

    private static final String CM_PARTICIPANTS_ENDPOINT = "http://localhost:8081/"
            + CMRestEndpoints.CONTEXT + '/' + CMRestEndpoints.PARTICIPANTS_CONTR + '/';


    @Override
    public CMResponse getCampaignParticipations(Long campId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.GET_FOR_CAMPAIGN)
                .queryParam(QueryParams.CAMP_ID,campId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse getClientCallRecords(Long clientId, TransportStatus transportStatus) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.GET_CLIENT_CALLS)
                .queryParam(QueryParams.CLIENT_ID,clientId)
                .queryParam(QueryParams.TRANSPORT_STATUS,transportStatus)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse getClientParticipations(ClientParticipationsRequest clientPatricipationsRequest) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.GET_CLIENT_PARTICIPATIONS)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,clientPatricipationsRequest,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<List<Long>> getParticipations(BehavioralFilterRequest fBehavioralFilterRequest) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.FILTER_PARTICIPATNS)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,fBehavioralFilterRequest,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse<List<ParticipationDTO>> getAllCallRecords(GetCallRecordsRequest getCallRecordsRequest) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.GET_ALL_CALLS)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,getCallRecordsRequest,
                        CMResponse.class).getBody();
    }


    @Override
    public CMResponse getActiveCallRecords(String user, GetCallRecordsRequest request) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.EDIT)
                .queryParam(QueryParams.USER,user)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,request,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse assignCallCardToUser(Long partId, String user) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.ASSIGN_CARD_TO_USER)
                .queryParam(QueryParams.USER,user)
                .queryParam(QueryParams.PARTICIPATION_ID,partId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse unassignCallCardToUser(Long partId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.UNASSIGN_CARD)
                .queryParam(QueryParams.PARTICIPATION_ID,partId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }


    @Override
    public CMResponse editParticipation(ParticipationDTO participationDTO) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.EDIT)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,participationDTO,
                        CMResponse.class).getBody();
    }

    @Override
    public CMResponse getCallCard(Long partId) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.GET_CALL_CARD)
                .queryParam(QueryParams.PARTICIPATION_ID,partId)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .getForEntity(URL,
                        CMResponse.class).getBody();
    }

}
