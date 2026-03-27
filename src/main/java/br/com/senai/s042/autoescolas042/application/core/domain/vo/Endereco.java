package br.com.senai.s042.autoescolas042.application.core.domain.vo;

import br.com.senai.s042.autoescolas042.application.core.domain.dto.DadosEndereco;
import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(DadosEndereco endereco){}

    public Endereco(String logradouro,
                    String numero,
                    String complemento,
                    String bairro,
                    String cidade,
                    String uf,
                    String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }


    public void atualizarInformacoes(Endereco endereco) {
        if(endereco.logradouro != null){
            this.logradouro = endereco.logradouro;
        }
        if(endereco.numero != null){
            this.numero = endereco.numero;
        }
        if(endereco.complemento != null){
            this.complemento = endereco.complemento;
        }
        if(endereco.bairro != null){
            this.bairro = endereco.bairro;
        }
        if(endereco.cidade != null){
            this.cidade = endereco.cidade;
        }
        if(endereco.uf != null){
            this.uf = endereco.uf;
        }
        if(endereco.cep!= null){
            this.cep = endereco.cep;
        }
    }
}