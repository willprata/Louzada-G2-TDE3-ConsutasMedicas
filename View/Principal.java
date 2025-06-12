package view;

import controller.ConsultaController;
import model.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultaController controller = new ConsultaController();

        try {
            System.out.print("Nome do paciente: ");
            String nomePaciente = scanner.nextLine();
            System.out.print("CPF do paciente: ");
            String cpfPaciente = scanner.nextLine();
            Paciente paciente = new Paciente(nomePaciente, cpfPaciente);

            System.out.print("Nome do médico: ");
            String nomeMedico = scanner.nextLine();
            System.out.print("CPF do médico: ");
            String cpfMedico = scanner.nextLine();
            System.out.print("Especialidade: ");
            String especialidade = scanner.nextLine();
            Medico medico = new Medico(nomeMedico, cpfMedico, especialidade);

            Consulta consulta = new Consulta(paciente, medico, LocalDateTime.now().plusDays(1));
            controller.agendarConsulta(consulta);

            System.out.println("\nConsultas agendadas:");
            for (Consulta c : controller.listarConsultas()) {
                c.exibirResumo();
            }

        } catch (CampoObrigatorioException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
