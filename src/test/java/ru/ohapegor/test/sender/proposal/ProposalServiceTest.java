package ru.ohapegor.test.sender.proposal;

import org.junit.jupiter.api.Test;
import ru.siblion.crm.sender.api.proposal.rest.dto.*;
import ru.siblion.crm.sender.api.proposal.rest.response.CreateEntityResponse;
import ru.siblion.crm.sender.api.proposal.rest.response.ResponseStatus;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ProposalServiceTest {

    private static final ProposalService service = new ProposalService();

    @Test
    void create() {
        CreateEntityResponse response = service.create(testProposal());
        System.out.println("response = " + response);
        assertSame(response.getStatus(),ResponseStatus.SUCCESS);
    }

    @Test
    void deleteById() {
        System.out.println("service.deleteById(100000L) = " + service.deleteById(100000L));
    }

    @Test
    void edit() {
        ProposalDTO dto = testProposal();
        dto.setId(100000L);
        dto.setCallData(null);
        dto.setSmsData(null);
        dto.setAttachments(null);
        dto.setName("modified");

        System.out.println("service.edit(dto) = " + service.edit(dto));
    }

    @Test
    void getAll() {
        System.out.println("service.getAll() = " + service.getAll(-1L));
    }

    @Test
    void getById() {
        System.out.println("service.getById(100000L) = " + service.getById(100000L));
    }

    @Test
    void getAttachmentById() {
        System.out.println("service.getAttachmentById() = " + service.getAttachmentById(100009L));
    }

    @Test
    void deleteAttachmentById() {
        System.out.println("service.deleteAttachmentById(100000L) = " + service.deleteAttachmentById(100001L));
    }

    @Test
    void assignProposalToCampaign() {
        System.out.println("service.assignProposalToCampaign(100000L,1L) = " + service.assignProposalToCampaign(100000L, 1L));
    }

    private SMSDataDTO testSmsData() {
        return new SMSDataDTO(null, "test", Collections.singletonList(testAttachment()));
    }

    private CallDataDTO testCallData() {
        return new CallDataDTO(null, "Приветствуем {{firstname}} твой id = {{client_id}}, твой email = {{email}}, твой телефон = {{phone}}", null);
    }

    private EmailDataDTO testEmailData() {
        return new EmailDataDTO(null, "test_mail", "siblion", "email", (
                "<!DOCTYPE html>" +
                        "<html><!-- This is a comment --><head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                        "<title></title>" +
                        "<style type=\"text/css\">\n" +
                        "/* ///////// CLIENT-SPECIFIC STYLES ///////// */\n" +
                        "#outlook a{padding:0;} /* Force Outlook to provide a 'view in browser' message */\n" +
                        ".ReadMsgBody{width:100%;} .ExternalClass{width:100%;} /* Force Hotmail to display emails at full width */\n" +
                        ".ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height: 100%;} /* Force Hotmail to display normal line spacing */\n" +
                        "body, table, td, p, a, li, blockquote{-webkit-text-size-adjust:100%; -ms-text-size-adjust:100%;} /* Prevent WebKit and Windows mobile changing default text sizes */\n" +
                        "table, td{mso-table-lspace:0pt; mso-table-rspace:0pt;} /* Remove spacing between tables in Outlook 2007 and up */\n" +
                        "img{-ms-interpolation-mode:bicubic;} /* Allow smoother rendering of resized image in Internet Explorer */\n" +
                        "/* ///////// RESET STYLES ///////// */\n" +
                        "body{margin:0; padding:0;}\n" +
                        "img{border:0; height:auto; line-height:100%; outline:none; text-decoration:none;}\n" +
                        "table{border-collapse:collapse !important;}\n" +
                        "table td { border-collapse: collapse !important;}\n" +
                        "body, #bodyTable, #bodyCell{height:100% !important; margin:0; padding:0; width:100% !important;}\n" +
                        "#mailBody.mailBody .uni-block.button-block { display:block; } /* Specific ukr.net style*/\n" +
                        "body {\n" +
                        "margin: 0;\n" +
                        "text-align: left;\n" +
                        "}\n" +
                        "pre {\n" +
                        "white-space: pre;\n" +
                        "white-space: pre-wrap;\n" +
                        "word-wrap: break-word;\n" +
                        "}\n" +
                        "table.mhLetterPreview { width:100%; }\n" +
                        "table {\n" +
                        "mso-table-lspace:0pt;\n" +
                        "mso-table-rspace:0pt;\n" +
                        "}\n" +
                        "img {\n" +
                        "-ms-interpolation-mode:bicubic;\n" +
                        "}\n" +
                        "</style>" +
                        "</head>" +
                        //"<body><h1>Привет {{firstname}}, твой любымый цвет {{color}}?</h1><br/><p>Мы дарим тебе <b>{{check}}</b> баллов на твой день рождения- {{birthday}} </p><br/><p>P.S. твой Id - {{client_id}} </p><br/><p><a href=\"http://www.siblion.ru/\">Сходи в сиблион</a></p><br/><p><b>Это не спам!</b></p></body>" +
                        "<body width=\"100%\" align=\"center\">\n" +
                        "<!--[if gte mso 9]>       <style type=\"text/css\">           .uni-block img {               display:block !important;           }       </style><![endif]-->\n" +
                        "<center>\n" +
                        "<table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" class=\"container responsive\">\n" +
                        "<tbody>\n" +
                        "<tr>\n" +
                        "<td>\n" +
                        "<table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"wrapper1\" style=\"width: 100%; box-sizing: border-box; background-color: rgb(246, 246, 246); float: left;\">\n" +
                        "<tbody>\n" +
                        "<tr>\n" +
                        "<td class=\"wrapper-row\" style=\"padding: 25px 0px;\"><!--[if (gte mso 9)|(IE)]><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"600\" align=\"center\"><tr><td><![endif]-->\n" +
                        "<table cellpadding=\"0\" cellspacing=\"0\" class=\"wrapper2\" align=\"center\" style=\"background-color: rgb(255, 255, 255); border-radius: 3px; max-width: 600px; width: 100%; border: none; margin: 0px auto; border-spacing: 0px; border-collapse: collapse;\">\n" +
                        "<tbody>\n" +
                        "<tr>\n" +
                        "<td width=\"100%\" class=\"wrapper-row\" style=\"vertical-align: top; max-width: 600px; font-size: 0px; padding: 0px;\"><!--[if (gte mso 9)|(IE)]><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"600\" align=\"center\"><tr><td><![endif]-->\n" +
                        "<table class=\"uni-block image-block\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"width: 100%; table-layout: fixed; height: auto; border-collapse: collapse; border-spacing: 0px; display: inline-table; vertical-align: top; font-size: medium;\">\n" +
                        "<tbody>\n" +
                        "<tr>\n" +
                        "<td style=\"width: 100%; background-image: none; padding: 0px; height: 100%;\" class=\"block-wrapper\" valign=\"top\">\n" +
                        "<table class=\"block-wrapper-inner-table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"height: 209px; width: 100%; table-layout: fixed; text-align: center; border-spacing: 0px; border-collapse: collapse; font-size: 0px;\">\n" +
                        "<tbody>\n" +
                        "<tr>\n" +
                        "<td style=\"width: auto; height: 100%; display: inline-table;\" class=\"content-wrapper\">\n" +
                        "<table class=\"content-box\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"display: inline-table; vertical-align: top; width: auto; height: 100%; border-spacing: 0px; border-collapse: collapse;\">\n" +
                        "<tbody>\n" +
                        "<tr>\n" +
                        "<td style=\"vertical-align: top;\">\n" +
                        "<div class=\"image-wrapper image-drop\"><a class=\"image-link\" href=\"javascript:;\" target=\"_self\"><img class=\"image-element\" src=\"/ru/user_file?resource=himg&user_id=3033041&name=61779xqf36yxyicqowt6znf88dkukf8jxtm7qsedkkmpfwpkjrs5kezx4chu7fqmen6w7gtto7jfu8fttfi3hjicdbddyao9571pjpkq741q9saukzdh38ue5rqa539fu\" alt=\"Some Image\" id=\"gridster_block_2_main_img\" style=\"font-size: small; border: none; width: 100%; max-width: 379px; height: auto; max-height: 209px; outline: none; text-decoration: none;\" width=\"379\"></a></div>\n" +

                        "<!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]--><!--[if (gte mso 9)|(IE)]><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"600\" align=\"center\"><tr><td><![endif]-->\n" +
                        "<table class=\"uni-block text-block\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"width: 100%; table-layout: fixed; height: auto; border-collapse: collapse; border-spacing: 0px; display: inline-table; vertical-align: top; font-size: medium;\">\n" +
                        "<tbody>\n" +
                        "<tr>\n" +
                        "<td style=\"width: 100%; background-color: rgb(255, 255, 255); background-image: none; border: none; height: 100%;\" class=\"block-wrapper\" valign=\"top\">\n" +
                        "<table class=\"block-wrapper-inner-table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"height: 0px; width: 100%; table-layout: fixed; border-spacing: 0px; border-collapse: collapse;\">\n" +
                        "<tbody>\n" +
                        "<tr>\n" +
                        "<td style=\"width: 100%; padding: 5px 10px 5px 20px; vertical-align: top; font-size: 12px; font-family: Arial, Helvetica, sans-serif; line-height: 14.4px; color: rgb(51, 51, 51);\" class=\"content-wrapper\">\n" +
                        "<div class=\"clearfix cke_editable cke_editable_inline cke_contents_ltr cke_show_borders\" style=\"word-wrap: break-word; position: relative;\" tabindex=\"0\" spellcheck=\"false\" role=\"textbox\" aria-label=\"false\" aria-describedby=\"cke_45\"><br>\n" +
                        "<div style=\"text-align:center\">Уважаемый&nbsp;{{CLIENTS_FirstNAME}}!</div>\n" +
                        "<br>\n" +
                        "Отпразднуйте Ваш День Рождения с красивой улыбкой!<br>\n" +
                        "<br>\n" +
                        "Предлагаем Вам скидку в честь вашего Дня Рождения {{CLIENTS_BDATE}} 5 дней 10% на стоматологию<br>\n" +
                        "<br>\n" +
                        "<br>\n" +
                        "<a data-cke-saved-href=\"https://www.drclinics.ru/\" href=\"https://www.drclinics.ru/\" style=\"color: rgb(51, 51, 51);\">Ваш Доктор Рядом!</a><br>\n" +
                        "<br></div>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>\n" +
                        "<!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]--></td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>\n" +
                        "<!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]--></td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>\n" +
                        "</center>\n" +
                        "</body>" +
                        "</html>"
        ).getBytes(Charset.forName("UTF-8")), "test", Collections.singletonList(testAttachment()),null);
    }

    private EmailDataDTO testEmailData2() {
        byte[] htmlBytes;
        try {
            htmlBytes = Files.readAllBytes(Paths.get("src/test/resources/test_email.html"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new EmailDataDTO(null,
                "test_mail",
                "siblion",
                "email",
                htmlBytes,
                "test",
                Collections.singletonList(testAttachment()),
                104735101L);
    }

    private ProposalDTO testProposal() {
        return new ProposalDtoBuilder()
                .code("testCode")
                .createdBy("egor")
                .description("testDescr")
                .name("testName")
                .startDate(ZonedDateTime.now())
                .endDate(ZonedDateTime.now())
                .smsData(testSmsData())
                .emailData(testEmailData2())
                .callData(testCallData())
                .attachments(Collections.singletonList(testAttachment()))
                .build();
    }

    private AttachmentDTO testAttachment() {
        byte[] attachmentsBytes;
        try {
            attachmentsBytes = Files.readAllBytes(Paths.get("src/test/resources/zub.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new AttachmentDTO(null, "zub.jpg", attachmentsBytes, "testAttach");
    }
}