package DAO;

import Classes.Funcionario;
import Util.conexaoAulaDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 291021
 */
public class FuncionarioDAO {
    private Connection conn;

    public FuncionarioDAO() {
        try {
            this.conn = conexaoAulaDAO.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão: " + ":\n" + e.getMessage());
        }
    }
    
    public ArrayList listar() {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList funcionarios = new ArrayList();
        try {
            String SQL = "SELECT * FROM funcionarios";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                Date data_nasc = rs.getDate("data_nasc");
                char sexo;
                if (rs.getString("sexo") == null) {
                    sexo = ' ';
                } else {
                    sexo = (rs.getString("sexo")).charAt(0);
                }
                String cpf = rs.getString("cpf");
                String endereco = rs.getString("endereco");
                float salario = rs.getFloat("salario");
                funcionarios.add(new Funcionario(codigo, nome, data_nasc, sexo, cpf, endereco, salario));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar funcionarios " + sqle);
        } finally {
            conexaoAulaDAO.close(connL, ps);
        }
        return funcionarios;
    }

    public void inserir(Funcionario funcionario) {
        PreparedStatement ps = null;
        Connection connL = null;

        if (funcionario == null) {
            JOptionPane.showMessageDialog(null, "O objeto funcionário não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO funcionarios (nome, data_nasc, sexo, cpf, endereco, salario) "
                    + "values (?,?,?,?,?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, funcionario.getNome());
            java.util.Date dataJAVA = funcionario.getData_nasc();  // Data da classe Java Util
            java.sql.Date dataSQL = new java.sql.Date(dataJAVA.getTime()); // Data da classe SQL
            ps.setDate(2, dataSQL);
            ps.setString(3, Character.toString(funcionario.getSexo()));
            ps.setString(4, funcionario.getCpf());
            ps.setString(5, funcionario.getEndereco());
            ps.setString(6, Float.toString(funcionario.getSalario()));
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir um novo funcionário" + sqle);
        } finally {
            conexaoAulaDAO.close(connL, ps);
        }
    }

    public Funcionario procurar(int codigo) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        Funcionario funcionario = new Funcionario();
        funcionario = null;
        try {
            String SQL = "SELECT * FROM funcionarios WHERE codigo = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
//                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                Date data_nasc = rs.getDate("data_nasc");
                char sexo;
                if (rs.getString("sexo") == null) {
                    sexo = ' ';
                } else {
                    sexo = (rs.getString("sexo")).charAt(0);
                }
                String cpf = rs.getString("cpf");
                String endereco = rs.getString("endereco");
                float salario = rs.getFloat("salario");
                funcionario = new Funcionario(codigo, nome, data_nasc, sexo, cpf, endereco, salario);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar agenda " + sqle);
        } finally {
           // conexaoAulaDAO.close(connL, ps);
        }
        return funcionario;
    }

    public void atualizar(Funcionario funcionario) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (funcionario == null) {
            JOptionPane.showMessageDialog(null, "O objeto funcionário não pode ser nulo.");
        }
        try {
            String SQL = "UPDATE funcionarios set nome=?, data_nasc=?, sexo=?, cpf=?, endereco=?, salario=? WHERE codigo=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, funcionario.getNome());
            java.util.Date dataJAVA = funcionario.getData_nasc();  // Data da classe Java Util
            java.sql.Date dataSQL = new java.sql.Date(dataJAVA.getTime()); // Data da classe SQL
            ps.setDate(2, dataSQL);
            ps.setString(3, Character.toString(funcionario.getSexo()));
            ps.setString(4, funcionario.getCpf());
            ps.setString(5, funcionario.getEndereco());
            ps.setString(6, Float.toString(funcionario.getSalario()));
            ps.setInt(7, funcionario.getCodigo());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao editar funcionário " + sqle);
        } finally {
            conexaoAulaDAO.close(connL, ps);
        }
    }

    public void excluir(Funcionario funcionario) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (funcionario == null) {
            JOptionPane.showMessageDialog(null, "O objeto funcionário não pode ser nulo.");
        }
        try {
            String SQL = "DELETE FROM funcionarios WHERE codigo=?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, funcionario.getCodigo());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir funcionário " + sqle);
        } finally {
            conexaoAulaDAO.close(connL, ps);
        }
    }
}