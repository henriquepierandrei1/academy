package br.com.academy.service;

import br.com.academy.Dao.UsuarioDao;
import br.com.academy.Exceptions.CriptoExistsException;
import br.com.academy.Exceptions.EmailExistsException;
import br.com.academy.Exceptions.ServiceExc;
import br.com.academy.Util.Util;
import br.com.academy.model.Usuario;
import org.hibernate.service.spi.ServiceException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class ServiceUsuario {
    @Autowired
    private UsuarioDao usuarioDao;
    public void salvarUsuario(@NotNull Usuario usuario) throws Exception, CriptoExistsException {
        try {
            if (usuarioDao.findByEmail(usuario.getEmail()) != null){
                throw new EmailExistsException("Já existe esse email para "+ usuario.getUser());
            }
            usuario.setSenha(Util.md5(usuario.getSenha()));
        } catch (NoSuchAlgorithmException e){
            throw new CriptoExistsException("Erro na criptografia da senha!");
        }
        usuarioDao.save(usuario);
    }

    public Usuario loginUser(String email, String senha) throws ServiceExc {
        Usuario usuario = usuarioDao.buscarLogin(email, senha);
        return usuario;
    }
}
