package bancoDados;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoBD {

	/*Connection que faz a conexão com o banco de dados*/
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/biblioteca" , "root" , "mysql" );
		} catch (ClassNotFoundException | SQLException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "ERRO: Falha na conexão com o banco de dados, impossível abrir programa!");
			throw new RuntimeException(e);
		}
		
	}
}
