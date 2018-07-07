package ru.ohapegor.test.sender.admin;

import org.junit.jupiter.api.Test;

class AdminkaTest {

    private Adminka adminka = new Adminka();

    @Test
    void changeRestApiKey() {
        System.out.println(adminka.changeRestApiKey("test"));
    }

    @Test
    void getAdminData() {
        System.out.println(adminka.getAdminData());
    }

}