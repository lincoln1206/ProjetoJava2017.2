package interfaceGrafica;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.towel.swing.img.JImagePanel;

import bancoDados.Livro;
import bancoDados.LivroDAO;

public class InterfaceGraficaInserir extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2438857302623013841L;
	Livro livro = new Livro();
	LivroDAO banco = new LivroDAO();
	GridBagConstraints c = new GridBagConstraints();
	JComboBox<String> cbAnos = new JComboBox<String>();
	Calendar cal = GregorianCalendar.getInstance();
	int anoAtual = cal.get(Calendar.YEAR);

	JTextField titulo = new JTextField(8);
	JTextField editora = new JTextField(8);
	JTextField autor = new JTextField(8);

	/* CONSTRUTOR PADR�O DO JFRAME 'InterfaceGraficaInserir' */
	public InterfaceGraficaInserir() throws IOException {
		Image iconeTitulo = Toolkit.getDefaultToolkit()
				.getImage("images/livro.png");/* CARREGA A IMAGEM DO ICONE DO JFRAME */
		this.setIconImage(iconeTitulo);/* DEFINE O ICONE DO JFRAME */
		this.setTitle("Biblioteca");/* DEFINE O T�TULO DO JFRAME */
		this.add(painel());/* ADICIONA O PAINEL AO JFRAME */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/* DEFINE A SA�DA PADR�O DO JFRAME */
		this.setSize(480, 480);/* DEFINE O TAMANHO DO JFRAME */
		this.setLocationRelativeTo(null);/* DEIXA O JFRAME NO CENTRO DA TELA */
		this.setResizable(false);/* BLOQUEIA O USU�RIO DE AUMENTAR O JFRAME */
	}

	public JImagePanel painel() throws IOException {
		JImagePanel painel = new JImagePanel(loadImage("images/biblioteca.jpg"));// CHAMA O PAINEL E CARREGA SUA IMAGEM
		// DE FUNDO
		painel.setLayout(new GridBagLayout());// DEFINE O LAYOUT DO PAINEL
		painel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);// DEFINE A ORIENTA��O DOS COMPONENTES DO
		// PAINEL
		painel.add(lNome(), c);// ADICIONA O LABEL 'lNome' AO PAINEL
		setPadrao(1);// DEFINE O PADR�O DE TAMANHO/LOCALIZA��O DOS ELEMENTOS DO PAINEL
		
		// ADICIONA O LABEL 'lTitulo' E O JTextField 'titulo' AO PAINEL
		painel.add(lTitulo(), c);
		c.gridx = 4;
		c.gridy = 1;
		painel.add(titulo, c);
		
		// ADICIONA O LABEL 'lAutor' E O JTextField 'autor' AO PAINEL
		painel.add(lAutor(), c);
		c.gridx = 4;
		c.gridy = 2;
		painel.add(autor, c);
		
		// ADICIONA O LABEL 'lEditora' E O JTextField 'editora' AO PAINEL
		painel.add(lEditora(), c);
		c.gridx = 4;
		c.gridy = 3;
		painel.add(editora, c);
		
		// ADICIONA O LABEL 'lAno' E A  JComboBox AO PAINEL
		painel.add(lAno(), c);
		c.gridx = 4;
		c.gridy = 4;
		preparaComboBox();
		painel.add(cbAnos, c);
		
		setPadrao(2);// DEFINE O PADR�O DE TAMANHO/LOCALIZA��O DOS ELEMENTOS DO PAINEL
		
		// ADICIONA O BOT�O INSERIR AO PAINEL
		c.gridx = 0;
		c.gridy = 5;
		painel.add(botaoInserir(), c);
		
		// ADICIONA O BOT�O CANCELAR AO PAINEL
		c.gridx = 0;
		c.gridy = 6;
		painel.add(botaoCancelar(), c);

		return painel;//RETORNA O PAINEL
	}

	// FUN��O PARA DEFINIR UM PADR�O DE TAMANHO/LOCALIZA��O NOS ELEMENTOS DO LAYOUT
	// DO PAINEL
	public void setPadrao(int padrao) {
		if (padrao == 1) {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.0;
			c.weighty = 0.5;
			c.ipady = 5;
			c.weightx = 1.0;
			c.gridwidth = 0;
			c.gridx = 0;

		} else if (padrao == 2) {
			c.weightx = 0.0;
			c.weighty = 0.0;
			c.ipady = 5;
		}
	}

	// LABELS
	public JLabel lNome() {
		JLabel lNome = new JLabel("INSERIR LIVRO");
		c.gridx = 2;
		c.gridy = 0;
		lNome.setForeground(Color.WHITE);
		lNome.setBackground(Color.WHITE);
		lNome.setFont(new Font("Arial", Font.BOLD, 20));
		return lNome;
	}

	public JLabel lTitulo() {
		JLabel lTitulo = new JLabel("T�tulo");
		c.gridx = 0;
		c.gridy = 1;
		lTitulo.setForeground(Color.WHITE);
		lTitulo.setBackground(Color.WHITE);
		lTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		return lTitulo;
	}

	public JLabel lAutor() {
		JLabel lAutor = new JLabel("Autor");
		c.gridx = 0;
		c.gridy = 2;
		lAutor.setForeground(Color.WHITE);
		lAutor.setBackground(Color.WHITE);
		lAutor.setFont(new Font("Arial", Font.BOLD, 20));
		return lAutor;
	}

	public JLabel lEditora() {
		JLabel lEditora = new JLabel("Editora");
		c.gridx = 0;
		c.gridy = 3;
		lEditora.setForeground(Color.WHITE);
		lEditora.setBackground(Color.WHITE);
		lEditora.setFont(new Font("Arial", Font.BOLD, 20));
		return lEditora;
	}

	public JLabel lAno() {
		JLabel lAno = new JLabel("Ano");
		c.gridx = 0;
		c.gridy = 4;
		lAno.setForeground(Color.WHITE);
		lAno.setBackground(Color.WHITE);
		lAno.setFont(new Font("Arial", Font.BOLD, 20));
		return lAno;
	}

	// LIMPA OS CAMPOS
	public void limparCampos() {

		titulo.setText("");
		livro.setTitulo(null);
		editora.setText("");
		livro.setEditora(null);
		autor.setText("");
		livro.setAutor(null);
		livro.setAno(null);
		cbAnos.setSelectedIndex(anoAtual + 3000);

	}

	// DEFINE OS VALORES DAS VARI�VEIS
	public void setValores() {
		try {
			if (titulo.getText().length() < 40 && autor.getText().length() < 40 && editora.getText().length() < 40) {
				livro.setTitulo(titulo.getText());
				livro.setEditora(editora.getText());
				livro.setAutor(autor.getText());
			} else {
				throw new StringIndexOutOfBoundsException();
			}
		} catch (StringIndexOutOfBoundsException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null,
					"ERRO: Os campos 'T�tulo' , 'Autor' e 'Editora' s� podem ter no m�ximo 40 caracteres!");
		}
	}

	// PREPARA O JComboBox DOS ANOS
	public void preparaComboBox() {
		List<Livro> anos = new ArrayList<Livro>();

		for (int i = -3000; i <= anoAtual; i++) {
			String anoTemp = String.valueOf(i);
			Livro ano = new Livro(anoTemp);
			anos.add(ano);
		}

		String strAno = null;
		for (int i = 0; i < anos.size(); i++) {
			strAno = (String) anos.get(i).toString();
			cbAnos.addItem(strAno);
		}
		cbAnos.setSelectedIndex(anos.size() - 1);
		cbAnos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				livro.setAno((String) cbAnos.getSelectedItem().toString());
			}
		});
	}

	// CARREGA A IMAGEM DO BACKGROUND
	private static BufferedImage loadImage(String file) throws IOException {
		return ImageIO.read(new File(file));
	}

	// BOT�O INSERIR
	public JButton botaoInserir() {

		JButton botaoInserir = new JButton("Inserir");
		botaoInserir.setToolTipText("Clique aqui para inserir os dados.");

		botaoInserir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				setValores();
				
				/*if que checa se os valores podem ser adicionados ao banco */
				if (livro.getTitulo() != null && !livro.getTitulo().isEmpty() && livro.getEditora() != null
						&& !livro.getEditora().isEmpty() && livro.getAno() != null && livro.getAutor() != null
						&& !livro.getAutor().isEmpty()) {

					banco.insereLivro(livro);

					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Livro inserido com sucesso!");

					InterfaceGraficaInserir.this.dispose();

					limparCampos();

				} else {

					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "ERRO: Todos os campos s�o obrigat�rios!!!");

				}

			}

		});

		return botaoInserir;

	}

	// BOT�O CANCELAR
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

}