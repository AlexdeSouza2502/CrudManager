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

import br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager.model.Medico;
import br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager.model.Plantao;
import br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager.model.dao.MedicoRepository;
import br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager.model.dao.PlantaoRepository;

@Controller
public class PlantaoController {
    @Autowired
    private PlantaoRepository plantaoRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping("/plantoes")
    public String plantoes(Model model) {
        List<Plantao> plantoes = plantaoRepository.findAll();
        model.addAttribute("plantoes", plantoes);
        return "indexPlantoes";
    }

    @GetMapping("/plantoes/form")
    public String plantaoForm(Model model) {
        model.addAttribute("plantao", new Plantao());
        List<Medico> medicos = medicoRepository.findAll();
        model.addAttribute("medicos", medicos);
        return "plantao_form";
    }

    @PostMapping("/plantoes/new")
    public String plantaoNew(@ModelAttribute("plantao") Plantao plantao) {
        // Obtém o objeto Medico com base no ID selecionado
        Medico medico = medicoRepository.findById(plantao.getMedico().getId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com o ID: " + plantao.getMedico().getId()));

        // Define o médico no plantão
        plantao.setMedico(medico);

        plantaoRepository.save(plantao);

        return "redirect:/plantoes";
    }

    @GetMapping("/plantoes/update/{id}")
    public String plantaoUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Plantao> optPlantao = plantaoRepository.findById(id);

        if (!optPlantao.isPresent()) {
            throw new RuntimeException("Plantão não encontrado com o ID: " + id);
        }

        Plantao plantao = optPlantao.get();
        model.addAttribute("plantao", plantao);

        List<Medico> medicos = medicoRepository.findAll();
        model.addAttribute("medicos", medicos);

        return "plantao_form";
    }

    @PostMapping("/plantoes/update")
    public String plantaoUpdate(@ModelAttribute("plantao") Plantao plantao) {
        // Obtém o objeto Medico com base no ID selecionado
        Medico medico = medicoRepository.findById(plantao.getMedico().getId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com o ID: " + plantao.getMedico().getId()));

        // Define o médico no plantão
        plantao.setMedico(medico);

        plantaoRepository.save(plantao);

        return "redirect:/plantoes";
    }

    @GetMapping("/plantoes/delete/{id}")
    public String plantaoDelete(@PathVariable("id") Integer id) {
        Optional<Plantao> optPlantao = plantaoRepository.findById(id);

        if (!optPlantao.isPresent()) {
            throw new RuntimeException("Plantão não encontrado com o ID: " + id);
        }

        Plantao plantao = optPlantao.get();
        plantaoRepository.delete(plantao);

        return "redirect:/plantoes";
    }
}
