package bancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoBD {
	
	public Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		    String hostName = "lincoln1206";
		    String dbName = "biblioteca";
		    String user = "lincoln1206";
		    String password = "2012120698lL";
		    String url = String.format("jdbc:sqlserver://%s.database.windows.net:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
		    return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: Falha na conexão com o banco de dados, impossível abrir programa!");
			throw new RuntimeException(e);
		}
	}
}
