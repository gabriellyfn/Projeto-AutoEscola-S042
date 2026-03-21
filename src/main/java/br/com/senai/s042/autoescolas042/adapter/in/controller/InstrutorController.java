package br.com.senai.s042.autoescolas042.adapter.in.controller;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrutor.DadosAtualizacaoInstrutor;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrutor.DadosCadastroInstrutor;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.instrutor.DadosDetalhamentoInstrutor;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.instrutor.DadosListagemInstrutor;
import br.com.senai.s042.autoescolas042.application.core.usecase.InstrutorService;
import br.com.senai.s042.autoescolas042.application.port.in.ModelDomainController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController // avisando o spring boot que e um controller
@RequestMapping("/instrutores")
public class InstrutorController implements ModelDomainController<
        DadosCadastroInstrutor,
        DadosListagemInstrutor,
        DadosAtualizacaoInstrutor,
        Void,
        DadosDetalhamentoInstrutor,
        Long
        > {

    private final InstrutorService service;

    public InstrutorController(InstrutorService service){
        this.service = service;
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<DadosDetalhamentoInstrutor> cadastrar(
            @RequestBody DadosCadastroInstrutor dados,
            UriComponentsBuilder uribuilder) {
        DadosDetalhamentoInstrutor dto = service.cadastrar(dados);

        URI uri = uribuilder.path("/instrutores/{id}")
                .buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri)
                .body(dto);
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Page<DadosListagemInstrutor>> listar(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        return ResponseEntity.ok(service.listar(paginacao));
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<DadosDetalhamentoInstrutor> detalhar(
            @PathVariable Long id) {
        DadosDetalhamentoInstrutor dto = service.detalhar(id);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<DadosDetalhamentoInstrutor> atualizar(
            @RequestBody DadosAtualizacaoInstrutor dados){
        DadosDetalhamentoInstrutor dto = service.atualizar(dados);
        return ResponseEntity.ok(dto);
    }

    @Override
    @DeleteMapping("/{id}") // Padrão de Mercado
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
