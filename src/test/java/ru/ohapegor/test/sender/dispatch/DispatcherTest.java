package ru.ohapegor.test.sender.dispatch;

import org.junit.jupiter.api.Test;
import ru.siblion.crm.sender.api.dispatch.request.DispatchSendRequest;
import ru.siblion.crm.sender.api.dispatch.request.DispatchStatusRequest;

import static org.junit.jupiter.api.Assertions.*;

class DispatcherTest {

    private static final Dispatcher dispatcher = new Dispatcher();

    @Test
    void send() {
        DispatchSendRequest request = new DispatchSendRequest();
        System.out.println("dispatcher.send(request) = " + dispatcher.send(request));
    }

    @Test
    void result() {
        DispatchStatusRequest request = new DispatchStatusRequest();
        System.out.println("dispatcher.result(request) = " + dispatcher.result(request));
    }
}