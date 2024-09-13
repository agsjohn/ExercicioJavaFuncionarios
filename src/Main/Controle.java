package Main;

import Classes.Funcionario;
import DAO.FuncionarioDAO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author 291021
 */
public class Controle {
    
    public static void listarFun(ArrayList<Funcionario> funcionarios) {
        try {
            FuncionarioDAO funDAO = new FuncionarioDAO();
            funcionarios = funDAO.listar();
        } catch (Exception ex) {
            System.out.println("problema");
        }
        String msg = "Lista de funcionários: \n";
        int tamanho = funcionarios.size();
        Funcionario funcionario = new Funcionario();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (tamanho == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum funcionário salvo");
        } else {
            for (int i = 0; i < tamanho; i++) {

                msg = msg + "Código: " + funcionarios.get(i).getCodigo();
                msg = msg + "\nNome: " + funcionarios.get(i).getNome();
                msg = msg + "\nData de Nascimento: " + sdf.format(funcionarios.get(i).getData_nasc());
                msg = msg + "\nSexo: " + funcionarios.get(i).getSexo();
                msg = msg + "\nCpf: " + funcionarios.get(i).getCpf();
                msg = msg + "\nEndereço: " + funcionarios.get(i).getEndereco();
                msg = msg + "\nSalário: " + funcionarios.get(i).getSalario();
                msg = msg + "\n___________________________________________________ \n";
            }
            JOptionPane.showMessageDialog(null, msg);
        }
    }

    public static void inserir() {

        Funcionario funcionario = new Funcionario();

        funcionario.setNome(JOptionPane.showInputDialog("Digite o nome: "));
        DateFormat dtOutput = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dt = dtOutput.parse(JOptionPane.showInputDialog("Digite a data de Nascimento: "));
            funcionario.setData_nasc(dt);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        funcionario.setSexo(JOptionPane.showInputDialog("Digite o sexo: ").charAt(0));
        funcionario.setCpf(JOptionPane.showInputDialog("Digite o cpf: "));
        funcionario.setEndereco(JOptionPane.showInputDialog("Digite o endereço: "));
        funcionario.setSalario(Float.parseFloat(JOptionPane.showInputDialog("Digite o salário: ")));
        FuncionarioDAO fdao = new FuncionarioDAO();
        fdao.inserir(funcionario);

    }

    public static void procurar() {

        int codigo = 0;
        Funcionario funcionario = new Funcionario();

        codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do funcionário para localizar"));

        try {
            FuncionarioDAO x = new FuncionarioDAO();
            funcionario = x.procurar(codigo);
        } catch (Exception ex) {
            System.out.println("problema");
        }
        String msg = "Dados do funcionário com ID indicado \n";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (funcionario == null) {
            JOptionPane.showMessageDialog(null, "Não encontrado !!");
        } else {
            msg = msg + "Código: " + funcionario.getCodigo();
            msg = msg + "\nNome: " + funcionario.getNome();
            msg = msg + "\nData de Nascimento: " + sdf.format(funcionario.getData_nasc());
            msg = msg + "\nSexo: " + funcionario.getSexo();
            msg = msg + "\nCpf: " + funcionario.getCpf();
            msg = msg + "\nEndereço: " + funcionario.getEndereco();
            msg = msg + "\nSalário: " + funcionario.getSalario();
            msg = msg + "\n___________________________________________________ \n";
            JOptionPane.showMessageDialog(null, msg);
        }
    }

    public static void excluirFun() {
        Funcionario funcionario = new Funcionario();
        FuncionarioDAO fdao = new FuncionarioDAO();
        int codigo;
        codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do funcionário a excluir"));
        funcionario = fdao.procurar(codigo);
        if (funcionario != null) {
            fdao.excluir(funcionario);
            JOptionPane.showMessageDialog(null, "O funcionário com o codigo " + codigo + " foi excluido com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "O funcionário com o codigo " + codigo + " não foi localizado.");
        }
    }

    public static void atualizarFun() {
        Funcionario funcionario = new Funcionario();
        FuncionarioDAO fdao = new FuncionarioDAO();
        int codigo;
        DateFormat dtOutput = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do funcionário a atualizar"));
        funcionario = fdao.procurar(codigo);
        if (funcionario != null) {
            funcionario.setCodigo(codigo);
            funcionario.setNome(JOptionPane.showInputDialog(null, "Digite o nome: ", funcionario.getNome()));
            try {
                Date dt = dtOutput.parse(JOptionPane.showInputDialog(null, "Digite a data de Nascimento: ", sdf.format(funcionario.getData_nasc())));
                funcionario.setData_nasc(dt);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            funcionario.setSexo(JOptionPane.showInputDialog(null, "Digite o sexo: ", funcionario.getSexo()).charAt(0));
            funcionario.setCpf(JOptionPane.showInputDialog(null, "Digite o cpf: ", funcionario.getCpf()));
            funcionario.setEndereco(JOptionPane.showInputDialog(null, "Digite o endereço: ", funcionario.getEndereco()));
            funcionario.setSalario(Float.parseFloat(JOptionPane.showInputDialog(null, "Digite o salário: ", funcionario.getSalario())));
            fdao.atualizar(funcionario);
        } else {
            JOptionPane.showMessageDialog(null, "O funcionário com o codigo " + codigo + " não foi localizado.");
        }
    }
}