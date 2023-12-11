package br.com.vitoriadeveloper.gestao_vagas.modules.candidate.useCases;

import br.com.vitoriadeveloper.gestao_vagas.exceptions.UserAlreadyExists;
import br.com.vitoriadeveloper.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.vitoriadeveloper.gestao_vagas.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository.findByUsernameOrEmail(
        candidateEntity.getUsername(),
        candidateEntity.getEmail()
      )
      .ifPresent(user -> {
        throw new UserAlreadyExists();
      });
    return this.candidateRepository.save(candidateEntity);
  }
}
