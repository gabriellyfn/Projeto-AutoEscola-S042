package br.com.senai.s042.autoescolas042.application.core.usecase;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario.DadosAtualizacaoUsuario;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario.DadosCadastroUsuarios;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario.DadosDetalhamentoUsuario;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario.DadosListagemUsuarios;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Usuario;
import br.com.senai.s042.autoescolas042.application.port.out.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;}

    @Transactional
    public DadosDetalhamentoUsuario cadastrar(DadosCadastroUsuarios dados){
        Usuario usuario = new Usuario(dados);
        repository.save(usuario);
        return new DadosDetalhamentoUsuario(usuario);
    }

    public Page<DadosListagemUsuarios> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemUsuarios::new);
    }

    public DadosDetalhamentoUsuario detalhar (Long id){
        Usuario usuario = repository.getReferenceById(id);
        return new DadosDetalhamentoUsuario(usuario);
    }

    @Transactional
    public DadosDetalhamentoUsuario atualizar(DadosAtualizacaoUsuario dados){
        Usuario usuario = repository.getReferenceById(Long.valueOf(dados.id()));
        usuario.atualizarInformacoes(dados);
        repository.save(usuario);
        return new DadosDetalhamentoUsuario(usuario);
    }

    @Transactional
    public void excluir(Long id){
        Usuario usuario = repository.getReferenceById(id);
        usuario.excluir();
        repository.save(usuario);
    }
}
