package bancoDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class LivroDAO {

	Connection con = new ConexaoBD().getConnection();

	public LivroDAO() {

	}

	public void criaTabelaLivro() {
		try {
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			String sql = "create table livro( " + "isbn int primary key," + "titulo varchar(30),"
					+ "editora varchar(30)," + "ano int );";
			stmt.execute(sql);
			con.commit();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Tabela criada com sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Falha ao criar tabela livro");
			throw new RuntimeException(e);
		}

	}

	public void insereLivro(Livro livros) {

		try {

			String sql = "INSERT INTO livro (isbn,titulo, editora, ano) VALUES(?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, livros.getIsbn());
			stmt.setString(2, livros.getTitulo());
			stmt.setString(3, livros.getEditora());
			stmt.setInt(4, livros.getAno());
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Livro inserido com sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Falha ao inserir livro!\n\nCAUSA: Outro livro já inserido com esse mesmo 'ISBN'");
			throw new RuntimeException(e);
		}
	}

	public void deletaLivro() {

		try {
			String titulo = JOptionPane.showInputDialog("Digite o título do livro que deseja excluir");

			if (titulo.length() > 0 && titulo != null) {
				String pesquisa = "SELECT titulo FROM livro WHERE titulo=?";
				PreparedStatement pstmt = con.prepareStatement(pesquisa);
				pstmt.setString(1, titulo);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					pstmt.close();
					rs.close();

					String sql = "DELETE FROM livro WHERE titulo=?";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, titulo);
					stmt.executeUpdate();
					stmt.close();

					JOptionPane.showMessageDialog(null, "Livro deletado com sucesso!");

				} else {
					JOptionPane.showMessageDialog(null, "Titulo não existe na tabela livro!");
					deletaLivro();
				}
			}else if(titulo.length() == 0 && titulo != null) {
				throw new DigitouNadaException();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar livro!");
			throw new RuntimeException(e);
		}catch (DigitouNadaException e) {
			JOptionPane.showMessageDialog(null, "ERRO: Digite algo antes de apertar OK!");
			deletaLivro();
		}catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "OPERAÇÃO CANCELADA!");
		}

	}

	public void atualizaTitulo() {
		try {
			String tituloAntigo = JOptionPane.showInputDialog("Digite o titulo do livro que deseja atualizar o titulo");
			if (tituloAntigo.length() > 0 && tituloAntigo != null) {
				String pesquisa = "SELECT titulo FROM livro WHERE titulo=?";
				PreparedStatement pstmt = con.prepareStatement(pesquisa);
				pstmt.setString(1, tituloAntigo);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					pstmt.close();
					rs.close();

					String novoTitulo = JOptionPane.showInputDialog("Digite o novo titulo");
					if (novoTitulo.length() > 0 && novoTitulo != null) {
						String sql = "UPDATE livro SET titulo=? WHERE titulo=?";
						PreparedStatement stmt = con.prepareStatement(sql);
						stmt.setString(1, novoTitulo);
						stmt.setString(2, tituloAntigo);
						stmt.executeUpdate();
						stmt.close();

						JOptionPane.showMessageDialog(null, "Titulo modificado com sucesso!");
					} else if (novoTitulo.length() == 0 && novoTitulo != null) {
						throw new DigitouNadaException();
					}
				} else {
					JOptionPane.showMessageDialog(null, "ERRO: Titulo não existe na tabela livro!");
					atualizaTitulo();

				}
			} else if (tituloAntigo.length() == 0 && tituloAntigo != null) {
				throw new DigitouNadaException();
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "OPERAÇÃO CANCELADA!");
		} catch (DigitouNadaException e) {
			JOptionPane.showMessageDialog(null, "ERRO: Digite algo antes de apertar OK!");
			atualizaTitulo();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar título!");
			throw new RuntimeException(e);
		}
	}
	
	public void deletarTabela() {
		try {
			String sql = "DROP TABLE livro";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Tabela 'livro' deletada com sucesso!");
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar tabela!");
			throw new RuntimeException(e);
		}
	}
	
	public void mostarTabela() {
		try {
			String sql = "SELECT *FROM livro";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList<Livro> array = new ArrayList<>();
			
			int i = 0;
			
			System.out.println("\tTABELA LIVROS\n");
			while(rs.next()) {
				System.out.println("\n");
				System.out.println("ISBN :"+rs.getInt("isbn"));
				System.out.println("TITULO :"+rs.getString("titulo"));
				System.out.println("EDITORA :"+rs.getString("editora"));
				System.out.println("ANO :"+rs.getInt("ano"));
				i++;
			}
			
			System.out.println("\nVocê tem " + i + " livros na sua biblioteca!");
			
			stmt.close();
			rs.close();
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao mostrar tabela!");
			throw new RuntimeException(e);
		}
	}

}


