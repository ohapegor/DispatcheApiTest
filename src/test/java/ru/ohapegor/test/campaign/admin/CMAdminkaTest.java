package ru.ohapegor.test.campaign.admin;

import org.junit.jupiter.api.Test;
import ru.siblion.crm.campaign.manager.api.dto.MappingDTO;
import ru.siblion.crm.campaign.manager.api.dto.MappingFieldDTO;
import ru.siblion.crm.campaign.manager.api.dto.ResultDataDTO;
import ru.siblion.crm.campaign.manager.api.dto.enums.MappingDataType;
import ru.siblion.crm.campaign.manager.api.dto.enums.MappingFieldType;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static ru.siblion.crm.campaign.manager.api.dto.enums.SystemFields.CLIENT_ID;
import static ru.siblion.crm.campaign.manager.api.dto.enums.SystemFields.EMAIL;
import static ru.siblion.crm.campaign.manager.api.dto.enums.SystemFields.PHONE;

class CMAdminkaTest {

    private static final Long sourceId = 100000L;

    private CMAdminka adminka = new CMAdminka();

    @Test
    void assignPesronalDataSource() {
        System.out.println(adminka.assignPersonalDataSource(testMapping()));
    }

    @Test
    void unassignPesronalDataSource() {
        System.out.println(adminka.unassignPersonalDataSource());
    }

    @Test
    void assignScheduleDataSource() {
        System.out.println(adminka.assignScheduleDataSource(testMapping()));
    }

    @Test
    void unassignScheduleSource() {
        System.out.println(adminka.unassignScheduleSource());
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
        MappingFieldDTO id = new MappingFieldDTO(
                "CLIENTS_PCODE",
                MappingFieldType.SYSTEM_FIELD,
                new ResultDataDTO(CLIENT_ID.name(),
                        MappingDataType.INTEGER), null);
        MappingFieldDTO phone = new MappingFieldDTO(
                "CALL_TRACKING_PHONE",
                MappingFieldType.SYSTEM_FIELD,
                new ResultDataDTO(PHONE.name(),
                        MappingDataType.STRING), null);
        MappingFieldDTO email = new MappingFieldDTO(
                "CLIENTS_CLMAIL",
                MappingFieldType.SYSTEM_FIELD,
                new ResultDataDTO(EMAIL.name(),
                        MappingDataType.STRING), null);
        MappingFieldDTO name = new MappingFieldDTO(
                "CLIENTS_FIRSTNAME",
                MappingFieldType.USER_FIELD,
                new ResultDataDTO("FirstName",
                        MappingDataType.STRING), null);
        /*MappingFieldDTO birthday = new MappingFieldDTO(
                "B_DATE",
                MappingFieldType.USER_FIELD,
                new ResultDataDTO("birthday",
                        MappingDataType.DATE), "yyyy-MM-dd HH:mm:ss");
        MappingFieldDTO check = new MappingFieldDTO(
                "AVG_SUM",
                MappingFieldType.USER_FIELD,
                new ResultDataDTO("check",
                        MappingDataType.DOUBLE), null);
        MappingFieldDTO color = new MappingFieldDTO(
                "любимый цвет",
                MappingFieldType.USER_FIELD,
                new ResultDataDTO("Color",
                        MappingDataType.STRING), null);*/
        return new MappingDTO(sourceId, Arrays.asList(id, phone, email, name));
    }
}