package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class ConsultaController {
    private List<Consulta> consultas = new ArrayList<>();

    public void agendarConsulta(Consulta consulta) throws CampoObrigatorioException {
        if (consulta.getPaciente().getNome().isEmpty()) {
            throw new CampoObrigatorioException("Nome do paciente é obrigatório.");
        }
        consultas.add(consulta);
    }

    public List<Consulta> listarConsultas() {
        return consultas;
    }

    public Consulta buscarConsultaPorPaciente(String nomePaciente) throws ConsultaNaoEncontradaException {
        return consultas.stream()
                .filter(c -> c.getPaciente().getNome().equalsIgnoreCase(nomePaciente))
                .findFirst()
                .orElseThrow(() -> new ConsultaNaoEncontradaException("Consulta não encontrada para " + nomePaciente));
    }

    public void cancelarConsulta(Consulta consulta) {
        consultas.remove(consulta);
    }
}
