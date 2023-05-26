package br.facens.eds2.gateway.impl;

import org.springframework.stereotype.Component;

import br.facens.eds2.domain.Aluno;
import br.facens.eds2.exception.DataBaseComunicationException;
import br.facens.eds2.gateway.AlunoGateway;
import br.facens.eds2.gateway.repository.AlunoRepository;
import br.facens.eds2.gateway.repository.entity.AlunoEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AlunoGatewayImpl implements AlunoGateway {

    private final AlunoRepository alunoRepository;

    @Override
    public Aluno salvar(Aluno aluno) {
        try {
            return alunoRepository.save(aluno.convertToEntity()).convertToAluno();
        } catch (Exception e) {
            throw new DataBaseComunicationException(String.format("Erro ao salvar aluno %s na base", aluno.getNome()), e);
        }
    }

    @Override
    public Aluno buscarPorId(Long id) {
        try {
            return alunoRepository.findById(id).map(AlunoEntity::convertToAluno).orElse(null);
        } catch (Exception e) {
            throw new DataBaseComunicationException(String.format("Erro ao buscar aluno por id %s na base", id), e);
        }
    }

}
