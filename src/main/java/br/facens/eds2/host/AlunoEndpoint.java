package br.facens.eds2.host;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.facens.eds2.domain.Aluno;
import br.facens.eds2.gateway.AlunoGateway;
import br.facens.eds2.host.dto.CriarAlunoRequest;
import br.facens.eds2.host.handler.ErrorResponse;
import br.facens.eds2.usecase.ConcederCurso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alunos")
public class AlunoEndpoint {

    private final AlunoGateway alunoGateway;
    private final ConcederCurso concederCurso;

    @PostMapping("/criar")
    @Operation(tags = { "aluno" }, description = "Cria um aluno", responses = {
            @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno") })
    public ResponseEntity<Void> criarAluno(@RequestBody CriarAlunoRequest criarAlunoRequest) {
        Aluno aluno = alunoGateway.salvar(criarAlunoRequest.toAluno());
        return ResponseEntity.created(URI.create("/aluno/" + aluno.getId())).build();
    }

    @PutMapping("/{id}/conceder-curso")
    @Operation(tags = { "aluno" }, description = "Concede curso", responses = {
            @ApiResponse(responseCode = "200", description = "Curso concedido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
            @ApiResponse(responseCode = "409", description = "Aluno não elegível para receber curso"),
            @ApiResponse(responseCode = "500", description = "Erro interno") })
    public ResponseEntity<Object> concederCurso(@PathVariable Long id) {
        boolean elegivel = concederCurso.execute(id);
        return elegivel
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(ErrorResponse.builder().userMessage("Aluno não elegível para receber curso").build());
    }

}
