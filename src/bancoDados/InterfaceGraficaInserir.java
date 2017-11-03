package bancoDados;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class InterfaceGraficaInserir {

	static boolean enviouDados = false;
	static boolean cancelou = true;
	Livro livro = new Livro();
	LivroDAO banco = new LivroDAO();

	JFrame janela = new JFrame("Inserir Livro");
	JPanel panel = new JPanel();

	JButton botaoInserir = new JButton("Inserir");
	JButton botaoCancelar = new JButton("Cancelar");

	JLabel lIsbn = new JLabel(" ISBN");
	JLabel lTitulo = new JLabel(" TÍTULO");
	JLabel lEditora = new JLabel(" EDITORA");
	JLabel lAno = new JLabel(" ANO");

	JTextField isbn = new JTextField(10);
	JTextField titulo = new JTextField(10);
	JTextField editora = new JTextField(10);
	JTextField ano = new JTextField(10);

	public void add() {
		preparaTelaInserir();
	}

	public void preparaTelaInserir() {
		preparaPainel();
		preparaJanela();
	}

	public void botaoInserir() {

		panel.add(botaoInserir, BorderLayout.SOUTH);

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
						&& !tempAno.isEmpty() && livro.getIsbn() > 0 && livro.getAno() > 0) {
					banco.insereLivro(livro);
					janela.dispose();
					limparCampos();
					enviouDados = true;
					cancelou = false;
				} else {
					if (enviouDados == false | cancelou == false) {
						JOptionPane.showMessageDialog(null, "ERRO: TODOS OS CAMPOS SÃO OBRIGATÓRIOS!!!");
					}
				}

			}
		});

	}

	public void botaoCancelar() {

		panel.add(botaoCancelar, BorderLayout.SOUTH);

		botaoCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				janela.dispose();
				limparCampos();
				cancelou = true;
			}
		});
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
		botaoCancelar();
		botaoInserir();

	}

	public void addIsbn() {
		String temp = isbn.getText();

		if (!temp.isEmpty()) {
			try {
				livro.setIsbn(Integer.parseInt(temp));
				if (livro.getIsbn() == 0 || livro.getIsbn() > 2147483647) {
					throw new NumberFormatException();
				}
			} catch (Exception e) {
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

	public void addAno() {
		String temp = ano.getText();

		if (!temp.isEmpty()) {
			try {
				livro.setAno(Integer.parseInt(temp));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"ERRO: O campo 'ANO' só aceita números inteiros menores que 2.147.483.647! ");
			}
		}
		System.out.println("ANO :" + livro.getAno());
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
	}

	public void preparaJanela() {
		janela.add(panel);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.pack();
		janela.setSize(200, 200);
		janela.setVisible(true);
		janela.setLocationRelativeTo(null);
	}

}
