package ru.ohapegor.test.campaign.participants;

import org.junit.jupiter.api.Test;
import ru.siblion.crm.campaign.manager.api.dto.ClientParticipationsRequest;
import ru.siblion.crm.campaign.manager.api.dto.ParticipationDTO;
import ru.siblion.crm.campaign.manager.api.dto.builder.ClientParticipationsRequestBuilder;
import ru.siblion.crm.campaign.manager.api.dto.call.CallRecordDTO;
import ru.siblion.crm.campaign.manager.api.dto.enums.ChannelType;
import ru.siblion.crm.campaign.manager.api.dto.enums.Queues;
import ru.siblion.crm.campaign.manager.api.dto.enums.RespondType;
import ru.siblion.crm.campaign.manager.api.dto.enums.TransportStatus;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.EnumSet;

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
        System.out.println(service.getActiveCallRecords(EnumSet.of(Queues.NEKRASOVKA)));
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
}