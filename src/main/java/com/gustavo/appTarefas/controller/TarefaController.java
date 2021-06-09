package com.gustavo.appTarefas.controller;

import java.util.List;

import javax.validation.Valid;

import com.gustavo.appTarefas.model.Tarefa;
import com.gustavo.appTarefas.service.TarefaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @GetMapping("/tarefas")
    public ModelAndView exibirTarefasNaoConcluidas(Model model) {
        List<Tarefa> tarefas = tarefaService.getTarefasNaoConcluidas();

        model.addAttribute("tarefas", tarefas);

        return new ModelAndView("HomeTarefas");
    }

    @GetMapping("/tarefas/concluidas")
    public ModelAndView exibirTarefasConcluidas(Model model) {
        List<Tarefa> tarefas = tarefaService.getTarefasConcluidas();

        model.addAttribute("tarefas", tarefas);

        return new ModelAndView("TarefasConcluidas");
    }

    @GetMapping("/tarefas/criar")
    public ModelAndView ExibirCriacaoDeNovaTarefa(Model model) {
        Tarefa tarefa = new Tarefa();
        model.addAttribute("tarefa", tarefa);

        return new ModelAndView("CriarTarefa");
    }

    @PostMapping("/tarefas/criar")
    public String CriarNovaTarefa(@Valid @ModelAttribute("tarefa") Tarefa tarefa, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "CriarTarefa";
        }

        tarefa.setConcluido(false);
        this.tarefaService.criarTarefa(tarefa);

        return "redirect:/tarefas/";
    }

    @RequestMapping("/tarefas/excluir/{idTarefa}")
    public ModelAndView excluirTarefa(@PathVariable("idTarefa") int idTarefa, Model model) {
        this.tarefaService.excluirTarefa(idTarefa);

        return new ModelAndView("redirect:/tarefas");
    }

    @GetMapping("/tarefas/atualizar/{idTarefa}")
    public ModelAndView ExibirAtualizarTarefa(@PathVariable("idTarefa") int idTarefa, Model model) {
        Tarefa tarefa = this.tarefaService.getTarefaById(idTarefa);
        model.addAttribute("tarefa", tarefa);

        return new ModelAndView("AtualizarTarefa");
    }

    @PostMapping("/tarefas/atualizar/{idTarefa}")
    public String AtualizarTarefa(@Valid @ModelAttribute("tarefa") Tarefa tarefa, BindingResult bindingResult,
            @PathVariable("idTarefa") int idTarefa, Model model) {
        if (bindingResult.hasErrors()) {
            return "AtualizarTarefa";
        }

        tarefa.setId(idTarefa);
        this.tarefaService.atualizarTarefa(tarefa);
        return "redirect:/tarefas/";
    }

    @GetMapping("/tarefas/concluir/{idTarefa}")
    public ModelAndView completarTarefa(@PathVariable("idTarefa") int idTarefa, Model model) {
        Tarefa t = this.tarefaService.getTarefaById(idTarefa);
        t.setConcluido(true);
        this.tarefaService.atualizarTarefa(t);

        return new ModelAndView("redirect:/tarefas");
    }
}
