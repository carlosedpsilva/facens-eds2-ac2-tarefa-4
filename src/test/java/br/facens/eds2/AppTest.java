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

    @Test
    @DisplayName("DADO que o aluno escreveu 2 ou mais tópicos"
            + "E recebeu 5 ou mais curtidas em comentarios em topicos de outros alunos"
            + "E não há mais cursos disponíveis na plataforma para compra"
            + "QUANDO o mês termina"
            + "ENTÃO não ganha o direito de comprar mais um curso")
    public void alunoEscreveu2OuMaisTopicosERecebeu5OuMaisCurtidasEmComentariosENaoHaMaisCursosDisponiveisNaPlataformaQuandoMesTerminaEntaoEleNaoGanhaDireitoDeComprarMaisUmCurso() {
        Aluno aluno = new Aluno();
        aluno.setTopicosEscritos(2);
        aluno.setCurtidasRecebidas(5);
        aluno.setCursosComprados(10);
        aluno.setCursosDisponiveisParaCompra(0);

        aluno.verificarElegibilidadeParaCurso(CURSOS_DISPONIVEIS_NA_PLATAFORMA);

        assertEquals(0, aluno.getCursosDisponiveisParaCompra());
    }

    @Test
    @DisplayName("DADO que o aluno não escreveu 2 ou mais tópicos"
            + "E não recebeu 5 ou mais curtidas em comentarios em topicos de outros alunos"
            + "QUANDO o mês termina"
            + "ENTÃO não ganha o direito de comprar mais um curso")
    public void alunoNaoEscreveu2OuMaisTopicosENaoRecebeu5OuMaisCurtidasEmComentariosQuandoMesTerminaEntaoEleNaoGanhaDireitoDeComprarMaisUmCurso() {
        Aluno aluno = new Aluno();
        aluno.setTopicosEscritos(1);
        aluno.setCurtidasRecebidas(4);
        aluno.setCursosComprados(0);
        aluno.setCursosDisponiveisParaCompra(0);

        aluno.verificarElegibilidadeParaCurso(CURSOS_DISPONIVEIS_NA_PLATAFORMA);

        assertEquals(0, aluno.getCursosDisponiveisParaCompra());
    }

}
