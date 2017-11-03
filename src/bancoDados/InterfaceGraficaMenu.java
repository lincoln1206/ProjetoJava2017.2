package bancoDados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class InterfaceGraficaMenu {

	LivroDAO metodos = new LivroDAO();
	Livro livro = new Livro();
	
	JFrame janela = new JFrame("Biblioteca");
	JLabel lNome = new JLabel("BIBILIOTECA");
	JPanel painel = new JPanel();

	public void menu() {
		preparaTelaMenu();
	}
	
	public void preparaTelaMenu() {
		preparaPainel();
		preparaJanela();
	}

	public void botaoSair() {
		JButton botaoSair = new JButton("Sair");

		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		painel.add(botaoSair, "wrap");
	}

	public void botaoCriar() {
		JButton botaoCriar = new JButton("Criar Tabela Livro");

		botaoCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodos.criaTabelaLivro();
			}
		});
		painel.add(botaoCriar, "wrap");
	}

	public void botaoInserir() {
		InterfaceGraficaInserir igi = new InterfaceGraficaInserir();
		JButton botaoInserir = new JButton("Inserir Livro");

		botaoInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				igi.add();
			}
		});

		painel.add(botaoInserir, "wrap");
	}

	public void botaoDeletar() {
		JButton botaoDeletar = new JButton("Deletar Livro");

		botaoDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodos.deletaLivro();
			}
		});

		painel.add(botaoDeletar, "wrap");
	}

	public void botaoAtualizar() {
		JButton botaoAtualizar = new JButton("Atualizar Livro");

		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodos.atualizaTitulo();
			}
		});
		
		painel.add(botaoAtualizar,"wrap");
	}
	
	public void botaoDeletarTabela() {
		JButton botaoDeletarTabela = new JButton("Deletar Tabela");
		
		botaoDeletarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodos.deletarTabela();	
			}
		});
		
		painel.add(botaoDeletarTabela, "wrap");
	}
	
	public void botaoMostrar() {
		JButton botaoMostar = new JButton("Mostar Tabela");
		
		botaoMostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodos.mostarTabela();
				
			}
		});
		
		painel.add(botaoMostar, "wrap");
	}

	public void preparaPainel() {
		
		painel.setLayout(new MigLayout());
		painel.add(lNome, "wrap");
		botaoCriar();
		botaoInserir();
		botaoDeletar();
		botaoAtualizar();
		botaoDeletarTabela();
		botaoMostrar();
		botaoSair();

	}

	public void preparaJanela() {
		janela.add(painel);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.pack();
		janela.setVisible(true);
		janela.setSize(480, 480);
		janela.setLocationRelativeTo(null);
	}

}
