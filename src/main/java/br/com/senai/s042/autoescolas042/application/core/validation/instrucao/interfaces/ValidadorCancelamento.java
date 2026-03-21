package br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosCancelamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrucao;

public interface ValidadorCancelamento {
    void validar(Instrucao instrucao, DadosCancelamentoInstrucao dados);
}
