package br.com.senai.s042.autoescolas042.adapter.in.controller.mapper;

import br.com.senai.s042.autoescolas042.application.core.domain.dto.DadosEndereco;
import br.com.senai.s042.autoescolas042.application.core.domain.vo.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {
    public Endereco toDomain(DadosEndereco dados){
        return new Endereco(
                dados.logradouro(),
                dados.numero(),
                dados.complemento(),
                dados.bairro(),
                dados.cidade(),
                dados.cep(),
                dados.uf()
        );
    }

    public DadosEndereco toDTO(Endereco endereco){
        return new DadosEndereco(
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getUf(),
                endereco.getCep()
        );
    }
}
