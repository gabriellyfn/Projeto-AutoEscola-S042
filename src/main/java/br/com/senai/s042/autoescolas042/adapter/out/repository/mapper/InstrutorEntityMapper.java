package br.com.senai.s042.autoescolas042.adapter.out.repository.mapper;

import br.com.senai.s042.autoescolas042.adapter.out.repository.entity.InstrutorEntity;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrutor;
import org.springframework.stereotype.Component;

@Component
public class InstrutorEntityMapper {
    public InstrutorEntity toEntity(Instrutor instrutor){
        return new InstrutorEntity(
                instrutor.getId(),
                instrutor.getNome(),
                instrutor.getEmail(),
                instrutor.getTelefone(),
                instrutor.getCnh(),
                instrutor.getEspecialidade(),
                instrutor.getEndereco(),
                instrutor.getAtivo());
    }

    public Instrutor toDomain(InstrutorEntity entity){
        return new Instrutor(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getCnh(),
                entity.getEspecialidade(),
                entity.getEndereco(),
                entity.getAtivo()
        );
    }
}
