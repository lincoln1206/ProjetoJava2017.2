package interfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

	/* CONSTRUTOR PADRÃO DO JFRAME 'InterfaceGraficaMostar' */
	public  InterfaceGraficaMostar() throws IOException {
		Image iconeTitulo = Toolkit.getDefaultToolkit()
				.getImage("images/livro.png");/* CARREGA A IMAGEM DO ICONE DO JFRAME */
		this.setIconImage(iconeTitulo);/* DEFINE O ICONE DO JFRAME */
		this.setTitle("Biblioteca");/* DEFINE O TÍTULO DO JFRAME */
		this.add(painel());/* ADICIONA O PAINEL AO JFRAME */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/* DEFINE A SAÍDA PADRÃO DO JFRAME */
		this.setSize(480, 480);/* DEFINE O TAMANHO DO JFRAME */
		this.setLocationRelativeTo(null);/* DEIXA O JFRAME NO CENTRO DA TELA */
		this.setResizable(false);/* BLOQUEIA O USUÁRIO DE AUMENTAR O JFRAME */
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
		return painel;//RETORNA O PAINEL PRONTO
	}

	// JTable tabela
	public JTable tabela() {
		JTable tabela = new JTable();
		List<Livro> livros = metodos.getLivros();//OBTEM UM LIST COM OS DADOS DO BANCO DE DADOS
		LivrosTableModel ltm = new LivrosTableModel(livros);//CHAMA O MODELO DE TABELA COM A LIST DENTRO
		tabela.setModel(ltm);//DEFINE O MODELO DA TABELA
		tabela.setBorder(new LineBorder(Color.black));//DEFINE A COR DAS LINHAS
		tabela.setGridColor(Color.black);//DEFINE A COR DAS COLUNAS
		tabela.setShowGrid(true);//MOSTRA AS COLUNAS
		return tabela;//RETORNA A TABELA PRONTA
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
