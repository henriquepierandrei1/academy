package br.com.academy.controllers;

import br.com.academy.Dao.AlunoDao;
import br.com.academy.model.Aluno;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AlunoController {
    @Autowired
    private AlunoDao alunoDao;



    @GetMapping("/inserirAlunos")
    public ModelAndView insertAlunos(Aluno aluno){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/formAluno");
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    @PostMapping("InsertAlunos")
    public ModelAndView inserirAluno(@Valid Aluno aluno, @NotNull BindingResult br){
        ModelAndView mv = new ModelAndView();
        if (br.hasErrors()){
            System.out.println("erro");
            // Se houver erros de validação, exiba novamente o formulário com os erros
            mv.setViewName("Aluno/formAluno");
            mv.addObject("aluno", aluno); // Adiciona o aluno com erros ao ModelAndView
            return mv;
        } else {
            // Se não houver erros de validação, salve o aluno e redirecione para a página de sucesso
            alunoDao.save(aluno);
            System.out.println("sem erro");
            return new ModelAndView("redirect:/index");
        }
    }



    @GetMapping("/alunos-adicionados")
    public ModelAndView listagemAlunos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/listAlunos");
        mv.addObject("alunosList", alunoDao.findAll());
        return mv;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alterar");
        Aluno aluno = alunoDao.getOne(id);
        mv.addObject("aluno",aluno);
        return mv;

    }

    @PostMapping("/alterar")
    public ModelAndView alterar(Aluno aluno){
        ModelAndView mv = new ModelAndView();
        alunoDao.save(aluno);
        mv.setViewName("redirect:/alunos-adicionados");
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id){
        alunoDao.deleteById(id);
        return "redirect:/alunos-adicionados";
    }

    @GetMapping("/filtrar")
    public ModelAndView filtro(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/filtroAluno");
        return mv;

    }

    @GetMapping("/alunos-ativos")
    public ModelAndView listagemAtivos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-ativos");
        mv.addObject("alunosAtivos", alunoDao.findByStatusAtivos());
        return mv;
    }

    @GetMapping("/alunos-inativos")
    public ModelAndView listagemInativos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-inativos");
        mv.addObject("alunosInativos", alunoDao.findByStatusInativos());
        return mv;
    }

    @GetMapping("/alunos-trancados")
    public ModelAndView listagemTrancados(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-trancados");
        mv.addObject("alunosTrancados", alunoDao.findByStatusTrancados());
        return mv;
    }

    @GetMapping("/alunos-cancelados")
    public ModelAndView listagemCancelados(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-cancelados");
        mv.addObject("alunosCancelados", alunoDao.findByStatusCancelados());
        return mv;
    }

    @PostMapping("pesquisar-aluno")
    public ModelAndView pesquisarAluno(@RequestParam(required = false) String nome){
        ModelAndView mv = new ModelAndView();
        List<Aluno> list;
        if (nome==null || nome.trim().isEmpty()){
            list = alunoDao.findAll();
        }else {
            list = alunoDao.findByNomeContainingIgnoreCase(nome);
        }
        mv.addObject("ListaDeAlunos", list);
        mv.setViewName("Aluno/pesquisa-resultado");
        return mv;
    }

}
