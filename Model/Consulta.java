package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consulta implements Exibivel {
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime dataHora;

    public Consulta(Paciente paciente, Medico medico, LocalDateTime dataHora) {
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    @Override
    public void exibirResumo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Consulta de " + paciente.getNome() + " com Dr(a). " + medico.getNome() +
                           " em " + dataHora.format(formatter));
    }
}
