package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteController {
    private List<Paciente> pacientes = new ArrayList<>();

    public void cadastrarPaciente(Paciente paciente) throws CampoObrigatorioException {
        if (paciente.getNome().isEmpty() || paciente.getCpf().isEmpty()) {
            throw new CampoObrigatorioException("Nome e CPF do paciente são obrigatórios.");
        }
        pacientes.add(paciente);
    }

    public void alterarPaciente(String cpf, Paciente novoPaciente) throws ConsultaNaoEncontradaException {
        Paciente paciente = buscarPacientePorCpf(cpf);
        paciente.nome = novoPaciente.getNome();
        paciente.cpf = novoPaciente.getCpf();
    }

    public void excluirPaciente(String cpf) throws ConsultaNaoEncontradaException {
        Paciente paciente = buscarPacientePorCpf(cpf);
        pacientes.remove(paciente);
    }

    public List<Paciente> listarPacientes() {
        return pacientes;
    }

    public Paciente buscarPacientePorCpf(String cpf) throws ConsultaNaoEncontradaException {
        return pacientes.stream()
            .filter(p -> p.getCpf().equals(cpf))
            .findFirst()
            .orElseThrow(() -> new ConsultaNaoEncontradaException("Paciente não encontrado."));
    }
}
