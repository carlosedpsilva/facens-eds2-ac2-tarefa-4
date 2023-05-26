package br.facens.eds2.gateway;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.query.InvalidJpaQueryMethodException;

import br.facens.eds2.domain.Aluno;
import br.facens.eds2.exception.DataBaseComunicationException;
import br.facens.eds2.gateway.impl.AlunoGatewayImpl;
import br.facens.eds2.gateway.repository.AlunoRepository;
import br.facens.eds2.gateway.repository.entity.AlunoEntity;

public class AlunoGatewayTest {

    private static final Long ID = 1L;
    private static final String NOME_ALUNO = "Teste";

    @Mock
    private AlunoRepository alunoRepository;

    private AlunoGateway alunoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        alunoGateway = new AlunoGatewayImpl(alunoRepository);
    }

    @Test
    @DisplayName("DADO um aluno "
            + "QUANDO for executado o save no banco "
            + "ENTÃO deve salvar o aluno sem erros")
    void testSave() {
        Aluno aluno = Aluno.builder().nome(NOME_ALUNO).build();

        when(alunoRepository.save(Mockito.any())).thenThrow(InvalidJpaQueryMethodException.class);

        assertThrows(DataBaseComunicationException.class, () -> alunoGateway.salvar(aluno));

        verify(alunoRepository).save(refEq(aluno.convertToEntity()));
    }

    @Test
    @DisplayName("DADO um aluno "
            + "QUANDO for executado o save no banco e for dado um erro ao salvar na base "
            + "ENTÃO deve ser lançado um DataBaseComunicationException")
    void testSaveWithException() {
        Aluno aluno = Aluno.builder().nome(NOME_ALUNO).build();

        when(alunoRepository.save(Mockito.any())).thenThrow(InvalidJpaQueryMethodException.class);

        assertThrows(DataBaseComunicationException.class, () -> alunoGateway.salvar(aluno));

        verify(alunoRepository).save(refEq(aluno.convertToEntity()));
    }

    @Test
    @DisplayName("DADO um ID "
            + "QUANDO for executado a busca no banco  "
            + "ENTÃO deve retornar o aluno sem erros")
    void testFind() {
        AlunoEntity alunoEntity = AlunoEntity.builder().id(ID).nome(NOME_ALUNO).build();
        when(alunoRepository.findById(Mockito.any())).thenReturn(Optional.of(alunoEntity));

        Aluno alunoReturned = alunoGateway.buscarPorId(ID);

        assertEquals(alunoReturned.getId(), alunoEntity.getId());

        verify(alunoRepository).findById(ID);
    }

    @Test
    @DisplayName("DADO um ID "
            + "QUANDO for executado a busca no banco e for dado um erro ao buscar na base "
            + "ENTÃO deve ser lançado um InvalidJpaQueryMethodException")
    void testFindWithException() {
        when(alunoRepository.findById(Mockito.any())).thenThrow(DataBaseComunicationException.class);

        assertThrows(DataBaseComunicationException.class, () -> alunoGateway.buscarPorId(ID));

        verify(alunoRepository).findById(ID);
    }

}
