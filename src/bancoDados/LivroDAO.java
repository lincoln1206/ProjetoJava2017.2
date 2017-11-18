package bancoDados;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import exceptions.DigitouNadaException;
import exceptions.SemDadosException;

public class LivroDAO {

	Connection con = new ConexaoBD().getConnection();

	public LivroDAO() {

	}

	public void criaTabelaLivro() {
		try {
			String sql = "CREATE TABLE livro( " + "codigo int IDENTITY(1,1) primary key," + "titulo varchar(30),"
					+ "editora varchar(30)," + "autor varchar(30)," + "ano varchar(5));";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.execute();
			stmt.close();

			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Tabela criada com sucesso!");

		} catch (SQLException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Falha ao criar tabela livro");
		}

	}

	public void insereLivro(Livro livros) {

		try {

			String sql = "INSERT INTO livro (titulo, editora,autor, ano) VALUES(?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, livros.getTitulo());
			stmt.setString(2, livros.getEditora());
			stmt.setString(3, livros.getAutor());
			stmt.setString(4, livros.getAno());
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Livro inserido com sucesso!");

		} catch (SQLException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null,
					"Falha ao inserir livro!\n\nCAUSA: Provavelmente outro livro já inserido com esse mesmo 'ISBN'");
		}
	}

	public void deletaLivro() {

		try {
			String codigo = JOptionPane.showInputDialog("Digite o código do livro que deseja excluir");

			if (codigo.length() > 0 && codigo != null) {
				String pesquisa = "SELECT titulo FROM livro WHERE codigo=?";
				PreparedStatement pstmt = con.prepareStatement(pesquisa);
				pstmt.setString(1, codigo);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					pstmt.close();
					rs.close();

					String sql = "DELETE FROM livro WHERE codigo=?";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, codigo);
					stmt.executeUpdate();
					stmt.close();

					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Livro deletado com sucesso!");

				} else {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Código de livro não existe na tabela livro!");
					deletaLivro();
				}
			} else if (codigo.length() == 0 && codigo != null) {
				throw new DigitouNadaException();
			}
		} catch (SQLException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Erro ao deletar livro!");
		} catch (DigitouNadaException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "ERRO: Digite algo antes de apertar OK!");
			deletaLivro();
		} catch (NullPointerException e) {
			// VOLTA PARA O MENU
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

						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Titulo modificado com sucesso!");
					} else if (novoTitulo.length() == 0 && novoTitulo != null) {
						throw new DigitouNadaException();
					}
				} else {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "ERRO: Titulo não existe na tabela livro!");
					atualizaTitulo();

				}
			} else if (tituloAntigo.length() == 0 && tituloAntigo != null) {
				throw new DigitouNadaException();
			}
		} catch (NullPointerException e) {
			// VOLTA PARA O MENU
		} catch (DigitouNadaException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "ERRO: Digite algo antes de apertar OK!");
			atualizaTitulo();
		} catch (SQLException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Erro ao atualizar título!");
		}
	}

	public void deletarTabela() {
		try {
			String sql = "DROP TABLE livro";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.execute();
			stmt.close();

			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Tabela 'livro' deletada com sucesso!");
		} catch (SQLException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Erro ao deletar tabela!");
		}
	}

	List<Livro> livros = new ArrayList<Livro>();

	public void obterTabela() {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT *FROM livro");

			int i = 0;

			while (rs.next()) {
				Livro livro = new Livro(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("editora"),
						rs.getString("autor"), rs.getString("ano"));
				livros.add(livro);
				i++;
			}
			if (i == 0) {
				throw new SemDadosException();
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Erro ao obter tabela!");
		} catch (SemDadosException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Tabela vazia!");
		}
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

}
