package ru.ohapegor.test.campaign.fakedata;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FakeDataGenerator {

    private static final String REAL = "Real";

    private static final List<Integer> FILIALS = Arrays.asList(
            1,
            5,
            99,
            2,
            3,
            6,
            98,
            4,
            8,
            7,
            9,
            10
    );

    private static final List<Names> VALID_NAMES = Arrays.asList(
            new Names("Егор", "Охапкин", REAL, 1),
            new Names("Мария", "Гордиенко", REAL, 2),
            new Names("Ромашка", "Няшка", REAL, 1)
    );

    private static final List<String> VALID_PHONES = Arrays.asList(
            "+79773588131", //егор
            "+79858401743", //маша
            "+79859110449", //рома
            "+79263930526",
            "+79858401743",
            "+79164308403",
            "+79151436501",
            "+79175032837",
            "+79857721730",
            "+79261022616"
    );

    private static final List<String> VALID_EMAILS = Arrays.asList(
            "ohapegor@list.ru",
            "msgordienko@gmail.com",
            "dungorthed@yandex.ru"
    );

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String manNames = "src/test/resources/fakeData/man_names.txt";

    private static final String femailNames = "src/test/resources/fakeData/female_names.txt";

    private static final String CLIENTS_FILE = "src/test/resources/fakeData/CLIENTS_500.sql";

    private static final String IDS_FILE = "src/test/resources/fakeData/clients_ids.tsv";

    private static final String INS_TEMPLATE = "\nINSERT INTO CLIENTS " +
            "(PCODE, FIRSTNAME, LASTNAME, MIDNAME, BDATE, FILIAL, POL, PHONE3, CLMAIL, MODIFYDATE, CSTATUS)" +
            " VALUES " +
            "('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');";

    private List<Names> getManNames() throws IOException {
        Path manFile = Paths.get(manNames);
        return Files.readAllLines(manFile).stream()
                .map((String line) -> line.split(" "))
                .map((String[] parts) -> new Names(
                        parts[1],
                        parts[0],
                        parts[2],
                        1
                ))
                .collect(Collectors.toList());
    }

    private List<Names> getFemailNames() throws IOException {
        Path manFile = Paths.get(femailNames);
        return Files.readAllLines(manFile).stream()
                .map((String line) -> line.split(" "))
                .map((String[] parts) -> new Names(
                        parts[1],
                        parts[0],
                        parts[2],
                        2
                ))
                .collect(Collectors.toList());
    }

    private List<Names> getAllNames() throws IOException {
        List<Names> allNames = new ArrayList<>();
        allNames.addAll(getFemailNames());
        allNames.addAll(getManNames());
        return allNames;
    }

    private List<String> clienIds() throws IOException {
        Path clientsFIle = Paths.get(IDS_FILE);
        return Files.readAllLines(clientsFIle);
    }

    private String randomBdate() {
        LocalDateTime startDate = LocalDateTime.now().minusYears(10);
        int randomSeconds = (int) (Math.random() * 60 * 60 * 24 * 365 * 70);
        LocalDateTime bdate = startDate.minusSeconds(randomSeconds);
        return bdate.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    private String randomModifyDate() {
        LocalDateTime startDate = LocalDateTime.now();
        int randomSeconds = (int) (Math.random() * 60 * 60 * 24 * 365 * 3);
        LocalDateTime bdate = startDate.minusSeconds(randomSeconds);
        return bdate.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    private String randomFilial() {
        return String.valueOf(FILIALS.get((int) ((Math.random() + 0.05) * (FILIALS.size() - 1))));
    }

    //@Test
    void testBdate() {
        for (int i = 0; i < 100; i++) {
            System.out.println(randomFilial());
        }
    }

    private String testEmail(int idx) {
        return String.format("test%d@divcu.com", idx);
    }

    private String testPhone(int idx) {
        return "+79" + (100000000 + idx);
    }

    @Test
    void test() throws IOException {
        Path SQL = Paths.get(CLIENTS_FILE);
        StringBuilder file = new StringBuilder("DELETE FROM CLIENTS;");
        List<String> ids = clienIds();
        List<Names> names = getAllNames();
        for (int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            Names name = VALID_NAMES.size() > i ? VALID_NAMES.get(i) : names.get(i % 500);
            file.append(String.format(INS_TEMPLATE,
                    id, //ID
                    name.getFirstName(), //FIRSTNAME
                    name.getLastName(), //LASTNAME
                    (i >= VALID_EMAILS.size() && i <= 30) ? "Fake" : name.getMidName(), //MIDNAME
                    randomBdate(), //BDATE
                    randomFilial(), //FILIAL
                    name.getSex(), //POL
                    VALID_PHONES.size() > i ? VALID_PHONES.get(i) : testPhone(i), //PHONE3
                    VALID_EMAILS.size() > i ? VALID_EMAILS.get(i) : testEmail(i),//CLMAIL
                    randomModifyDate(),//MODIFYDATE
                    (int) (Math.random() * 10)//CSTATUS
            ));
        }
        Files.write(SQL, file.toString().getBytes());
    }
}
