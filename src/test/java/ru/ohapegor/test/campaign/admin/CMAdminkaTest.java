package ru.ohapegor.test.campaign.admin;

import org.junit.jupiter.api.Test;
import ru.siblion.crm.campaign.manager.api.dto.campaign.enums.ChannelType;
import ru.siblion.crm.campaign.manager.api.dto.efficency.enums.AggregateType;
import ru.siblion.crm.campaign.manager.api.dto.efficency.mapping.ResultMappingDTO;
import ru.siblion.crm.campaign.manager.api.dto.efficency.mapping.ResultMappingFieldDTO;
import ru.siblion.crm.campaign.manager.api.dto.efficency.mapping.ResultMappingFieldType;
import ru.siblion.crm.campaign.manager.api.dto.mapping.MappingDTO;
import ru.siblion.crm.campaign.manager.api.dto.mapping.MappingFieldDTO;
import ru.siblion.crm.campaign.manager.api.dto.mapping.ResultDataDTO;
import ru.siblion.crm.campaign.manager.api.dto.mapping.enums.MappingDataType;
import ru.siblion.crm.campaign.manager.api.dto.mapping.enums.MappingFieldType;
import ru.siblion.crm.campaign.manager.api.dto.mapping.enums.SourcePurpose;
import ru.siblion.crm.campaign.manager.api.dto.mapping.enums.SystemFields;
import ru.siblion.crm.campaign.manager.api.response.CMResponse;
import ru.siblion.crm.campaign.manager.api.response.ResponseStatus;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertSame;


class CMAdminkaTest {

    private static final Long sourceId = 100000L;

    private Long mappingId = 100001L;

    private CMAdminka adminka = new CMAdminka();

    @Test
    void setupSources(){
        MappingDTO mappingDTO = testMapping();
        mappingDTO.setName(String.format("testMapping[%s]",ZonedDateTime.now()));
        mappingDTO.setSourcePurpose(SourcePurpose.PERSONAL);

        CMResponse<Long> response1 = adminka.addGlobalDataSource(mappingDTO);
        assertSame(response1.getStatus(),ResponseStatus.SUCCESS);
        assertSame(adminka.activateDataSource(response1.getDto()).getStatus(),ResponseStatus.SUCCESS);



        mappingDTO.setName(String.format("testMapping[%s]",ZonedDateTime.now()));
        mappingDTO.setSourcePurpose(SourcePurpose.SCHEDULE);
        CMResponse<Long> response3 = adminka.addGlobalDataSource(mappingDTO);
        assertSame(response3.getStatus(),ResponseStatus.SUCCESS);
        assertSame(adminka.activateDataSource(response3.getDto()).getStatus(),ResponseStatus.SUCCESS);
    }

    @Test
    void assignPersonalSource(){
        MappingDTO mappingDTO = testMapping();
        mappingDTO.setName(String.format("testMapping[%s]",ZonedDateTime.now()));
        mappingDTO.setSourcePurpose(SourcePurpose.PERSONAL);
        CMResponse response = adminka.addGlobalDataSource(mappingDTO);
        System.out.println(response);
        assertSame(response.getStatus(),ResponseStatus.SUCCESS);
    }

    @Test
    void assignReportlSources(){
        ResultMappingDTO resultMappingDTO = testResultMapping1();
        CMResponse response1 = adminka.addGlobalResultDataSource(resultMappingDTO);
        System.out.println(response1);
        assertSame(response1.getStatus(),ResponseStatus.SUCCESS);

        ResultMappingDTO resultMappingDTO2 = testResultMapping2();
        CMResponse response2 = adminka.addGlobalResultDataSource(resultMappingDTO2);
        System.out.println(response2);
        assertSame(response2.getStatus(),ResponseStatus.SUCCESS);
    }

    @Test
    void assignScheduleSource(){
        MappingDTO mappingDTO = testMapping();
        mappingDTO.setName(String.format("testMapping[%s]",ZonedDateTime.now()));
        mappingDTO.setSourcePurpose(SourcePurpose.SCHEDULE);
        System.out.println(adminka.addGlobalDataSource(mappingDTO));
    }

