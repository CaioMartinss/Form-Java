package data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class FuncionarioDao {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public FuncionarioDao() {
    }

    public boolean conectar() {
        try {
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            //conn = DriverManager.getConnection("jdbc:sqlserver://localhost;database=banco;integratedSecurity=true;");  
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "root", "");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
    }

    public int salvar(Funcionario funcionario) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO funcionario VALUES(?,?,?,?)");
            st.setString(1, funcionario.getMatricula());
            st.setString(2, funcionario.getNome());
            st.setString(3, funcionario.getCargo());
            st.setDouble(4, funcionario.getSalario());
            status = st.executeUpdate();
            return status; //retorna 1
        } catch (SQLException ex) {
            return ex.getErrorCode();
            //1062 tentativa de inserir uma matrícula já cadastrada.
        }
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {

        }
    }

    public Funcionario consultar(String matricula) {
        try {
            Funcionario funcionario = new Funcionario();
            st = conn.prepareStatement("SELECT * FROM funcionario WHERE matricula = ?");
            st.setString(1, matricula);
            rs = st.executeQuery();
            // verifica se a consulta encontrou o funcionário com a matrícula informada
            if (rs.next()) { // se encontrou o funcionário
                funcionario.setMatricula(rs.getString("matricula"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setSalario(rs.getDouble("salario"));
                return funcionario;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }

    public boolean excluir(String matricula) {

        try {
            st = conn.prepareStatement(" DELETE FROM funcionario WHERE matricula = ? ");
            st.setString(1, matricula);
            st.executeUpdate();
            
            return true;

            //if( == true){
            //JOptionPane.showMessageDialog(null, "cadastro excluido com sucesso");
            //}
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "não foi possivel excluir");
        }
        return false;
    }
    
//    public int Update(Funcionario funcionario){
//        
//    }

}
