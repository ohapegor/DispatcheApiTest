package ru.ohapegor.test.sender.proposal;

import org.junit.jupiter.api.Test;
import ru.siblion.crm.sender.api.proposal.rest.dto.*;
import ru.siblion.crm.sender.api.proposal.rest.response.CreateEntityResponse;

import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ProposalServiceTest {

    private static final ProposalService service = new ProposalService();

    @Test
    void create() {
        System.out.println("response = " + service.create(testProposal()));
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
        dto.setEmailData(null);
        dto.setSmsData(null);
        dto.setAttachments(null);
        dto.setName("modifyed");

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
        System.out.println("service.assignProposalToCampaign(100000L,1L) = " + service.assignProposalToCampaign(100000L,1L));
    }

    private SMSDataDTO testSmsData(){
        return new SMSDataDTO(null, "test", Collections.singletonList(testAttachment()));
    }
    private CallDataDTO testCallData(){
        return new CallDataDTO(null, "test_call", Collections.singletonList(testAttachment()));
    }

    private EmailDataDTO testEmailData() {
        return new EmailDataDTO(null, "title", "senderName", "email", "<h1>Hello</>".getBytes(Charset.forName("UTF-8")), "test", Collections.singletonList(testAttachment()));
    }

    private ProposalDTO testProposal(){
        return new ProposalDtoBuilder()
                .code("testCode")
                .createdBy("egor")
                .description("testDescr")
                .name("testName")
                .startDate(ZonedDateTime.now())
                .endDate(ZonedDateTime.now())
                .smsData(testSmsData())
                .emailData(testEmailData())
                .callData(testCallData())
                .attachments(Collections.singletonList(testAttachment()))
                .build();
    }

    private AttachmentDTO testAttachment() {
        return new AttachmentDTO(null, "test.txt", "HelloWorld".getBytes(Charset.forName("UTF-8")), "testAttach");
    }
}