package br.com.senai.s042.autoescolas042.application.port.out;

import br.com.senai.s042.autoescolas042.application.core.domain.enums.Especialidade;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.Optional;

public interface InstrutorRepository {
    Page<Instrutor> findAllByAtivoTrue(Pageable paginacao);
    Instrutor escolherInstrutorAleatorioDisponivel(Especialidade especialidade, LocalDateTime data);
    Boolean findAtivoById(Long id);
    Instrutor save(Instrutor instrutor);
    Optional<Instrutor> findById(Long id);
    Instrutor getReferenceById(Long id);
    boolean existsById(Long aLong);
}