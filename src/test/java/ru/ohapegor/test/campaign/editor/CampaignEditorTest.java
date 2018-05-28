package ru.ohapegor.test.campaign.editor;

import org.junit.jupiter.api.Test;
import ru.siblion.crm.campaign.manager.api.dto.CampaignDTO;
import ru.siblion.crm.campaign.manager.api.dto.MappingDTO;
import ru.siblion.crm.campaign.manager.api.dto.MappingFieldDTO;
import ru.siblion.crm.campaign.manager.api.dto.ResultDataDTO;
import ru.siblion.crm.campaign.manager.api.dto.builder.CampaignDTOBuilder;
import ru.siblion.crm.campaign.manager.api.dto.enums.ChannelType;
import ru.siblion.crm.campaign.manager.api.dto.enums.MappingDataType;
import ru.siblion.crm.campaign.manager.api.dto.enums.MappingFieldType;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.UUID;

import static ru.siblion.crm.campaign.manager.api.dto.enums.SystemFields.CLIENT_ID;
import static ru.siblion.crm.campaign.manager.api.dto.enums.SystemFields.EMAIL;
import static ru.siblion.crm.campaign.manager.api.dto.enums.SystemFields.PHONE;

class CampaignEditorTest {

    private static final CampaignEditor campaignEditor = new CampaignEditor();

    private static final Long campId = 100000L;

    @Test
    void createCampaign() {
        System.out.println(campaignEditor.createCampaign(testCampaign()));
    }

    @Test
    void editCampaign() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getById() {
        System.out.println(campaignEditor.getById(100000L));
    }

    @Test
    void deleteById() {
    }

    @Test
    void assignAudience() {
        System.out.println(campaignEditor.assignAudience(campId,87L));
    }

    @Test
    void unassignAudience() {
    }

    @Test
    void assignSource() {
        MappingFieldDTO id = new MappingFieldDTO(
                "PCODE",
                MappingFieldType.SYSTEM_FIELD,
                new ResultDataDTO(CLIENT_ID.name(),
                        MappingDataType.INTEGER), null);
        MappingFieldDTO phone = new MappingFieldDTO(
                "PHONE",
                MappingFieldType.SYSTEM_FIELD,
                new ResultDataDTO(PHONE.name(),
                        MappingDataType.STRING), null);
        MappingFieldDTO email = new MappingFieldDTO(
                "CLMAIL",
                MappingFieldType.SYSTEM_FIELD,
                new ResultDataDTO(EMAIL.name(),
                        MappingDataType.STRING), null);
        MappingFieldDTO name = new MappingFieldDTO(
                "F_NAME",
                MappingFieldType.USER_FIELD,
                new ResultDataDTO("FirstName",
                        MappingDataType.STRING), null);
        MappingFieldDTO color = new MappingFieldDTO(
                "любимый цвет",
                MappingFieldType.USER_FIELD,
                new ResultDataDTO("Color",
                        MappingDataType.STRING), null);

        System.out.println(campaignEditor.assignSource(campId,
                new MappingDTO(19L, Arrays.asList(id, phone, email, name, color))));
    }

    @Test
    void unassignSource() {
        System.out.println(campaignEditor.unassignSource(campId));
    }

    @Test
    void assignProposal() {
        System.out.println(campaignEditor.assignProposal(campId, 100000L, ChannelType.EMAIL));
    }

    @Test
    void unassignProposal() {
        System.out.println(campaignEditor.unassignProposal(campId));
    }

    private CampaignDTO testCampaign() {
        return new CampaignDTOBuilder()
                .name(String.format("TestCampaign[%s]", UUID.randomUUID().toString()))
                .startDate(ZonedDateTime.now().plusWeeks(1))
                .endDate(ZonedDateTime.now().plusMonths(2))
                .startResponseCollectDate(ZonedDateTime.now().plusMonths(1))
                .endResponseCollectDate(ZonedDateTime.now().plusMonths(1).plusWeeks(1))
                .channel(ChannelType.EMAIL)
                .createdBy("egor")
                .proposalId(777L)
                .language("ru")
                .build();
    }
}