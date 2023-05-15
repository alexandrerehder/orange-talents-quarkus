package br.com;

import br.com.dto.CadastroDTO;
import br.com.dto.CrudMethod;
import br.com.dto.QueueRequestDTO;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/cadastro")
public class ApiResource {

    private final Logger log = LoggerFactory.getLogger(ApiResource.class);

    @Channel("quarkus-rabbitmq")
    Emitter<QueueRequestDTO> emitter;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> sendToSimpleQueue(@RequestBody CadastroDTO dto) {
        try {
            QueueRequestDTO request = new QueueRequestDTO();
            request.setObjeto(dto);
            request.setCrudMethod(CrudMethod.GET);
            emitter.send(request);
            return ResponseEntity.ok("Operação realizada com sucesso!");

        } catch (Exception e) {
            log.error("Erro ao enviar autor para o RabbitMQ", e);
            return ResponseEntity.status(400).body("Erro!");
        }
    }
}
