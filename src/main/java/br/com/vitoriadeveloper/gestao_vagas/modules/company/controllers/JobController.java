package br.com.vitoriadeveloper.gestao_vagas.modules.company.controllers;

import br.com.vitoriadeveloper.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.vitoriadeveloper.gestao_vagas.modules.company.entities.JobEntity;
import br.com.vitoriadeveloper.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company/job")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  @PreAuthorize("hasRole('company')")
  public ResponseEntity<Object> create(
    @Valid @RequestBody CreateJobDTO createJobDTO,
    HttpServletRequest request
  ) {
    var companyId = request.getAttribute("company_id");
    try {
      System.out.println("BATEU NO CONTROLLER");
      var jobEntity = JobEntity
        .builder()
        .benefits(createJobDTO.getBenefits())
        .companyId(UUID.fromString(companyId.toString()))
        .description(createJobDTO.getDescription())
        .level(createJobDTO.getLevel())
        .build();

      var result = this.createJobUseCase.execute(jobEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
