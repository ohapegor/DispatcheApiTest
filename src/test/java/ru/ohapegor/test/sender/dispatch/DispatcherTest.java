package ru.ohapegor.test.sender.dispatch;

import org.junit.jupiter.api.Test;
import ru.siblion.crm.sender.api.dispatch.dto.ChannelType;
import ru.siblion.crm.sender.api.dispatch.dto.ClientInfo;
import ru.siblion.crm.sender.api.dispatch.dto.unisender.FieldType;
import ru.siblion.crm.sender.api.dispatch.dto.unisender.PersonalField;
import ru.siblion.crm.sender.api.dispatch.request.DispatchSendRequest;
import ru.siblion.crm.sender.api.dispatch.request.DispatchStatusRequest;

import java.util.*;


class DispatcherTest {

    private static final String EMAIL = "email";
    private static final String NAME = "Name";
    private static final String PHONE = "phone";
    private static final String CLIENT_ID = "clientId";

    private static final Dispatcher dispatcher = new Dispatcher();

    @Test
    void send() {
        DispatchSendRequest request = new DispatchSendRequest();
        request.setChannelType(ChannelType.EMAIL);
        request.setDescription("testDescription");
        request.setProposalId(100000L);
        request.setFields(Arrays.asList(
                new PersonalField(EMAIL, FieldType.STRING, null),
                new PersonalField(NAME, FieldType.STRING, null),
                new PersonalField(CLIENT_ID, FieldType.NUMBER, null),
                new PersonalField(PHONE, FieldType.STRING, null)
        ));
        request.setName("testName");
        request.setContacts(clientInfoList());

        System.out.println(dispatcher.send(request));

    }

    @Test
    void result() {
        DispatchStatusRequest request = new DispatchStatusRequest();
        request.setDispatchId(100008L);
        request.setAdditionalFields(Arrays.asList(PHONE,CLIENT_ID,NAME));
        System.out.println(dispatcher.result(request));
    }

    private List<ClientInfo> clientInfoList() {
        Map<String, String> map1 = new LinkedHashMap<>();
        map1.put(EMAIL, "ohapegor@list.ru");
        map1.put(PHONE, "+79773588111");
        map1.put(CLIENT_ID, "999");
        map1.put(NAME, "Егорка");

        Map<String, String> map2 = new LinkedHashMap<>();
        map2.put(EMAIL, "3@example.org");
        map2.put(PHONE, "+79773588133");
        map2.put(CLIENT_ID, "123");
        map2.put(NAME, "NAME_2");
        return Arrays.asList(
                new ClientInfo(1L, map1),
                new ClientInfo(2L, map1)
        );
    }
}