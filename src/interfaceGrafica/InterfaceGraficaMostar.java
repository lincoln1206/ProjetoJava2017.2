package interfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import bancoDados.Livro;
import bancoDados.LivroDAO;


public class InterfaceGraficaMostar extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5273317426796751102L;
	LivroDAO metodos = new LivroDAO();

	// CONSTRUTOR PADRÃO DO JFRAME 'InterfaceGraficaMostar'
	public InterfaceGraficaMostar()  {
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage("images/livro.png");// CARREGA A IMAGEM DO ICONE DO
		// JFRAME
		this.setIconImage(iconeTitulo);// DEFINE O ICONE DO JFRAME
		this.setTitle("Biblioteca");// DEFINE O TÍTULO DO JFRAME
		this.add(painel());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// DEFINE A SAÍDA PADRÃO DO JFRAME
		this.setSize(480, 480);// DEFINE O TAMANHO DO JFRAME
		this.setLocationRelativeTo(null);// DEIXA O JFRAME NO CENTRO DA TELA
		this.setResizable(false);// BLOQUEIA O USUÁRIO DE AUMENTAR O JFRAME
	}

	// PAINEL
	public JPanel painel(){
		JPanel painel = new JPanel();
		JScrollPane scroll = new JScrollPane();
		painel.setLayout(new BorderLayout());// DEFINE LAYOUT DO PAINEL
		metodos.obterTabela();// OBTEM OS DADOS DA TABELA
		painel.add(tabela());// ADICIONA A JTABLE "tabela" NO PAINEL
		scroll.getViewport().add(tabela());// DEFINE O SCROLL DE ACORDO COM O TAMANHO DA TABELA
		painel.add(scroll);// ADICIONA O SCROLL NO PAINEL
		painel.add(botaoVoltar(), BorderLayout.SOUTH);// ADICIONA O BOTÃO "Voltar Ao Menu" NO PAINEL
		return painel;
	}

	// JTable tabela
	public JTable tabela() {
		JTable tabela = new JTable();
		List<Livro> livros = metodos.getLivros();
		LivrosTableModel ltm = new LivrosTableModel(livros);
		tabela.setModel(ltm);
		tabela.setBorder(new LineBorder(Color.black));
		tabela.setGridColor(Color.black);
		tabela.setShowGrid(true);
		return tabela;
	}

	// BOTÃO "Voltar ao menu"
	public JButton botaoVoltar() {
		JButton botaoVoltar = new JButton("Voltar ao menu");

		botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceGraficaMostar.this.dispose();
			}
		});
		return botaoVoltar;
	}
}
