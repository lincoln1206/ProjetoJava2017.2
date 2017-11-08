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
	JTable table = new JTable();
	JPanel painel = new JPanel();
	LivroDAO metodos = new LivroDAO();
	List<Livro> livros = metodos.getLivros();
	LivrosTableModel ltm = new LivrosTableModel(livros);
	JScrollPane scroll = new JScrollPane();
	
	public InterfaceGraficaMostar() {
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage("images/livro.png");  
		this.setIconImage(iconeTitulo);
		
		preparaTabela();
		preparaPainel();
		
		this.setTitle("Biblioteca");
		this.add(painel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(480, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	public void preparaTabela() {
		metodos.obterTabela();
		table.setModel(ltm);
		table.setBorder(new LineBorder(Color.black));
		table.setGridColor(Color.black);
		table.setShowGrid(true);
	}
	
	public JButton botaoVoltar() {
		JButton botaoVoltar = new JButton("Voltar ao menu");
		
		botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceGraficaMostar.this.dispose();
			}
		});
		return botaoVoltar;
	}
	
	public void preparaPainel() {
		painel.add(table);
		scroll.getViewport().add(table);
		painel.add(scroll);
		painel.add(botaoVoltar(), BorderLayout.SOUTH);
	}
}
