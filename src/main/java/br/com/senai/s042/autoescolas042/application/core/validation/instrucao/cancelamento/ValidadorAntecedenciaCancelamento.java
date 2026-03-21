package br.com.senai.s042.autoescolas042.application.core.validation.instrucao.cancelamento;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosCancelamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrucao;
import br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces.ValidadorCancelamento;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorAntecedenciaCancelamento implements ValidadorCancelamento {
    public void validar(Instrucao instrucao, DadosCancelamentoInstrucao dados) {

        if (Duration.between(LocalDateTime.now(), instrucao.getData()).toHours() < 24) {
            throw new RuntimeException("Cancelamento deve ter 24h de antecedência");
        }
    }
}
