package br.com.vitoriadeveloper.gestao_vagas.modules.candidate.controller;

import br.com.vitoriadeveloper.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.vitoriadeveloper.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.vitoriadeveloper.gestao_vagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import br.com.vitoriadeveloper.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.vitoriadeveloper.gestao_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @Autowired
  private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

  @PostMapping
  public ResponseEntity<Object> create(
    @Valid @RequestBody CandidateEntity candidateEntity
  ) {
    try {
      var result = this.createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping
  @PreAuthorize("hasRole('candidate')")
  public ResponseEntity<Object> get(HttpServletRequest request) {
    var idCandidato = request.getAttribute("candidate_id");
    try {
      var profile =
        this.profileCandidateUseCase.execute(
            UUID.fromString(idCandidato.toString())
          );
      return ResponseEntity.ok().body(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/job")
  @PreAuthorize("hasRole('candidate')")
  @Tag(
    name = "Candidato",
    description = "Endpoint relacionado a informações de vagas do candidato"
  )
  @Operation(
    summary = "Buscar vagas por filtro",
    description = "Este endpoint permite buscar vagas com base em um filtro de descrição."
  )
  @ApiResponse(
    responseCode = "200",
    content = {
      @Content(
        array = @ArraySchema(schema = @Schema(implementation = JobEntity.class))
      ),
    }
  )
  public ResponseEntity<Object> findJobByFilter(@RequestParam String filter) {
    try {
      var jobs = this.listAllJobsByFilterUseCase.execute(filter);
      return ResponseEntity.ok().body(jobs);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
