package interfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class InterfaceGraficaMostar {
	JTable table = new JTable();
	JFrame janela = new JFrame("Mostra Livros");
	JPanel painel = new JPanel();
	LivroDAO metodos = new LivroDAO();
	List<Livro> livros = metodos.getLivros();
	LivrosTableModel ltm = new LivrosTableModel(livros);
	JScrollPane scroll = new JScrollPane();
	
	public void mostraTabela() {
		preparaJanela();
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
				janela.dispose();
				painel.removeAll();
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

	public void preparaJanela() {
		
		preparaTabela();
		preparaPainel();
	
		janela.add(painel);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.pack();
		janela.setVisible(true);
		janela.setSize(480, 500);
		janela.setLocationRelativeTo(null);
		janela.setResizable(false);
	}
}
