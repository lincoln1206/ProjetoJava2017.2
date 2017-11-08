package interfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Image;
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

public class InterfaceGraficaInserir extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2438857302623013841L;
	Livro livro = new Livro();
	LivroDAO banco = new LivroDAO();

	JPanel panel = new JPanel();

	JLabel lIsbn = new JLabel(" ISBN");
	JLabel lTitulo = new JLabel(" TÍTULO");
	JLabel lEditora = new JLabel(" EDITORA");
	JLabel lAutor = new JLabel(" AUTOR");
	JLabel lAno = new JLabel(" ANO");

	JTextField isbn = new JTextField(8);
	JTextField titulo = new JTextField(8);
	JTextField editora = new JTextField(8);
	JTextField autor = new JTextField(8);
	JTextField ano = new JTextField(8);

	public InterfaceGraficaInserir() {
		
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage("images/livro.png");  
		this.setIconImage(iconeTitulo);
		preparaPainel();
		this.setTitle("Biblioteca");
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(200, 200);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	public JButton botaoInserir() {

		JButton botaoInserir = new JButton("Inserir");
		botaoInserir.setToolTipText("Clique aqui para inserir os dados.");

		botaoInserir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				addIsbn();
				addTitulo();
				addEditora();
				addAutor();
				addAno();

				String tempIsbn = String.valueOf(livro.getIsbn());

				String tempAno = String.valueOf(livro.getAno());

				if (tempIsbn != null && !tempIsbn.isEmpty() && livro.getTitulo() != null && !livro.getTitulo().isEmpty()

						&& livro.getEditora() != null && !livro.getEditora().isEmpty() && tempAno != null

						&& !tempAno.isEmpty() && livro.getIsbn() > 0 && livro.getAno() != 0 && livro.getAutor() != null
						&& !livro.getAutor().isEmpty()) {

					banco.insereLivro(livro);

					InterfaceGraficaInserir.this.dispose();

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
		botaoCancelar.setToolTipText("Clique aqui para cancelar e retornar ao menu.");

		botaoCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				InterfaceGraficaInserir.this.dispose();

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
		panel.add(lAutor);
		panel.add(autor, "wrap");
		panel.add(lAno);
		panel.add(ano, "wrap");
		panel.add(botaoInserir(), BorderLayout.CENTER);
		panel.add(botaoCancelar(), BorderLayout.CENTER);

	}

	public void limparCampos() {

		isbn.setText("");
		livro.setIsbn(0);
		titulo.setText("");
		livro.setTitulo(null);
		editora.setText("");
		livro.setEditora(null);
		autor.setText("");
		livro.setAutor(null);
		ano.setText("");
		livro.setAno(0);

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

		livro.setTitulo(titulo.getText());
		System.out.println("TÍTULO :" + livro.getTitulo());

	}

	public void addEditora() {

		livro.setEditora(editora.getText());
		System.out.println("EDITORA :" + livro.getEditora());

	}

	public void addAutor() {
		livro.setAutor(autor.getText());
		System.out.println("AUTOR :" + livro.getAutor());
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