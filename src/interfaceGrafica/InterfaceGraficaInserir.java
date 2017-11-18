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
	
	JLabel lNome = new JLabel("INSERIR LIVRO");
	JLabel lTitulo = new JLabel("TÍTULO:");
	JLabel lEditora = new JLabel("EDITORA:");
	JLabel lAutor = new JLabel("AUTOR:");
	JLabel lAno = new JLabel("ANO:");

	JTextField titulo = new JTextField(8);
	JTextField editora = new JTextField(8);
	JTextField autor = new JTextField(8);
	JComboBox<String> cbAnos = new JComboBox<String>();

	public InterfaceGraficaInserir() throws IOException {

		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage("images/livro.png");
		this.setIconImage(iconeTitulo);
		this.setTitle("Biblioteca");
		this.add(painel());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(480, 480);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	public JButton botaoInserir() {

		JButton botaoInserir = new JButton("Inserir");
		botaoInserir.setToolTipText("Clique aqui para inserir os dados.");

		botaoInserir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				addTitulo();
				addEditora();
				addAutor();

				if (livro.getTitulo() != null && !livro.getTitulo().isEmpty() && livro.getEditora() != null
						&& !livro.getEditora().isEmpty() && livro.getAno() != null
						&& livro.getAutor() != null && !livro.getAutor().isEmpty()) {

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
	
	public JImagePanel painel() throws IOException {
		JImagePanel painel = new JImagePanel(loadImage("images/biblioteca.jpg"));
		painel.setLayout(new GridBagLayout());
		
		painel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();
	
		c.gridx = 2;
		c.gridy = 0;
		lNome.setForeground(Color.WHITE);
		lNome.setBackground(Color.WHITE);
		lNome.setFont(new Font("Arial", Font.BOLD, 20));
		painel.add(lNome, c);
		
		//DEFINE OS PADRÕES
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.weighty = 0.5;
		c.ipady = 10;      
		c.weightx = 1.0;
		c.gridwidth = 0;
		c.gridx = 0;
		//
		
		c.gridx = 0;
		c.gridy = 1;
		lTitulo.setForeground(Color.WHITE);
		lTitulo.setBackground(Color.WHITE);
		lTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		painel.add(lTitulo, c);
		
		c.gridx = 4;
		c.gridy = 1;
		painel.add(titulo, c);
		
		c.gridx = 0;
		c.gridy = 2;
		lAutor.setForeground(Color.WHITE);
		lAutor.setBackground(Color.WHITE);
		lAutor.setFont(new Font("Arial", Font.BOLD, 20));
		painel.add(lAutor, c);
		
		c.gridx = 4;
		c.gridy = 2;
		painel.add(autor, c);
		
		c.gridx = 0;
		c.gridy = 3;
		lEditora.setForeground(Color.WHITE);
		lEditora.setBackground(Color.WHITE);
		lEditora.setFont(new Font("Arial", Font.BOLD, 20));
		painel.add(lEditora, c);

		c.gridx = 4;
		c.gridy = 3;
		painel.add(editora, c);
		
		c.gridx = 0;
		c.gridy = 4;
		lAno.setForeground(Color.WHITE);
		lAno.setBackground(Color.WHITE);
		lAno.setFont(new Font("Arial", Font.BOLD, 20));
		painel.add(lAno, c);
		
		c.gridx = 4;
		c.gridy = 4;
		comboBox();
		painel.add(cbAnos, c);
		
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.ipady = 5;  
		
		c.gridx = 0;
		c.gridy = 5;
		painel.add(botaoInserir(), c);
		
		c.gridx = 0;
		c.gridy = 6;
		painel.add(botaoCancelar(), c);
		
		return painel;
	}

	public void limparCampos() {

		titulo.setText("");
		livro.setTitulo(null);
		editora.setText("");
		livro.setEditora(null);
		autor.setText("");
		livro.setAutor(null);
		livro.setAno(null);

	}

	public void addTitulo() {

		livro.setTitulo(titulo.getText());

	}

	public void addEditora() {

		livro.setEditora(editora.getText());
		
	}

	public void addAutor() {

		livro.setAutor(autor.getText());
		
	}
	
	public void addAno() {
		
		livro.setAno((String)cbAnos.getSelectedItem().toString());
		
	}
	
	public void comboBox() {
		List<Livro> anos = new ArrayList<Livro>();

		Calendar cal = GregorianCalendar.getInstance();
		int anoAtual = cal.get(Calendar.YEAR);

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
				addAno();
			}
		});
	}
	
	private static BufferedImage loadImage(String file) throws IOException {
		return ImageIO.read(new File(file));
	}

}