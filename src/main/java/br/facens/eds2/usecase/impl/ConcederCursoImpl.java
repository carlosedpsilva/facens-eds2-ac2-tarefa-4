package br.facens.eds2.usecase.impl;

import org.springframework.stereotype.Component;

import br.facens.eds2.domain.Aluno;
import br.facens.eds2.gateway.AlunoGateway;
import br.facens.eds2.usecase.ConcederCurso;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ConcederCursoImpl implements ConcederCurso {

    private final AlunoGateway alunoGateway;
    // private final CursoGateway cursoGateway;

    private static final int CURSOS_DISPONIVEIS_NA_PLATAFORMA = 10;

    @Override
    public boolean execute(Long id) {
        Aluno aluno = alunoGateway.buscarPorId(id);
        // Integer cursosDisponieisNaPlataforma = cursoGateway.contarCursosDisponiveisNaPlataforma();
        boolean elegivel = aluno.verificarElegibilidadeParaCurso(CURSOS_DISPONIVEIS_NA_PLATAFORMA);

        if (elegivel) {
            int cursosDisponiveisParaCompra = aluno.getCursosDisponiveisParaCompra();
            aluno.setCursosDisponiveisParaCompra(cursosDisponiveisParaCompra + 1);
            alunoGateway.salvar(aluno);
        }

        return elegivel;
    }

}
