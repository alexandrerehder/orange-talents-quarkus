package br.com.sender;

import br.com.config.DeclaredQueuesEnum;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import io.quarkiverse.rabbitmqclient.RabbitMQClient;
import io.quarkus.runtime.StartupEvent;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

@Singleton
public class MessageSender {

    private final Logger log = LoggerFactory.getLogger(MessageSender.class);

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private RabbitMQClient rabbitMQClient;
    private Channel channel;

    public void onApplicationStart(@Observes StartupEvent startupEvent) {

        log.info("M=onApplicationStart, I=starting default sender");
        try {

            final Connection connection = rabbitMQClient.connect();
            channel = connection.createChannel();

        } catch (IOException e) {
            log.error("M=createQueues, I=Erro criando queue. error={}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void send(final DeclaredQueuesEnum queue, final Serializable message) {
        final AMQP.BasicProperties basicProperties = new AMQP.BasicProperties("application/json",
                "UTF-8",
                new HashMap<>(),
                1,
                0, null, null, null,
                null, null, null, null,
                null, null);

        log.info("M=send, I=mensagem enviada. fila={}, mensagem={}", queue.getQueueName(), message);

        send(queue.getQueueName(), queue.getExchangeName(), message, basicProperties);
    }

    public void send(final String queueName, final String exchangeName, final Serializable message, final AMQP.BasicProperties basicProperties) {

        try {
            channel.basicPublish(exchangeName, queueName, basicProperties, objectMapper.writeValueAsBytes(message));
            log.info("M=send, I=mensagem enviada. fila={}, mensagem={}", queueName, message);
        } catch (IOException e) {

            log.error("M=send, E=mesagem nao publicada, e={}", e.getMessage());
        }
    }
}
