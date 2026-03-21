package br.com.senai.s042.autoescolas042.application.port.out;

import br.com.senai.s042.autoescolas042.application.core.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long > {
    UserDetails findByLogin(String login);
    Page<Usuario> findAllByAtivoTrue (Pageable pageable);
}
