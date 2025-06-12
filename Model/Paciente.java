package model;

public class Paciente extends Pessoa {

    public Paciente(String nome, String cpf) {
        super(nome, cpf);
    }

    @Override
    public void exibirResumo() {
        System.out.println("Paciente: " + nome + " | CPF: " + cpf);
    }
}
