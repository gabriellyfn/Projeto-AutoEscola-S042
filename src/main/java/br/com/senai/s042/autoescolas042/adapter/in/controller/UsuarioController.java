package br.com.senai.s042.autoescolas042.adapter.in.controller;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario.DadosAtualizacaoUsuario;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario.DadosCadastroUsuarios;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario.DadosDetalhamentoUsuario;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario.DadosListagemUsuarios;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Usuario;
import br.com.senai.s042.autoescolas042.application.port.out.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Page<DadosListagemUsuarios>> listarUsuarios(
            @PageableDefault(size = 10, sort = {"login"}) Pageable pageable){
        Page page = usuarioRepository.findAllByAtivoTrue(pageable).map(DadosListagemUsuarios::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<DadosDetalhamentoUsuario> detalharUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrarUsuarios(
            @RequestBody
            @Valid
            DadosCadastroUsuarios dadosUsuario,
            UriComponentsBuilder uriBuilder){

        String senhaCriptografada = passwordEncoder.encode(dadosUsuario.senha());

        Usuario usuario = new Usuario (
                null,
                dadosUsuario.login(),
                senhaCriptografada,
                true,
                dadosUsuario.perfil()
        );

        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri)
                .body(new DadosDetalhamentoUsuario(usuario));
    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void atualizarUsuarios(@RequestBody DadosAtualizacaoUsuario dadosAtualizacaoUsuario){
        Usuario usuario = usuarioRepository.getReferenceById(Long.valueOf(dadosAtualizacaoUsuario.id()));

        usuario.atualizarInformacoes(dadosAtualizacaoUsuario);
        usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id){

        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.excluir();

        usuarioRepository.save(usuario);
        return ResponseEntity.noContent().build();
    }
}

