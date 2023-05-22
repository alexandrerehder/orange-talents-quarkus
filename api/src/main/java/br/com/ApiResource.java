package br.com;

import br.com.config.DeclaredQueuesEnum;
import br.com.dto.CadastroDTO;
import br.com.dto.CrudMethod;
import br.com.dto.QueueRequestDTO;
import br.com.sender.MessageSender;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/api")
public class ApiResource {

    private final Logger log = LoggerFactory.getLogger(ApiResource.class);

    @Inject
    private MessageSender messageSender;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> sendToSimpleQueue(@RequestBody CadastroDTO dto) {
        try {
            QueueRequestDTO request = new QueueRequestDTO();
            request.setObjeto(dto);
            request.setCrudMethod(CrudMethod.INSERT);
            messageSender.send(DeclaredQueuesEnum.SAMPLE_QUEUE, request);
            return ResponseEntity.ok("Operação realizada com sucesso!");

        } catch (Exception e) {
            log.error("Erro ao enviar autor para o RabbitMQ", e);
            return ResponseEntity.status(400).body("Erro!");
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "vai tomar no cu";
    }
}

