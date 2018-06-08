package ru.ohapegor.test.campaign.participants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ohapegor.test.campaign.editor.CampaignEditor;
import ru.siblion.crm.campaign.manager.api.CMParticipantsRestApi;
import ru.siblion.crm.campaign.manager.api.constants.CMRestEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestParticipantsEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.CMRestStatisticsEndpoints;
import ru.siblion.crm.campaign.manager.api.constants.QueryParams;
import ru.siblion.crm.campaign.manager.api.dto.ClientParticipationsRequest;
import ru.siblion.crm.campaign.manager.api.dto.ParticipationDTO;
import ru.siblion.crm.campaign.manager.api.dto.enums.Queues;
import ru.siblion.crm.campaign.manager.api.dto.enums.TransportStatus;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;
import ru.siblion.crm.campaign.manager.api.response.CallCardResponse;
import ru.siblion.crm.campaign.manager.api.response.ParticipationsListResponse;

import java.util.EnumSet;

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
    public ParticipationsListResponse getActiveCallRecords(EnumSet<Queues> enumSet) {
        String URL = UriComponentsBuilder.fromHttpUrl(CM_PARTICIPANTS_ENDPOINT + CMRestParticipantsEndpoints.GET_ACTIVE_CALLS)
                .build().toUri().toString();
        LOG.info(URL);
        return restTemplate
                .postForEntity(URL,enumSet,
                        ParticipationsListResponse.class).getBody();
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
