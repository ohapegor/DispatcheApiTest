package ru.ohapegor.test.campaign;

import org.junit.jupiter.api.Test;
import ru.siblion.crm.campaign.manager.api.dto.CampaignDTO;
import ru.siblion.crm.campaign.manager.api.dto.builder.CampaignDTOBuilder;
import ru.siblion.crm.campaign.manager.api.dto.enums.*;
import ru.siblion.crm.campaign.manager.api.response.ResponseStatus;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

class CampaignEditorTest {

    private static final CampaignEditor CM = new CampaignEditor();

    @Test
    void createCampaign() {
        System.out.println(CM.createCampaign(testCampaign()));
    }

    @Test
    void editCampaign() {
        int success = 0;
        for (int i = 0; i <1000 ; i++) {
            CampaignDTO campaignDTO = testCampaign();
            campaignDTO.setId(100000L);
            campaignDTO.setName(String.format("new name[%s]",UUID.randomUUID().toString()));
            if (CM.editCampaign(campaignDTO).getStatus() == ResponseStatus.SUCCESS){
                success++;
            };
        }
        System.out.println("success = " + success);

    }

    @Test
    void getAll() {
        System.out.println(CM.getAll());
    }

    @Test
    void getById() {
        System.out.println(CM.getById(100001L));
    }

    @Test
    void deleteById() {
    }

    @Test
    void assignAudience() {
    }

    @Test
    void unassignAudience() {
    }

    @Test
    void assignSource() {
    }

    @Test
    void unassignSource() {
    }

    private CampaignDTO testCampaign() {
        return new CampaignDTOBuilder()
                .code("testCode")
                .controlGroupPercent(20.0)
                .createdBy("egor")
                .description("testDescription")
                .endDate(ZonedDateTime.now().plusMonths(2))
                .stage("stage")
                .startDate(ZonedDateTime.now().plusMonths(1))
                .startResponseCollectDate(ZonedDateTime.now().plusMonths(1).plusDays(1))
                .endResponseCollectDate(ZonedDateTime.now().plusMonths(1).plusDays(2))
                .marketingCategory(MarketingCategory.BUSINESS)
                .name(String.format("testName[%s]",UUID.randomUUID().toString()))
                .period(2)
                .periodTimeUnit(TimeUnit.HOURS)
                .language("ru")
                .program("unknown")
                .scope(CampaignScope.MASS)
                .type(CampaignType.CRM)
                .frequency(CampaignFrequency.ONE_TIME)
                .channel(ChannelType.EMAIL)
                .status(CampaignStatus.PLANNING)
                .campaignCategory(CampaignCategory.EXTRA_SERVICES)
                .build();
    }
}