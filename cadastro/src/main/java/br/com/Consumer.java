package br.com;

import br.com.dto.QueueRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.dto.CadastroDTO;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@ApplicationScoped
public class Consumer {

    private final Logger log = LoggerFactory.getLogger(Consumer.class);

    @Inject
    CadastroTransformMapper mapper;

    @Incoming("quarkus-rabbitmq")

    public QueueResponseDTO processaEnvioAutor(QueueRequestDTO request) throws Exception {
        QueueResponseDTO response = new QueueResponseDTO();

        switch (request.getCrudMethod()) {

            case GET:
                try {
                    UUID id = (UUID) request.getObjeto();
                    log.info("ID recebido:" + "\n" + id);

                    CadastroDTO autorPorId = autorService.buscarAutorPorId(id);

                    if (Objects.isNull(autorPorId.getId())) {
                        log.info("Autor não encontrado");

                        response.setMensagemRetorno("Autor não encontrado");
                        response.setErro(false);
                        response.setObjeto("Data/Horário da transação: " + LocalDateTime.now());
                    } else {
                        log.info("Autor referente ao ID:" + "\n" + autorPorId);

                        response.setMensagemRetorno("Autor encontrado");
                        response.setErro(false);
                        response.setObjeto(autorPorId);
                    }

                } catch (Exception e) {
                    response.setMensagemRetorno(e.getMessage());
                    response.setErro(true);
                    response.setObjeto(e);
                    log.error("Falha ao buscar autor: " + response);
                }

                break;
        }
    }