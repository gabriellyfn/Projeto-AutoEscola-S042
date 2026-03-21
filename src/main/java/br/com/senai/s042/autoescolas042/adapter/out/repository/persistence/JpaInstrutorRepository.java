package br.com.senai.s042.autoescolas042.adapter.out.repository.persistence;

import br.com.senai.s042.autoescolas042.adapter.out.repository.entity.InstrutorEntity;
import br.com.senai.s042.autoescolas042.application.core.domain.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface JpaInstrutorRepository extends JpaRepository<InstrutorEntity, Long> {
    Page<InstrutorEntity> findAllByAtivoTrue(Pageable paginacao);

    @Query(""" 
        SELECT i FROM Instrutor i
        WHERE
        i.ativo = true
        AND i.especialidade = :especialidade
        AND 
        i.id NOT IN (
                    SELECT a.instrutor.id
                    FROM Instrucao a
                    WHERE a.data = :data
        )
      
        ORDER BY rand()
        LIMIT 01

""")
    InstrutorEntity escolherInstrutorAleatorioDisponivel(Especialidade especialidade, LocalDateTime data);

    @Query("""
        SELECT i.ativo
        FROM Instrutor i
        WHERE 
        i.id = :id
""")
    Boolean findAtivoById(Long id);
}


