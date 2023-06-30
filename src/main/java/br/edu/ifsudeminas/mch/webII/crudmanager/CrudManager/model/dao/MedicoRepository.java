package br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsudeminas.mch.webII.crudmanager.CrudManager.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer>{

}
