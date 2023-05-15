package br.com.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Cadastro extends PanacheEntityBase {

    @Id
    public Long id;

    @Email @NotBlank @NotNull
    public String email;

    @NotBlank @NotNull @Min(6)
    public String senha;

    @CreationTimestamp @Column(name = "data_criacao")
    public LocalDateTime dataCriacao;

    @UpdateTimestamp @Column(name = "data_atualizacao")
    public LocalDateTime dataAtualizacao;

}
