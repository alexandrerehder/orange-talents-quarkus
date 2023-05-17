package br.com;

import br.com.dto.CadastroDTO;
import br.com.dto.CrudMethod;
import br.com.dto.QueueRequestDTO;
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

    @Channel("quarkus-rabbitmq")
    Emitter<QueueRequestDTO> emitter;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> sendToSimpleQueue(@RequestBody CadastroDTO dto) {
        try {
            QueueRequestDTO request = new QueueRequestDTO();
            log.info(String.valueOf(dto));
            request.setObjeto(dto);
            request.setCrudMethod(CrudMethod.INSERT);
            emitter.send(request);
            return ResponseEntity.ok("Operação realizada com sucesso!" + dto);

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

