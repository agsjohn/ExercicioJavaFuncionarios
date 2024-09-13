package Main;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 291021
 */
public class ExercicioFuncionarios {
    public static void main(String[] args) {
        ArrayList funcionarios = new ArrayList();
        char esc;
        do{
            esc = JOptionPane.showInputDialog("Digite L para listar \nI para inserir \nE para excluir \nA para atualizar \nP para procurar\nS para sair").charAt(0);
            switch(esc){
                case 'L':
                case 'l':
                    Controle.listarFun(funcionarios);
                    break;
                case 'I':
                case 'i':
                    Controle.inserir();
                    break;
                case 'P':
                case 'p':
                    Controle.procurar();
                    break;
                case 'E':
                case 'e':
                    Controle.excluirFun();
                    break;
                case 'A':
                case 'a':
                    Controle.atualizarFun();
                    break;
            }
            
        }while(esc != 's' && esc != 'S');
    }
}