    @Test
    void activateGlobalPersonalSource(){
        System.out.println(adminka.activateDataSource(100001L));
    }

    @Test
    void activateGlobalReportlSource(){
        System.out.println(adminka.activateDataSource(100002L));
    }

    @Test
    void activateScheduleReportlSource(){
        System.out.println(adminka.activateDataSource(100003L));
    }

    @Test
    void showAdminData() {
        System.out.println(adminka.showAdminData());
    }


    @Test
    void showFields() {
        System.out.println(adminka.showFields());
    }

    private MappingDTO testMapping() {
        MappingFieldDTO id = new MappingFieldDTO();
        id.setSourceName("CLIENTS_PCODE");
        id.setMappingFieldType(MappingFieldType.SYSTEM_FIELD);
        id.setResultData(new ResultDataDTO(SystemFields.CLIENT_ID.name(), MappingDataType.INTEGER));

        MappingFieldDTO phone = new MappingFieldDTO();
        phone.setSourceName("CALL_TRACKING_PHONE");
        phone.setMappingFieldType(MappingFieldType.SYSTEM_FIELD);
        phone.setResultData(new ResultDataDTO(SystemFields.PHONE.name(), MappingDataType.STRING));

        MappingFieldDTO email = new MappingFieldDTO();
        email.setSourceName("CLIENTS_CLMAIL");
        email.setMappingFieldType(MappingFieldType.SYSTEM_FIELD);
        email.setResultData(new ResultDataDTO(ChannelType.EMAIL.name(), MappingDataType.STRING));

        MappingFieldDTO name = new MappingFieldDTO();
        name.setSourceName("CLIENTS_FIRSTNAME");
        name.setMappingFieldType(MappingFieldType.USER_FIELD);
        name.setResultData(new ResultDataDTO("FirstName", MappingDataType.STRING));

        MappingDTO mappingDTO = new MappingDTO();
        mappingDTO.setSourceId(sourceId);
        mappingDTO.setFields(Arrays.asList(id, phone, email, name));
        return mappingDTO;
    }

    private ResultMappingDTO testResultMapping1(){
        ResultMappingDTO mapping = new ResultMappingDTO();

        ResultMappingFieldDTO id = new ResultMappingFieldDTO();
        id.setResultMappingFieldType(ResultMappingFieldType.CLIENT_ID.CLIENT_ID);
        id.setSourceFieldName("CLIENTS_PCODE");

        ResultMappingFieldDTO summ = new ResultMappingFieldDTO();
        summ.setResultMappingFieldType(ResultMappingFieldType.AGGREGATE);
        summ.setAggregateType(AggregateType.SUMM);
        summ.setSourceFieldName("CLIENTS_POL");

        ResultMappingFieldDTO filter = new ResultMappingFieldDTO();
        filter.setResultMappingFieldType(ResultMappingFieldType.FILTER);
        filter.setSourceFieldName("CLIENTS_FIRSTNAME");

        mapping.setSourceId(sourceId);
        mapping.setName("test_result_source");
        mapping.setFields(Arrays.asList(id,summ,filter));
        return mapping;
    }

    private ResultMappingDTO testResultMapping2(){
        ResultMappingDTO mapping = new ResultMappingDTO();

        ResultMappingFieldDTO id = new ResultMappingFieldDTO();
        id.setResultMappingFieldType(ResultMappingFieldType.CLIENT_ID);
        id.setSourceFieldName("CLIENTS_PCODE");

        ResultMappingFieldDTO summ = new ResultMappingFieldDTO();
        summ.setResultMappingFieldType(ResultMappingFieldType.AGGREGATE);
        summ.setAggregateType(AggregateType.SUMM);
        summ.setSourceFieldName("CLIENTS_FILIAL");

        ResultMappingFieldDTO date = new ResultMappingFieldDTO();
        date.setResultMappingFieldType(ResultMappingFieldType.DATE);
        date.setSourceFieldName("CLIENTS_BDATE");

        mapping.setSourceId(100038L);
        mapping.setName("test_result_source2");
        mapping.setFields(Arrays.asList(id,summ,date));
        return mapping;
    }
}