package lista.funcionariosindustria;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thays
 */


import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa{
    private BigDecimal salario;
    private String funcao;
    
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }
    
    public BigDecimal getSalario() {
        return salario;
    }
    
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
    
    public String getFuncao() {
        return funcao;
    }
    
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    
    @Override
    public String toString() {
        return "Funcionario{" +
               "nome='" + getNome() + '\'' +
               ", dataNascimento=" + getDataNascimento() +
               ", salario=" + salario +
               ", funcao='" + funcao + '\'' +
               '}';
    }
}
