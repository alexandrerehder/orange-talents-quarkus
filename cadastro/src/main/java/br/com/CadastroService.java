package br.com;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
public class CadastroService {

    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
}
