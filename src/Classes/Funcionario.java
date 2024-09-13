package Classes;

import java.util.Date;

/**
 *
 * @author 291021
 */
public class Funcionario {
   int codigo;
   String nome;
   Date data_nasc;
   char sexo;
   String cpf;
   String endereco;
   float salario;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Funcionario() {
    }

    public Funcionario(String nome, Date data_nasc, char sexo, String cpf, String endereco, float salario) {
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.sexo = sexo;
        this.cpf = cpf;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Funcionario(int codigo, String nome, Date data_nasc, char sexo, String cpf, String endereco, float salario) {
        this.codigo = codigo;
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.sexo = sexo;
        this.cpf = cpf;
        this.endereco = endereco;
        this.salario = salario;
    }
   
   
}