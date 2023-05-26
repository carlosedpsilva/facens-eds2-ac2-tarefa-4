package br.facens.eds2.host;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.facens.eds2.App;
import br.facens.eds2.gateway.repository.AlunoRepository;
import br.facens.eds2.gateway.repository.entity.AlunoEntity;
import br.facens.eds2.host.dto.CriarAlunoRequest;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = App.class)
public class AlunoEndpointTest {

    private static final Long ID = 1L;
    private static final String NOME_ALUNO = "Teste";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("DADO um CriarAlunoRequest "
            + "QUANDO chamado o endpoint de criar o aluno "
            + "ENTÃO deve criar o aluno e retornar 201")
    public void testCriar() throws Exception {
        CriarAlunoRequest request = CriarAlunoRequest.builder().nome("Teste").build();

        when(alunoRepository.save(Mockito.any())).thenReturn(
                AlunoEntity.builder().id(ID).nome(NOME_ALUNO).build());

        mockMvc.perform(post("/api/v1/alunos/criar")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        verify(alunoRepository).save(Mockito.any());
    }

    @Test
    @DisplayName("DADO um ID "
            + "QUANDO chamado o endpoint de conceder curso "
            + "ENTÃO deve executar regra e retornar 200")
    public void testConcederCursoPositivo() throws Exception {
        AlunoEntity alunoEntity = AlunoEntity.builder()
                .id(ID)
                .nome(NOME_ALUNO)
                .topicosEscritos(2)
                .curtidasRecebidas(5)
                .cursosComprados(0)
                .cursosDisponiveisParaCompra(0)
                .build();

        when(alunoRepository.findById(Mockito.any())).thenReturn(Optional.of(alunoEntity));
        when(alunoRepository.save(Mockito.any())).thenReturn(alunoEntity);

        mockMvc.perform(put("/api/v1/alunos/{id}/conceder-curso", ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(alunoRepository).findById(Mockito.any());
        verify(alunoRepository).save(Mockito.any());
    }

    @Test
    @DisplayName("DADO um ID "
            + "QUANDO chamado o endpoint de conceder curso "
            + "ENTÃO deve executar regra e retornar 409")
    public void testConcederCursoNegativo() throws Exception {
        AlunoEntity alunoEntity = AlunoEntity.builder()
                .id(ID)
                .nome(NOME_ALUNO)
                .topicosEscritos(1)
                .curtidasRecebidas(4)
                .cursosComprados(0)
                .cursosDisponiveisParaCompra(0)
                .build();

        when(alunoRepository.findById(Mockito.any())).thenReturn(Optional.of(alunoEntity));

        mockMvc.perform(put("/api/v1/alunos/{id}/conceder-curso", ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andReturn();

        verify(alunoRepository).findById(Mockito.any());
        verify(alunoRepository, never()).save(Mockito.any());
    }

    /**
     * Converte um objeto para JSON
     *
     * @param <T>            Tipo do objeto
     * @param responseObject Objeto a ser convertido
     * @return
     */
    private <T> String asJsonString(T responseObject) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            return objectMapper.writeValueAsString(responseObject);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
