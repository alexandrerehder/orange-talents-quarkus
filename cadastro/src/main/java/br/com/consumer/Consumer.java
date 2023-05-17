package br.com.consumer;

import br.com.dto.CadastroDTO;
import br.com.dto.QueueRequestDTO;
import br.com.dto.QueueResponseDTO;
import br.com.service.CadastroService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Objects;


@ApplicationScoped
public class Consumer {

    private final Logger log = LoggerFactory.getLogger(Consumer.class);

    @Inject
    CadastroService cadastroService;

    @Incoming("quarkus-rabbitmq")

    public void processaCriacaoCadastro(QueueRequestDTO request) throws Exception {
        log.info("chegou" + request);
    }
}


