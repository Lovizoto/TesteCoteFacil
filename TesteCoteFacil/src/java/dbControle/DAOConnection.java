/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbControle;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class DAOConnection {

    public java.sql.Connection cn;
    private String driver = "org.postgresql.Driver";
    private String path = "jdbc:postgresql://localhost:5432/dbTeste";
    private String user = "postgres";
    private String pwd = "31415926";

    public java.sql.Connection conectaDB() {
        try {
            System.setProperty("jdcb.Drivers", driver);
//            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection(path, user, pwd);
            return cn;
            // JOptionPane.showMessageDialog(null, "conexao ok");
        } catch (SQLException ex) {
            System.out.println("ex = " + ex);
//            JOptionPane.showMessageDialog(null, "Erro na conexao" + ex.getMessage());
            return null;
        }
    }

    public boolean desconectaDB() {
        try {
            cn.close();
            return true;
            // JOptionPane.showMessageDialog(null, "desconectado com sucesso");
        } catch (SQLException ex) {
            System.out.println("ex = " + ex);
//            JOptionPane.showMessageDialog(null, "Erro na desconexao");
            return false;
        }
    }

}
