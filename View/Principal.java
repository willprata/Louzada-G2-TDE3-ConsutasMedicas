package view;

import controller.*;
import model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PacienteController pacienteController = new PacienteController();
        MedicoController medicoController = new MedicoController();
        ConsultaController consultaController = new ConsultaController();

        while (true) {
            System.out.println("\n------ MENU ------");
            System.out.println("1. Cadastrar Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Alterar Paciente");
            System.out.println("4. Excluir Paciente");
            System.out.println("5. Cadastrar Médico");
            System.out.println("6. Listar Médicos");
            System.out.println("7. Alterar Médico");
            System.out.println("8. Excluir Médico");
            System.out.println("9. Agendar Consulta");
            System.out.println("10. Listar Consultas");
            System.out.println("11. Buscar Consulta por Paciente");
            System.out.println("12. Cancelar Consulta");
            System.out.println("13. Mostrar Pessoas (Polimorfismo)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            try {
                switch (opcao) {
                    case 1: // Cadastrar Paciente
                        System.out.print("Nome do paciente: ");
                        String nomeP = scanner.nextLine();
                        System.out.print("CPF do paciente: ");
                        String cpfP = scanner.nextLine();
                        pacienteController.cadastrarPaciente(new Paciente(nomeP, cpfP));
                        break;

                    case 2: // Listar Pacientes
                        for (Paciente p : pacienteController.listarPacientes()) {
                            p.exibirResumo();
                        }
                        break;

                    case 3: // Alterar Paciente
                        System.out.print("CPF do paciente a alterar: ");
                        String cpfAntigoP = scanner.nextLine();
                        System.out.print("Novo nome: ");
                        String novoNomeP = scanner.nextLine();
                        System.out.print("Novo CPF: ");
                        String novoCpfP = scanner.nextLine();
                        pacienteController.alterarPaciente(cpfAntigoP, new Paciente(novoNomeP, novoCpfP));
                        break;

                    case 4: // Excluir Paciente
                        System.out.print("CPF do paciente a excluir: ");
                        String cpfExcluirP = scanner.nextLine();
                        pacienteController.excluirPaciente(cpfExcluirP);
                        break;

                    case 5: // Cadastrar Médico
                        System.out.print("Nome do médico: ");
                        String nomeM = scanner.nextLine();
                        System.out.print("CPF do médico: ");
                        String cpfM = scanner.nextLine();
                        System.out.print("Especialidade: ");
                        String espM = scanner.nextLine();
                        medicoController.cadastrarMedico(new Medico(nomeM, cpfM, espM));
                        break;

                    case 6: // Listar Médicos
                        for (Medico m : medicoController.listarMedicos()) {
                            m.exibirResumo();
                        }
                        break;

                    case 7: // Alterar Médico
                        System.out.print("CPF do médico a alterar: ");
                        String cpfAntigoM = scanner.nextLine();
                        System.out.print("Novo nome: ");
                        String novoNomeM = scanner.nextLine();
                        System.out.print("Novo CPF: ");
                        String novoCpfM = scanner.nextLine();
                        System.out.print("Nova Especialidade: ");
                        String novaEspM = scanner.nextLine();
                        medicoController.alterarMedico(cpfAntigoM, new Medico(novoNomeM, novoCpfM, novaEspM));
                        break;

                    case 8: // Excluir Médico
                        System.out.print("CPF do médico a excluir: ");
                        String cpfExcluirM = scanner.nextLine();
                        medicoController.excluirMedico(cpfExcluirM);
                        break;

                    case 9: // Agendar Consulta
                        System.out.print("CPF do paciente: ");
                        String cpfPacienteConsulta = scanner.nextLine();
                        System.out.print("CPF do médico: ");
                        String cpfMedicoConsulta = scanner.nextLine();
                        Paciente pacienteConsulta = pacienteController.buscarPacientePorCpf(cpfPacienteConsulta);
                        Medico medicoConsulta = medicoController.buscarMedicoPorCpf(cpfMedicoConsulta);
                        Consulta consulta = new Consulta(pacienteConsulta, medicoConsulta, LocalDateTime.now().plusDays(1));
                        consultaController.agendarConsulta(consulta);
                        break;

                    case 10: // Listar Consultas
                        for (Consulta c : consultaController.listarConsultas()) {
                            c.exibirResumo();
                        }
                        break;

                    case 11: // Buscar Consulta
                        System.out.print("Nome do paciente: ");
                        String nomeBusca = scanner.nextLine();
                        Consulta c = consultaController.buscarConsultaPorPaciente(nomeBusca);
                        c.exibirResumo();
                        break;

                    case 12: // Cancelar Consulta
                        System.out.print("Nome do paciente: ");
                        String nomeCancelamento = scanner.nextLine();
                        Consulta consultaCancelar = consultaController.buscarConsultaPorPaciente(nomeCancelamento);
                        consultaController.cancelarConsulta(consultaCancelar);
                        break;

                    case 13: // Polimorfismo
                        List<Pessoa> pessoas = new ArrayList<>();
                        pessoas.addAll(pacienteController.listarPacientes());
                        pessoas.addAll(medicoController.listarMedicos());
                        for (Pessoa pessoa : pessoas) {
                            pessoa.exibirResumo();
                        }
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (CampoObrigatorioException | ConsultaNaoEncontradaException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
