package br.com.academy.Dao;

import br.com.academy.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {


    @Query("select i from Usuario i where i.email = :email")
    public Usuario findByEmail(String email);

    @Query("select h from Usuario h where h.email = :email and h.senha = :senha")
    public Usuario buscarLogin(String email, String senha);
}
