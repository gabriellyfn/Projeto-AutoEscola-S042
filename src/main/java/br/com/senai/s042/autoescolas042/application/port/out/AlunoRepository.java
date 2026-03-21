package br.com.senai.s042.autoescolas042.application.port.out;

import br.com.senai.s042.autoescolas042.application.core.domain.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Page<Aluno> findAllByAtivoTrue(Pageable pageable);

    @Query("""
        SELECT i.ativo
        FROM Aluno i
        WHERE 
        i.id = :id
""")
    Boolean findAtivoById(Long id);
}
