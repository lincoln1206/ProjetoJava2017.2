package interfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import bancoDados.Livro;
import bancoDados.LivroDAO;
import net.miginfocom.swing.MigLayout;

public class InterfaceGraficaInserir {

	Livro livro = new Livro();
	LivroDAO banco = new LivroDAO();
	
	JFrame janela = new JFrame("Inserir Livro");
	JPanel panel = new JPanel();
	
	JLabel lIsbn = new JLabel(" ISBN");
	JLabel lTitulo = new JLabel(" TÍTULO");
	JLabel lEditora = new JLabel(" EDITORA");
	JLabel lAno = new JLabel(" ANO");
	
	JTextField isbn = new JTextField(8);
	JTextField titulo = new JTextField(8);
	JTextField editora = new JTextField(8);
	JTextField ano = new JTextField(8);

	public void add() {

		preparaJanela();

	}

	public JButton botaoInserir() {

		JButton botaoInserir = new JButton("Inserir");

		botaoInserir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				addIsbn();
				addTitulo();
				addEditora();
				addAno();
				
				String tempIsbn = String.valueOf(livro.getIsbn());

				String tempAno = String.valueOf(livro.getAno());

				if (tempIsbn != null && !tempIsbn.isEmpty() && livro.getTitulo() != null && !livro.getTitulo().isEmpty()

						&& livro.getEditora() != null && !livro.getEditora().isEmpty() && tempAno != null

						&& !tempAno.isEmpty() && livro.getIsbn() > 0 && livro.getAno() != 0) {

					banco.insereLivro(livro);

					janela.dispose();

					limparCampos();

				} else {
					
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "ERRO: TODOS OS CAMPOS SÃO OBRIGATÓRIOS!!!");

				}

			}

		});

		return botaoInserir;

	}

	public JButton botaoCancelar() {

		JButton botaoCancelar = new JButton("Cancelar");

		botaoCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				janela.dispose();

				limparCampos();

			}

		});

		return botaoCancelar;

	}

	public void preparaPainel() {

		panel.setLayout(new MigLayout());

		panel.add(lIsbn);

		panel.add(isbn, "wrap");

		panel.add(lTitulo);

		panel.add(titulo, "wrap");

		panel.add(lEditora);

		panel.add(editora, "wrap");

		panel.add(lAno);

		panel.add(ano, "wrap");

		panel.add(botaoInserir(), BorderLayout.CENTER);

		panel.add(botaoCancelar(), BorderLayout.CENTER);

	}

	public void preparaJanela() {

		preparaPainel();

		janela.add(panel);

		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		janela.pack();

		janela.setSize(200, 180);

		janela.setVisible(true);

		janela.setLocationRelativeTo(null);

		janela.setResizable(false);

	}

	public void limparCampos() {

		isbn.setText("");

		livro.setIsbn(0);

		titulo.setText("");

		livro.setTitulo(null);

		editora.setText("");

		livro.setEditora(null);

		ano.setText("");

		livro.setAno(0);

		panel.removeAll();

	}

	public void addIsbn() {

		String temp = isbn.getText();

		if (!temp.trim().isEmpty()) {

			try {

				if (!temp.trim().isEmpty()) {

					livro.setIsbn(Integer.parseInt(temp));

				} else if (livro.getIsbn() == 0 || livro.getIsbn() > 2147483647) {

					throw new NumberFormatException();

				}

			} catch (Exception e) {
				
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null,

						"ERRO: CAMPO 'ISBN' deve conter apenas números inteiros maiores que 0 e menores que 2.147.483.647!");

			}

		}

		System.out.println("ISBN :" + livro.getIsbn());

	}

	public void addTitulo() {

		String temp = titulo.getText();

		if (!temp.trim().isEmpty()) {

			livro.setTitulo(titulo.getText());

		}

		System.out.println("TÍTULO :" + livro.getTitulo());

	}

	public void addEditora() {

		String temp = editora.getText();

		if (!temp.trim().isEmpty()) {

			livro.setEditora(editora.getText());

		}

		System.out.println("EDITORA :" + livro.getEditora());

	}

	public void addAno() {

		String temp = ano.getText();

		try {

			if (!temp.trim().isEmpty()) {

				livro.setAno(Integer.parseInt(temp));

			} else if (livro.getAno() > 2147483647) {

				throw new NumberFormatException();

			}

		} catch (Exception e) {
			
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null,

					"ERRO: O campo 'ANO' só aceita números inteiros menores que 2.147.483.647!");

		}

		System.out.println("ANO :" + livro.getAno());

	}

}