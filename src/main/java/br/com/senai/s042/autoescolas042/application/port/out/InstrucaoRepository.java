package br.com.senai.s042.autoescolas042.application.port.out;

import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrucao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {
    Boolean existsByAlunoIdAndDataBetween(Long id, LocalDateTime inicioExpediente, LocalDateTime fimExpediente);
    Boolean existsByInstrutorIdAndData(Long id, LocalDateTime data);
}
