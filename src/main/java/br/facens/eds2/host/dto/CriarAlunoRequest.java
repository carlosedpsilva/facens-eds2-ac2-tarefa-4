package br.facens.eds2.host.dto;

import javax.validation.constraints.NotBlank;

import br.facens.eds2.domain.Aluno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CriarAlunoRequest {

    @NotBlank
    private String nome;

    public Aluno toAluno() {
        return Aluno.builder().nome(nome).build();
    }

}
