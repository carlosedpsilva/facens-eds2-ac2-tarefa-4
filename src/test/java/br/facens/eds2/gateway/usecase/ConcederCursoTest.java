package br.facens.eds2.gateway.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.facens.eds2.domain.Aluno;
import br.facens.eds2.gateway.AlunoGateway;
import br.facens.eds2.usecase.ConcederCurso;
import br.facens.eds2.usecase.impl.ConcederCursoImpl;

public class ConcederCursoTest {

    private static final String NOME_ALUNO = "Teste";

    @Mock
    private AlunoGateway alunoGateway;

    private ConcederCurso concederCurso;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        concederCurso = new ConcederCursoImpl(alunoGateway);
    }

    @Test
    @DisplayName("DADO um id "
            + "QUANDO for executado e o aluno for elegível para receber um curso "
            + "ENTÃO deve conceder o curso e retornar true")
    public void testConcederCursoPositivo() {
        Long id = 1L;
        Aluno aluno = Aluno.builder()
                .id(id)
                .nome(NOME_ALUNO)
                .topicosEscritos(2)
                .curtidasRecebidas(5)
                .cursosComprados(0)
                .cursosDisponiveisParaCompra(0)
                .build();

        when(alunoGateway.buscarPorId(Mockito.anyLong())).thenReturn(aluno);
        when(alunoGateway.salvar(Mockito.any())).thenReturn(aluno);

        boolean elegivel = concederCurso.execute(id);
        assertTrue(elegivel);

        verify(alunoGateway).buscarPorId(id);
        verify(alunoGateway).salvar(aluno); // Se chamado, o curso foi concedido
    }

    @Test
    @DisplayName("DADO um id "
            + "QUANDO for executado e o aluno não for elegível para receber um curso "
            + "ENTÃO deve não conceder o curso e retornar false")
    public void testConcederCursoNegativo() {
        Long id = 1L;
        Aluno aluno = Aluno.builder()
                .id(id)
                .nome(NOME_ALUNO)
                .topicosEscritos(1)
                .curtidasRecebidas(5)
                .cursosComprados(0)
                .cursosDisponiveisParaCompra(0)
                .build();

        when(alunoGateway.buscarPorId(Mockito.anyLong())).thenReturn(aluno);

        boolean elegivel = concederCurso.execute(id);
        assertFalse(elegivel);

        verify(alunoGateway).buscarPorId(id);
        verify(alunoGateway, never()).salvar(aluno);
    }

}
