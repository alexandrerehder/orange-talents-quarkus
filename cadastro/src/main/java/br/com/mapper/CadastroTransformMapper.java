package br.com.mapper;

import br.com.domain.Cadastro;
import br.com.dto.CadastroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CadastroTransformMapper {

    CadastroTransformMapper INSTANCE = Mappers.getMapper(CadastroTransformMapper.class);

    public abstract CadastroDTO toDTO(Cadastro cadastro);

    public abstract Cadastro toEntity(CadastroDTO dto);
}
