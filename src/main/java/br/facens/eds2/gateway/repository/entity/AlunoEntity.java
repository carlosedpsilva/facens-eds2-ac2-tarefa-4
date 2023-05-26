package br.facens.eds2.gateway.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.facens.eds2.domain.Aluno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "aluno")
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int topicosEscritos;
    private int curtidasRecebidas;
    private int cursosComprados;
    private int cursosDisponiveisParaCompra;

    public Aluno convertToAluno() {
        return Aluno.builder()
                .id(id)
                .nome(nome)
                .topicosEscritos(topicosEscritos)
                .curtidasRecebidas(curtidasRecebidas)
                .cursosComprados(cursosComprados)
                .cursosDisponiveisParaCompra(cursosDisponiveisParaCompra)
                .build();
    }

}
