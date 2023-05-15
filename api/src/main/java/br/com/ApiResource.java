package br.com;

import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


@Path("/cadastro")
public class ApiResource {

    private final Logger log = LoggerFactory.getLogger(ApiResource.class);

    @Channel("quarkus-rabbitmq")
    Emitter<JsonObject> emitter;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> sendToSimpleQueue() {
        try {
            JsonObject obj = new JsonObject();
            obj.put("salve", "quebrada");
            emitter.send(obj);
            return ResponseEntity.ok("Operação realizada com sucesso!");

        } catch (Exception e) {
            log.error("Erro ao enviar autor para o RabbitMQ", e);
            return ResponseEntity.status(400).body("Erro!");
        }
    }
}
