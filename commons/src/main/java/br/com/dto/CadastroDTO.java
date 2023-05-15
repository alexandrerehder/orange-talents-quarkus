package br.com.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CadastroDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String email;
    private String senha;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

}
