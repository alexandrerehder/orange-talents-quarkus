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

    public QueueResponseDTO processaCriacaoCadastro(QueueRequestDTO request) throws Exception {
        QueueResponseDTO response = new QueueResponseDTO();

        switch (request.getCrudMethod()) {

            case INSERT:
                try {
                    CadastroDTO cadastro = (CadastroDTO) request.getObjeto();
                    log.info("Objeto recebido:" + "\n" + cadastro);

                    Long id = (Long) request.getObjeto();

                    CadastroDTO clienteExistente = cadastroService.buscarCadastroPorId(id);

                    if (Objects.nonNull(clienteExistente.getId())) {
                        log.info("Listener: Cliente já cadastrado");

                        response.setMensagemRetorno("Cliente já cadastrado. Verifique se as informações estão corretas");
                        response.setErro(false);
                        response.setObjeto("Data/Horário da transação: " + LocalDateTime.now());

                    } else {
                        cadastroService.criarCadastro(cadastro);
                        log.info("Cliente cadastrado:");

                        response.setMensagemRetorno("Cliente cadastrado com sucesso");
                        response.setErro(false);
                    }
                } catch (Exception e) {
                    response.setMensagemRetorno(e.getMessage());
                    response.setErro(true);
                    response.setObjeto(e);
                    log.error("Falha ao cadastrar cliente: " + response);
                }

                break;
        }
        return response;
    }
}


