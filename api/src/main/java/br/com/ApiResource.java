package br.com;

import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


@Path("/api")
public class ApiResource {


    @Channel("quarkus-rabbitmq")
    Emitter<JsonObject> emitter;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sendToSimpleQueue() {
        JsonObject obj = new JsonObject();
        obj.put("salve", "quebrada");
        emitter.send(obj);
        return "Rabbit Working";
    }

}
