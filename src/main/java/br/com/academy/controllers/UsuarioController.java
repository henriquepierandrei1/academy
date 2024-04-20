package br.com.academy.controllers;

import br.com.academy.Dao.UsuarioDao;
import br.com.academy.Exceptions.CriptoExistsException;
import br.com.academy.Exceptions.ServiceExc;
import br.com.academy.Util.Util;
import br.com.academy.model.Aluno;
import br.com.academy.model.Usuario;
import br.com.academy.service.ServiceUsuario;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Login/login");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastrar(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Login/cadastro");
        mv.addObject("usuario", new Usuario());
        return mv;
    }
    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home/index");
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    @PostMapping("/salvarUsuario")
    public ModelAndView salvarUsuario(Usuario usuario) throws Exception, CriptoExistsException {
        ModelAndView mv = new ModelAndView();
        serviceUsuario.salvarUsuario(usuario);
        mv.setViewName("redirect:/");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid Usuario usuario, @NotNull BindingResult br, HttpSession httpSession) throws NoSuchAlgorithmException, ServiceExc {
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new Usuario());
        if (br.hasErrors()){
            mv.setViewName("Login/login");
        }
        Usuario usuario1 = serviceUsuario.loginUser(usuario.getEmail(), Util.md5(usuario.getSenha()));
        if (usuario1 == null){
            mv.addObject("msg","Usuário não encontrado!");
            System.out.println("erro");
        }else{
            httpSession.setAttribute("usuarioLogado",usuario1);
            System.out.println("sem erro");
            return index();
        }
        return mv;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        return login();
    }



}
