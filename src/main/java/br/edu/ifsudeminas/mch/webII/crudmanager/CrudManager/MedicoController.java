package br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager.model.Medico;
import br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager.model.dao.MedicoRepository;

@Controller
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping
    public String listarMedicos(Model model) {
        List<Medico> medicos = medicoRepository.findAll();
        model.addAttribute("medicos", medicos);
        return "indexMedicos";
    }

    @GetMapping("/form")
    public String exibirFormularioMedico(@ModelAttribute("medico") Medico medico) {
        return "medico_form";
    }

    @PostMapping("/new")
    public String adicionarMedico(@ModelAttribute("medico") Medico medico) {
        medicoRepository.save(medico);
        return "redirect:/medicos";
    }

    @GetMapping("/update/{id}")
    public String atualizarMedico(@PathVariable("id") Integer id, Model model) {
        Optional<Medico> optMedico = medicoRepository.findById(id);

        if (optMedico.isPresent()) {
            Medico medico = optMedico.get();
            model.addAttribute("medico", medico);
            return "medico_form";
        } else {
            // Gerar erro
            return "redirect:/medicos";
        }
    }

    @GetMapping("/delete/{id}")
    public String deletarMedico(@PathVariable("id") Integer id) {
        Optional<Medico> optMedico = medicoRepository.findById(id);

        if (optMedico.isPresent()) {
            Medico medico = optMedico.get();
            medicoRepository.delete(medico);
        } else {
            // Gerar erro
        }

        return "redirect:/medicos";
    }
}
