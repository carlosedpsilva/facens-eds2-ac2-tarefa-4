package br.facens.eds2.domain;

import br.facens.eds2.gateway.repository.entity.AlunoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Aluno {

    private Long id;
    private String nome;
    private int topicosEscritos;
    private int curtidasRecebidas;
    private int cursosComprados;
    private int cursosDisponiveisParaCompra;

    public boolean verificarElegibilidadeParaCurso(int cursosDisponiveisNaPlataforma) {
        return getTopicosEscritos() >= 2
                && getCurtidasRecebidas() >= 5
                && getCursosComprados() < cursosDisponiveisNaPlataforma;
    }

    public AlunoEntity convertToEntity() {
        return AlunoEntity.builder()
                .id(id)
                .nome(nome)
                .topicosEscritos(topicosEscritos)
                .curtidasRecebidas(curtidasRecebidas)
                .cursosComprados(cursosComprados)
                .cursosDisponiveisParaCompra(cursosDisponiveisParaCompra)
                .build();
    }

}
