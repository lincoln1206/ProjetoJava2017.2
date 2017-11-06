package interfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
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
import net.miginfocom.swing.MigLayout;

public class InterfaceGraficaMenu {

	LivroDAO metodos = new LivroDAO();
	Livro livro = new Livro();
	
	JFrame janela = new JFrame("Biblioteca");
	JLabel lNome = new JLabel("BIBILIOTECA");
	

	public void menu() throws IOException {
		preparaJanela();
	}

	public JButton botaoSair() {
		JButton botaoSair = new JButton("Sair");

		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		return botaoSair;
	}

	public JButton botaoCriar() {
		JButton botaoCriar = new JButton("Criar Tabela Livro");

		botaoCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodos.criaTabelaLivro();
			}
		});
		
		return botaoCriar;
	}

	public JButton botaoInserir() {
		InterfaceGraficaInserir igi = new InterfaceGraficaInserir();
		JButton botaoInserir = new JButton("Inserir Livro");

		botaoInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				igi.add();
			}
		});
		
		return botaoInserir;
	}

	public JButton botaoDeletar() {
		JButton botaoDeletar = new JButton("Deletar Livro");

		botaoDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodos.deletaLivro();
			}
		});

		return botaoDeletar;
	}

	public JButton botaoAtualizar() {
		JButton botaoAtualizar = new JButton("Atualizar Livro");

		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodos.atualizaTitulo();
			}
		});
		
		return botaoAtualizar;
	}
	
	public JButton botaoDeletarTabela() {
		JButton botaoDeletarTabela = new JButton("Deletar Tabela");
		
		botaoDeletarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodos.deletarTabela();	
			}
		});
		
		return botaoDeletarTabela;
	}
	
	public JButton botaoMostrar() {
		JButton botaoMostar = new JButton("Mostar Tabela");
		InterfaceGraficaMostar igm = new InterfaceGraficaMostar();
		
		botaoMostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				igm.mostraTabela();
				
			}
		});
		
		return botaoMostar;
	}

	public JImagePanel painel() throws IOException {
		JImagePanel painel = new JImagePanel(
	            loadImage("images/biblioteca.jpg"));
		painel.setLayout(new MigLayout());
		lNome.setForeground(Color.WHITE);
		painel.add(lNome, BorderLayout.CENTER);
		
		painel.add(botaoSair(), BorderLayout.SOUTH);
		painel.add(botaoCriar(), BorderLayout.SOUTH);
		painel.add(botaoInserir(), BorderLayout.SOUTH);
		painel.add(botaoDeletar(), BorderLayout.SOUTH);
		painel.add(botaoAtualizar(),BorderLayout.SOUTH);
		painel.add(botaoDeletarTabela(), BorderLayout.SOUTH);
		painel.add(botaoMostrar(), BorderLayout.SOUTH);
		
		
		return painel;
	}

	public void preparaJanela() throws IOException {
		
		janela.add(painel());
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.pack();
		janela.setVisible(true);
		janela.setSize(480, 480);
		janela.setLocationRelativeTo(null);
		janela.setResizable(false);
	}
	
	private static BufferedImage loadImage(String file) throws IOException {
        return ImageIO.read(new File(file));
    }

}
