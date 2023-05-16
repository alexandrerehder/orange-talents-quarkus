package br.com.service;

import br.com.domain.Cadastro;
import br.com.dto.CadastroDTO;
import br.com.mapper.CadastroTransformMapper;
import br.com.repository.CadastroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class CadastroService {

    @Inject
    CadastroRepository cadastroRepository;
    private CadastroTransformMapper mapper;


    @Transactional
    public void criarCadastro(CadastroDTO dto) {
        Cadastro cadastro = mapper.toEntity(dto);
        cadastroRepository.persistAndFlush(cadastro);
    }

    @Transactional
    public CadastroDTO buscarCadastroPorId(Long id) {
        Optional<Cadastro> cadastro = cadastroRepository.findByIdOptional(id);
        return cadastro.isPresent() ? mapper.toDTO(cadastro.get()) : new CadastroDTO();
    }
}
