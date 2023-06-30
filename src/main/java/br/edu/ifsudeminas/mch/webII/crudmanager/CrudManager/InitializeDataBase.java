package br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager.model.Medico;
import br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager.model.Plantao;
import br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager.model.dao.MedicoRepository;
import br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager.model.dao.PlantaoRepository;

@Component
@Transactional
public class InitializeDataBase implements CommandLineRunner {
	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PlantaoRepository plantaoRepository;

	@Override
	public void run(String... args) throws Exception {
		Medico alex = new Medico();
		alex.setName("Alex");
		alex.setEmail("alex@gmail.com");
		alex.setGender("M");

		Medico maria = new Medico();
		maria.setName("Maria");
		maria.setEmail("maria@gmail.com");
		maria.setGender("F");

		Medico jose = new Medico();
		jose.setName("jose");
		jose.setEmail("jose@gmail.com");
		jose.setGender("M");

		medicoRepository.save(alex);
		medicoRepository.save(maria);
		medicoRepository.save(jose);

		Plantao plantao1 = new Plantao();
		plantao1.setLocal("Hospital A");
		plantao1.setHorario("08:00 - 14:00");
		plantao1.setMedico(alex);
		plantaoRepository.save(plantao1);

		Plantao plantao2 = new Plantao();
		plantao2.setLocal("Hospital B");
		plantao2.setHorario("14:00 - 20:00");
		plantao2.setMedico(maria);
		plantaoRepository.save(plantao2);

		Plantao plantao3 = new Plantao();
		plantao3.setLocal("Hospital C");
		plantao3.setHorario("20:00 - 02:00");
		plantao3.setMedico(jose);
		plantaoRepository.save(plantao3);

		List<Medico> medicos = medicoRepository.findAll();

		for (Medico medico : medicos) {
			System.out.println("Médico:");
			System.out.println("ID: " + medico.getId());
			System.out.println("Nome: " + medico.getName());
			System.out.println("Email: " + medico.getEmail());
			System.out.println("Gênero: " + medico.getGender());
			System.out.println("Plantão:");
			Plantao plantao = medico.getPlantao();
			if (plantao != null) {
				System.out.println("Local: " + plantao.getLocal());
				System.out.println("Horário: " + plantao.getHorario());
			} else {
				System.out.println("Sem plantão");
			}
			System.out.println();
		}
	}
}
