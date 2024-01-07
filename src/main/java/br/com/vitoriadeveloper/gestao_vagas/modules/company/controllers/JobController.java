package br.com.vitoriadeveloper.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vitoriadeveloper.gestao_vagas.modules.company.entities.JobEntity;
import br.com.vitoriadeveloper.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create( @Valid @RequestBody JobEntity jobEntity, HttpServletRequest request) {
      try {
        var companyId = request.getAttribute("company_id");
        jobEntity.setCompanyId(UUID.fromString(companyId.toString()));
        var result = createJobUseCase.execute(jobEntity);
        return ResponseEntity.ok().body(result);
      } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    }
    
}
