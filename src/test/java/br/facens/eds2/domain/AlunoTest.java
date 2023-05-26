package br.facens.eds2.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AlunoTest {

    private static final int CURSOS_DISPONIVEIS_NA_PLATAFORMA = 10;

    @Test
    @DisplayName("DADO que o aluno escreveu 2 ou mais tópicos "
            + "E recebeu 5 ou mais curtidas em comentarios em topicos de outros alunos "
            + "E ainda há cursos disponíveis na plataforma para compra "
            + "QUANDO o mês termina "
            + "ENTÃO não ganha o direito de comprar mais um curso")
    public void testVerificarElegibilidadePositivo() {
        Aluno aluno = Aluno.builder()
                .topicosEscritos(2)
                .curtidasRecebidas(5)
                .cursosComprados(0)
                .build();

        boolean elegivel = aluno.verificarElegibilidadeParaCurso(CURSOS_DISPONIVEIS_NA_PLATAFORMA);
        assertEquals(true, elegivel);
    }

    @Test
    @DisplayName("DADO que o aluno escreveu 2 ou mais tópicos "
            + "E recebeu 5 ou mais curtidas em comentarios em topicos de outros alunos "
            + "E não há mais cursos disponíveis na plataforma para compra "
            + "QUANDO o mês termina "
            + "ENTÃO não ganha o direito de comprar mais um curso")
    public void testVerificarElegibilidadeNegativo1() {
        Aluno aluno = Aluno.builder()
                .topicosEscritos(2)
                .curtidasRecebidas(5)
                .cursosComprados(10)
                .build();

        boolean elegivel = aluno.verificarElegibilidadeParaCurso(CURSOS_DISPONIVEIS_NA_PLATAFORMA);
        assertEquals(false, elegivel);
    }

    @Test
    @DisplayName("DADO que o aluno não escreveu 2 ou mais tópicos "
            + "E não recebeu 5 ou mais curtidas em comentarios em topicos de outros alunos "
            + "QUANDO o mês termina "
            + "ENTÃO não ganha o direito de comprar mais um curso")
    public void testVerificarElegibilidadeNegativo2() {
        Aluno aluno = Aluno.builder()
                .topicosEscritos(1)
                .curtidasRecebidas(4)
                .cursosComprados(0)
                .build();

        boolean elegivel = aluno.verificarElegibilidadeParaCurso(CURSOS_DISPONIVEIS_NA_PLATAFORMA);
        assertEquals(false, elegivel);
    }

    @Test
    @DisplayName("DADO que o aluno escreveu 2 ou mais tópicos "
            + "E não recebeu 5 ou mais curtidas em comentarios em topicos de outros alunos "
            + "QUANDO o mês termina "
            + "ENTÃO não ganha o direito de comprar mais um curso")
    public void testVerificarElegibilidadeNegativo3() {
        Aluno aluno = Aluno.builder()
                .topicosEscritos(2)
                .curtidasRecebidas(4)
                .cursosComprados(0)
                .build();

        boolean elegivel = aluno.verificarElegibilidadeParaCurso(CURSOS_DISPONIVEIS_NA_PLATAFORMA);
        assertEquals(false, elegivel);
    }

    @Test
    @DisplayName("DADO que o aluno não escreveu 2 ou mais tópicos "
            + "E recebeu 5 ou mais curtidas em comentarios em topicos de outros alunos "
            + "QUANDO o mês termina "
            + "ENTÃO não ganha o direito de comprar mais um curso")
    public void testVerificarElegibilidadeNegativo4() {
        Aluno aluno = Aluno.builder()
                .topicosEscritos(1)
                .curtidasRecebidas(5)
                .cursosComprados(0)
                .build();

        boolean elegivel = aluno.verificarElegibilidadeParaCurso(CURSOS_DISPONIVEIS_NA_PLATAFORMA);
        assertEquals(false, elegivel);
    }

}
