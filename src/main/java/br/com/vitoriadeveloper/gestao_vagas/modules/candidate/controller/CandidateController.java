package br.com.vitoriadeveloper.gestao_vagas.modules.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vitoriadeveloper.gestao_vagas.exceptions.UserAlreadyExists;
import br.com.vitoriadeveloper.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.vitoriadeveloper.gestao_vagas.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity){
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(),
        candidateEntity.getEmail()).ifPresent(user -> {
          throw new UserAlreadyExists();
        });
        return  this.candidateRepository.save(candidateEntity);
    }
}
