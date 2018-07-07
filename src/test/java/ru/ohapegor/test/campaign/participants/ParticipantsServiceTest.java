package ru.ohapegor.test.campaign.participants;

import org.junit.jupiter.api.Test;
import ru.siblion.crm.campaign.manager.api.dto.BehavioralFilterRequest;
import ru.siblion.crm.campaign.manager.api.dto.call.CallRecordDTO;
import ru.siblion.crm.campaign.manager.api.dto.campaign.enums.CampaignStatus;
import ru.siblion.crm.campaign.manager.api.dto.campaign.enums.ChannelType;
import ru.siblion.crm.campaign.manager.api.dto.filter.BehavioralFieldsEnum;
import ru.siblion.crm.campaign.manager.api.dto.filter.BehavioralFilter;
import ru.siblion.crm.campaign.manager.api.dto.filter.BehavioralPredicate;
import ru.siblion.crm.campaign.manager.api.dto.filter.ConditionsEnum;
import ru.siblion.crm.campaign.manager.api.dto.filter.StringRelationsEnum;
import ru.siblion.crm.campaign.manager.api.dto.participant.ClientParticipationsRequestBuilder;
import ru.siblion.crm.campaign.manager.api.dto.participant.ParticipationDTO;
import ru.siblion.crm.campaign.manager.api.dto.participant.enums.Queues;
import ru.siblion.crm.campaign.manager.api.dto.participant.enums.RespondType;
import ru.siblion.crm.campaign.manager.api.dto.participant.enums.TransportStatus;
import ru.siblion.crm.campaign.manager.api.request.ClientParticipationsRequest;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

class ParticipantsServiceTest {

    private ParticipantsService service = new ParticipantsService();

    private Long campId = 100000L;
    private Long clientId = 777L;
    private Long clientId2 = 60006200L;
    private Long clientId3 = 990000188L;
    private Long participationId = 100012L;

    @Test
    void getParticipants() {
        System.out.println(service.getParticipants(campId));
    }


    @Test
    void getClientCallRecords() {
        System.out.println(service.getClientCallRecords(clientId, null));
    }

    @Test
    void getActiveCallRecords() {
        System.out.println(service.getActiveCallRecords("",EnumSet.of(Queues.NEKRASOVKA)));
    }

    @Test
    void editParticipation() {
        ParticipationDTO participationDTO = new ParticipationDTO();
        participationDTO.setId(participationId);
        CallRecordDTO callRecordDTO = new CallRecordDTO();
        callRecordDTO.setAppointedTo("trololo");
        callRecordDTO.setVoiced(true);
        callRecordDTO.setComment("olala");
        callRecordDTO.setCallDate(ZonedDateTime.now());
        participationDTO.setCallRecord(callRecordDTO);
        participationDTO.setTransportStatus(TransportStatus.DELIVERED);
        participationDTO.setRespondType(RespondType.INTERESTED);
        System.out.println(service.editParticipation(participationDTO));
    }

    @Test
    void getCallCard() {
        System.out.println(service.getCallCard(participationId));
    }

    @Test
    void clientParticipations(){
        ClientParticipationsRequest request = ClientParticipationsRequestBuilder.forClients(Arrays.asList(clientId,clientId2,clientId3))
                .withChannels(EnumSet.of(ChannelType.CALL))
                .withRespondTypes(EnumSet.of(RespondType.NOT_RECEIVED))
                .withTransportStatuses(EnumSet.of(TransportStatus.REQUESTED_TO_SEND))
                .after(ZonedDateTime.now().minusWeeks(2))
                .build();
        System.out.println(service.getClientParticipations(request));

    }

    @Test
    void filter(){
        BehavioralPredicate p1 = new BehavioralPredicate();
        p1.setNextConditional(ConditionsEnum.OR);
        p1.setOperand(CampaignStatus.RUNNING.name());
        p1.setRelation(StringRelationsEnum.EQUALS_TO.value());
        p1.setBehavioralField(BehavioralFieldsEnum.CAMPAIGN_STATUS);

        BehavioralPredicate p2 = new BehavioralPredicate();
        p2.setNextConditional(ConditionsEnum.OR);
        p2.setOperand(RespondType.DELIVERED.name());
        p2.setRelation(StringRelationsEnum.EQUALS_TO.value());
        p2.setBehavioralField(BehavioralFieldsEnum.TRANSPORT_STATUS);

        BehavioralFilter filter = new BehavioralFilter();
        filter.setPredicates(Arrays.asList(p1,p2));

        List<Long> ids = Arrays.asList(20000288L,40000389L,990000188L,60006200L);
        BehavioralFilterRequest request = new BehavioralFilterRequest();
        request.setClientIds(ids);
        request.setFilter(filter);

        System.out.println("service.getParticipations(request) = " + service.getParticipations(request));
    }
}