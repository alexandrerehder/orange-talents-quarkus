package br.com.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class QueueRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5277553801366314342L;

    private Object objeto;
    private CrudMethod crudMethod;
}
