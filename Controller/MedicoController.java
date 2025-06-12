package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoController {
    private List<Medico> medicos = new ArrayList<>();

    public void cadastrarMedico(Medico medico) throws CampoObrigatorioException {
        if (medico.getNome().isEmpty() || medico.getCpf().isEmpty() || medico.getEspecialidade().isEmpty()) {
            throw new CampoObrigatorioException("Nome, CPF e especialidade do médico são obrigatórios.");
        }
        medicos.add(medico);
    }

    public void alterarMedico(String cpf, Medico novoMedico) throws ConsultaNaoEncontradaException {
        Medico medico = buscarMedicoPorCpf(cpf);
        medico.nome = novoMedico.getNome();
        medico.cpf = novoMedico.getCpf();
        medico.especialidade = novoMedico.getEspecialidade();
    }

    public void excluirMedico(String cpf) throws ConsultaNaoEncontradaException {
        Medico medico = buscarMedicoPorCpf(cpf);
        medicos.remove(medico);
    }

    public List<Medico> listarMedicos() {
        return medicos;
    }

    public Medico buscarMedicoPorCpf(String cpf) throws ConsultaNaoEncontradaException {
        return medicos.stream()
            .filter(m -> m.getCpf().equals(cpf))
            .findFirst()
            .orElseThrow(() -> new ConsultaNaoEncontradaException("Médico não encontrado."));
    }
}
