package br.com.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class QueueResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -92711001580885805L;

    private String mensagemRetorno;
    private boolean erro;
    private Object objeto;

}
