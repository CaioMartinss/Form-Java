package data;

public class Funcionario {
    //Atributos da classe
    private String matricula;
    private String nome;
    private String cargo;
    private double salario;
    
    //Construtor da classe
    public Funcionario() {
    }

    // Métodos getter e setter
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
