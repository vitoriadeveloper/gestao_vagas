package br.com.vitoriadeveloper.gestao_vagas.modules.company.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vitoriadeveloper.gestao_vagas.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID>{
    // contains / LIKE %filter%
    List<JobEntity> findByDescriptionContaining(String filter);

    
}
