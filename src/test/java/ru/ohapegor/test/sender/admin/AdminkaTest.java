package ru.ohapegor.test.sender.admin;

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

class AdminkaTest {

    private Adminka adminka = new Adminka();

    @Test
    void changeRestApiKey() {
        System.out.println(adminka.changeRestApiKey("test"));
    }

    @Test
    void changeDefaultSenderName() {
        System.out.println(adminka.changeDefaultSenderName("test"));
    }

    @Test
    void changeDefaultSenderEmail() {
        System.out.println(adminka.changeDefaultSenderEmail("test"));
    }

    @Test
    void changeDefaultSenderPhone() {
        System.out.println(adminka.changeDefaultSenderPhone("test"));
    }

    @Test
    void getAdminData() {
        System.out.println(adminka.getAdminData());
    }

}