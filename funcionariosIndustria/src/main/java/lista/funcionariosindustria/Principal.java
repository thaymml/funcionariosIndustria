package lista.funcionariosindustria;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thays
 */

import java.math.BigDecimal;;;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.math.RoundingMode;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        
        funcionarios.removeIf(f -> f.getNome().equals("João"));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Funcionario f : funcionarios) {
            System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %,.2f, Função: %s%n",
                    f.getNome(),
                    f.getDataNascimento().format(formatter),
                    f.getSalario(),
                    f.getFuncao()
            );
        }
        
        for (Funcionario f : funcionarios) {
            BigDecimal novoSalario = f.getSalario().multiply(BigDecimal.valueOf(1.1));
            f.setSalario(novoSalario);
        }
        
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %,.2f%n",
                        f.getNome(),
                        f.getDataNascimento().format(formatter),
                        f.getSalario()
                );
            } 
        }
        System.out.println("Funcionários que fazem aniversário no mês 10 e 12:");
        for (Funcionario f : funcionarios) {
            int mesNascimento = f.getDataNascimento().getMonthValue();
            if (mesNascimento == 10 || mesNascimento == 12) {
                System.out.printf("Nome: %s, DataNascimento: %s%n",
                        f.getNome(),
                        f.getDataNascimento().format(formatter)
                );
            }
        }
        
        Funcionario maisVelho = funcionarios.stream()
                .min((f1, f2) -> f1.getDataNascimento().compareTo(f2.getDataNascimento()))
                .orElse(null);
        
        if (maisVelho != null) {
            int idade = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
            System.out.printf("Funcionário mais velho: Nome: %s, Idade: %d%n",
                    maisVelho.getNome(),
                    idade
            );
        }
        
        System.out.println("Funcionários em ordem alfabética:");
        funcionarios.stream()
                .sorted((f1, f2) -> f1.getNome().compareToIgnoreCase(f2.getNome()))
                .forEach(f -> System.out.printf("Nome: %s%n", f.getNome()));
        
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("Total dos salários: %,.2f%n", totalSalarios);
        
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("Salários mínimos por funcionários:");
        for (Funcionario f :  funcionarios) {
            BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.printf("Nome: %s, Salários mínimos: %,.2f%n", f.getNome(), salariosMinimos);
        }
    }
}
