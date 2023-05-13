package br.com;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class CadastroService {

    private final Logger log = LoggerFactory.getLogger(CadastroService.class);

    @Incoming("quarkus-rabbitmq")

    public void hello(JsonObject msg) {
        Log.info(msg.toString());
        Log.info("Chegou, meu passero!");
    }
}
