package br.com.senai.s042.autoescolas042.application.core.usecase;

import br.com.senai.s042.autoescolas042.adapter.in.controller.mapper.EnderecoMapper;
import br.com.senai.s042.autoescolas042.adapter.in.controller.mapper.InstrutorMapper;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrutor.DadosAtualizacaoInstrutor;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrutor.DadosCadastroInstrutor;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.instrutor.DadosDetalhamentoInstrutor;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.instrutor.DadosListagemInstrutor;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrutor;
import br.com.senai.s042.autoescolas042.application.port.out.InstrutorRepository;
import br.com.senai.s042.autoescolas042.exception.types.instrutor.InstrutorNaoExisteException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InstrutorService {
    private final InstrutorRepository repository;
    private final EnderecoMapper enderecoMapper;
    private final InstrutorMapper instrutorMapper;

    public InstrutorService(
            InstrutorRepository repository,
            EnderecoMapper enderecoMapper,
            InstrutorMapper instrutorMapper){
        this.repository = repository;
        this.enderecoMapper = enderecoMapper;
        this.instrutorMapper = instrutorMapper;
    }

    @Transactional
    public DadosDetalhamentoInstrutor cadastrar(DadosCadastroInstrutor dados) {
        Instrutor instrutor = instrutorMapper.toDomain(dados);
        Instrutor salvo = repository.save(instrutor);
        return instrutorMapper.toDetailsDTO(salvo);
    }

    public Page<DadosListagemInstrutor> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao)
                .map(instrutorMapper::toListDTO);
    }

    public DadosDetalhamentoInstrutor detalhar(Long id) {
        Instrutor instrutor = repository.findById(id)
                .orElseThrow(() -> new InstrutorNaoExisteException("ID do instrutor informado não existe"));
        return instrutorMapper.toDetailsDTO(instrutor);
    }

    @Transactional
    public DadosDetalhamentoInstrutor atualizar(DadosAtualizacaoInstrutor dados) {
        Instrutor instrutor = repository.getReferenceById(dados.id());
        instrutor.atualizarInformacoes(
                dados.nome(),
                dados.telefone(),
                enderecoMapper.toDomain(dados.endereco()
                )
        );
        Instrutor salvo = repository.save(instrutor);
        return instrutorMapper.toDetailsDTO(salvo);
    }

    @Transactional
    public void excluir(Long id){
        Instrutor instrutor = repository.getReferenceById(id);
        instrutor.excluir();
        repository.save(instrutor);
    }
}
