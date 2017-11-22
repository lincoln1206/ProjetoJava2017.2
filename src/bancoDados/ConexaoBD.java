package bancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoBD {
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/biblioteca" , "root" , "mysql");
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: Falha na conex�o com o banco de dados, imposs�vel abrir programa!");
			throw new RuntimeException(e);
		}
	}
}
