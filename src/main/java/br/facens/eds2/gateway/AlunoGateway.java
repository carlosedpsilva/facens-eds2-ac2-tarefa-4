package br.facens.eds2.gateway;

import br.facens.eds2.domain.Aluno;

public interface AlunoGateway {

    Aluno salvar(Aluno aluno);
    Aluno buscarPorId(Long id);

}
