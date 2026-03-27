package br.com.senai.s042.autoescolas042.application.core.domain.model;


import br.com.senai.s042.autoescolas042.application.core.domain.enums.Especialidade;
import br.com.senai.s042.autoescolas042.application.core.domain.vo.Endereco;

public class Instrutor {
    private Long id; // long permite colocar um numero maior de dados
    private String nome;
    private String email;
    private String telefone;
    private String cnh;
    private Especialidade especialidade;
    private Endereco endereco;
    private Boolean ativo = true;

    public Instrutor() {}

    public Instrutor(Long id,
                     String nome,
                     String email,
                     String telefone,
                     String cnh,
                     Especialidade especialidade,
                     Endereco endereco,
                     Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cnh = cnh;
        this.especialidade = especialidade;
        this.endereco = endereco;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCnh() {
        return cnh;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void atualizarInformacoes(String nome, String telefone, Endereco endereco) {
        if (nome != null && !nome.isBlank() && !nome.isEmpty()){
            this.nome = nome;
        }
        if (telefone != null) {
            this.telefone = telefone;
        }
        if (endereco != null){
            this.endereco.atualizarInformacoes(endereco);
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
