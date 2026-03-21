package br.com.senai.s042.autoescolas042.adapter.in.controller;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosCancelamentoInstrucao;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.instrucao.DadosDetalhamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.core.usecase.AgendaDeInstrucoes;
import br.com.senai.s042.autoescolas042.application.core.usecase.CancelamentoDeInstrucoes;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instrucoes")
public class InstrucaoController {

    private final AgendaDeInstrucoes agendaDeInstrucoes;
    private final CancelamentoDeInstrucoes cancelamentoDeInstrucoes;

    public InstrucaoController(AgendaDeInstrucoes agendaDeInstrucoes,
                               CancelamentoDeInstrucoes cancelamentoDeInstrucoes) {
        this.agendaDeInstrucoes = agendaDeInstrucoes;
        this.cancelamentoDeInstrucoes = cancelamentoDeInstrucoes;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity <DadosDetalhamentoInstrucao> agendarInstrucao(
            @RequestBody
            @Valid
            DadosAgendamentoInstrucao dadosAgendamentoInstrucao){

        DadosDetalhamentoInstrucao dto = agendaDeInstrucoes.agendarInstrucao(dadosAgendamentoInstrucao);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/cancelar")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity <DadosDetalhamentoInstrucao> cancelarInstrucao(
            @RequestBody
            @Valid
            DadosCancelamentoInstrucao dadosCancelamentoInstrucao) {

        DadosDetalhamentoInstrucao dto = cancelamentoDeInstrucoes.cancelarInstrucao(dadosCancelamentoInstrucao);

        return ResponseEntity.ok(dto);
    }
}

