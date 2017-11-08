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
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.towel.swing.img.JImagePanel;

import bancoDados.Livro;
import bancoDados.LivroDAO;

public class InterfaceGraficaMenu extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6120954101551585652L;
	LivroDAO metodos = new LivroDAO();
	Livro livro = new Livro();

	public InterfaceGraficaMenu() throws IOException {
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage("images/livro.png");  
		this.setIconImage(iconeTitulo);
		this.setTitle("Biblioteca");
		this.add(painel());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(480, 480);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	public JButton botaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.setToolTipText("Clique aqui para sair do programa.");

		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		return botaoSair;
	}

	public JButton botaoCriar() {
		JButton botaoCriar = new JButton("Criar Tabela Livro");
		botaoCriar.setToolTipText("Clique aqui para criar uma tebela 'livros' na biblioteca.");

		botaoCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodos.criaTabelaLivro();
			}
		});

		return botaoCriar;
	}

	public JButton botaoInserir() {
		JButton botaoInserir = new JButton("Inserir Livro");
		botaoInserir.setToolTipText("Clique aqui para inserir um livro na biblioteca.");

		botaoInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InterfaceGraficaInserir();
			}
		});

		return botaoInserir;
	}

	public JButton botaoDeletar() {
		JButton botaoDeletar = new JButton("Deletar Livro");
		botaoDeletar.setToolTipText("Clique aqui para deletar um título existente na biblioteca.");

		botaoDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodos.deletaLivro();
			}
		});

		return botaoDeletar;
	}

	public JButton botaoAtualizar() {
		JButton botaoAtualizar = new JButton("Atualizar Título");
		botaoAtualizar.setToolTipText("Clique aqui para atualizar um título de um livro existenete na biblioteca.");

		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodos.atualizaTitulo();
			}
		});

		return botaoAtualizar;
	}

	public JButton botaoDeletarTabela() {
		JButton botaoDeletarTabela = new JButton("Deletar Tabela");
		botaoDeletarTabela.setToolTipText("Clique aqui para deletar todos os livros da biblioteca.");

		botaoDeletarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodos.deletarTabela();
			}
		});

		return botaoDeletarTabela;
	}

	public JButton botaoMostrar() {
		JButton botaoMostar = new JButton("Mostar Tabela");
		botaoMostar.setToolTipText("Clique aqui para mostar todos os livros cadastrados na biblioteca.");

		botaoMostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InterfaceGraficaMostar();

			}
		});

		return botaoMostar;
	}

	public JImagePanel painel() throws IOException {
		JImagePanel painel = new JImagePanel(loadImage("images/biblioteca.jpg"));
		painel.setLayout(new GridBagLayout());
		
		painel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel lNome = new JLabel("BIBLIOTECA");
		c.gridx = 2;
		c.gridy = 0;
		lNome.setForeground(Color.WHITE);
		lNome.setBackground(Color.WHITE);
		lNome.setFont(new Font("Arial", Font.BOLD, 20));
		painel.add(lNome, c);
		
		//DEFINE OS PADRÕES DE TODOS OS BOTÕES
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.weighty = 0.5;
		c.ipady = 20;      
		c.weightx = 0.0;
		c.gridwidth = 0;
		c.gridx = 0;
		//

		c.gridx = 0;
		c.gridy = 1;
		painel.add(botaoCriar(), c);
		
		c.gridx = 0;
		c.gridy = 2;
		painel.add(botaoInserir(), c);
		
		c.gridx = 0;
		c.gridy = 3;
		painel.add(botaoDeletar(), c);
		
		c.gridx = 0;
		c.gridy = 4;
		painel.add(botaoAtualizar(), c);
		
		c.gridx = 0;
		c.gridy = 5;
		painel.add(botaoDeletarTabela(), c);
		
		c.gridx = 0;
		c.gridy = 6;
		painel.add(botaoMostrar(), c);
		
		c.gridx = 0;
		c.gridy = 7;
		painel.add(botaoSair(), c);

		return painel;
	}

	private static BufferedImage loadImage(String file) throws IOException {
		return ImageIO.read(new File(file));
	}

}
