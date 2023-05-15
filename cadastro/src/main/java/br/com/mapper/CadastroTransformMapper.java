package br.com.mapper;

import br.com.domain.Cadastro;
import br.com.dto.CadastroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface CadastroTransformMapper {

    CadastroDTO toDTO(Cadastro cadastro);

    @Mapping(target = "id", ignore = true)
    Cadastro toEntity(CadastroDTO dto);
}
