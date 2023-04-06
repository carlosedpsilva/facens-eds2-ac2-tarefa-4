package br.facens.eds2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {

    private static final int CURSOS_DISPONIVEIS_NA_PLATAFORMA = 10;

    @Test
    @DisplayName("DADO que o aluno escreveu 2 ou mais tópicos"
            + "E recebeu 5 ou mais curtidas em comentarios em topicos de outros alunos"
            + "E ainda há cursos disponíveis na plataforma para compra"
            + "QUANDO o mês termina"
            + "ENTÃO não ganha o direito de comprar mais um curso")
    public void alunoEscreveu2OuMaisTopicosERecebeu5OuMaisCurtidasEmComentariosEHaCursosDisponiveisNaPlataformaQuandoMesTerminaEntaoEleGanhaDireitoDeComprarMaisUmCurso() {
        Aluno aluno = new Aluno();
        aluno.setTopicosEscritos(2);
        aluno.setCurtidasRecebidas(5);
        aluno.setCursosComprados(0);
        aluno.setCursosDisponiveisParaCompra(0);

        aluno.verificarElegibilidadeParaCurso(CURSOS_DISPONIVEIS_NA_PLATAFORMA);

        assertEquals(1, aluno.getCursosDisponiveisParaCompra());
    }

}
