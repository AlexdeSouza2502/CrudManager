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
import br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager.model.dao.MedicoRepository;

@Controller
public class MedicoController {
	@Autowired
	private MedicoRepository medicoRepository;

	@GetMapping("/medicos")
	public String medicos(Model model) {
		List<Medico> medicos = medicoRepository.findAll();
		model.addAttribute("medicos", medicos);
		return "index";
	}
	
	@GetMapping("/medicos/form")
	public String medicoForm(@ModelAttribute("medico") Medico medico) {
		return "medico_form";		
	}
	
	@PostMapping("/medicos/new")
	public String medicoNew(@ModelAttribute("medico") Medico medico) {
		medicoRepository.save(medico);
		return "redirect:/medicos";
	}
	
	@GetMapping("/medicos/update/{id}")
	public String medicoUpdate(@PathVariable("id") Integer id, Model model) {
		Optional<Medico> optMedico = medicoRepository.findById(id);
		
		if (!optMedico.isPresent()) {
			// Gerar erro
		}
		Medico medico = optMedico.get();
		model.addAttribute("medico", medico);
		return "medico_form";
	}
	
	@GetMapping("/medicos/delete/{id}")
	public String medicoDelete(@PathVariable("id") Integer id, Model model) {
		Optional<Medico> optMedico = medicoRepository.findById(id);
		
		if (!optMedico.isPresent()) {
			// Gerar erro
		}
		Medico medico = optMedico.get();
		medicoRepository.delete(medico);
		return "redirect:/medicos";
	}
}
