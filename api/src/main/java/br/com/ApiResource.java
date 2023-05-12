package br.com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import javax.inject.Inject;


@Path("/api")
public class ApiResource {

    private final Logger log = LoggerFactory.getLogger(MessageResource.class);

    @Inject
    private MessageSender messageSender;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CadastroInfos sendToSimpleQueue(final CadastroInfos message) {
        log.info("M=sendToSimpleQueue, I=receiving message to send. message={}", message);

        messageSender.send(DeclaredQueuesEnum.SAMPLE_QUEUE, message);

        return message;
    }

}
