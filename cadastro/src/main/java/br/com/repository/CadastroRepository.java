package br.com.repository;

import br.com.domain.Cadastro;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface CadastroRepository extends PanacheRepository<Cadastro> {

}
