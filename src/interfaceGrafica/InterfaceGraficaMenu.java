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
import bancoDados.LivroDAO;

public class InterfaceGraficaMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6120954101551585652L;
	LivroDAO metodos = new LivroDAO();
	GridBagConstraints c = new GridBagConstraints();

	private InterfaceGraficaInserir igi = new InterfaceGraficaInserir();
	private InterfaceGraficaMostar igm = new InterfaceGraficaMostar();

	// CONSTRUTOR PADR�O DO JFRAME 'InterfaceGraficaMenu'
	public InterfaceGraficaMenu() throws IOException {
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage("images/livro.png");// CARREGA A IMAGEM DO ICONE DO
																						// JFRAME
		this.setIconImage(iconeTitulo);// DEFINE O ICONE DO JFRAME
		this.setTitle("Biblioteca");// DEFINE O T�TULO DO JFRAME
		this.add(painel());// ADICIONA O PAINEL AO JFRAME
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// DEFINE A SA�DA PADR�O DO JFRAME
		this.setVisible(true);// DEFINE A VISIBILIDADE DO JFRAME
		this.setSize(480, 480);// DEFINE O TAMANHO DO JFRAME
		this.setLocationRelativeTo(null);// DEIXA O JFRAME NO CENTRO DA TELA
		this.setResizable(false);// BLOQUEIA O USU�RIO DE AUMENTAR O JFRAME
	}

	// JIMAGEPENAL - JPANEL MODIFICADO PARA COLOCAR IMAGENS NO BACKGROUND
	public JImagePanel painel() throws IOException {
		JImagePanel painel = new JImagePanel(loadImage("images/biblioteca.jpg"));// CHAMA O PAINEL E CARREGA SUA IMAGEM
																					// DE FUNDO

		painel.setLayout(new GridBagLayout());// DEFINE O LAYOUT DO PAINEL
		painel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);// DEFINE A ORIENTA��O DOS COMPONENTES DO
																			// PAINEL
		painel.add(lNome(), c);// ADICIONA O LABEL 'lNome' AO PAINEL
		setPadrao();// DEFINE O PADR�O DE TAMANHO/LOCALIZA��O DOS ELEMENTOS DO PAINEL

		// DEFINE A POSI��O DO BOT�O NO PAINEL
		c.gridx = 0;
		c.gridy = 1;
		painel.add(botaoCriar(), c);// ADICIONA O BOT�O 'Criar Tabela'

		// DEFINE A POSI��O DO BOT�O NO PAINEL
		c.gridx = 0;
		c.gridy = 2;
		painel.add(botaoInserir(), c);// ADICIONA O BOT�O 'Inserir Livro'

		// DEFINE A POSI��O DO BOT�O NO PAINEL
		c.gridx = 0;
		c.gridy = 3;
		painel.add(botaoDeletar(), c);// ADICIONA O BOT�O 'Deletar Livro'

		// DEFINE A POSI��O DO BOT�O NO PAINEL
		c.gridx = 0;
		c.gridy = 4;
		painel.add(botaoAtualizar(), c);// ADICIONA O BOT�O 'Atualizar T�tulo'

		// DEFINE A POSI��O DO BOT�O NO PAINEL
		c.gridx = 0;
		c.gridy = 5;
		painel.add(botaoDeletarTabela(), c);// ADICIONA O BOT�O 'Deletar Tabela'

		// DEFINE A POSI��O DO BOT�O NO PAINEL
		c.gridx = 0;
		c.gridy = 6;
		painel.add(botaoMostrar(), c);// ADICIONA O BOT�O 'Mostar Tabela'

		// DEFINE A POSI��O DO BOT�O NO PAINEL
		c.gridx = 0;
		c.gridy = 7;
		painel.add(botaoSair(), c);// ADICIONA O BOT�O 'Sair'

		return painel;
	}

	// FUN��O PARA DEFINIR UM PADR�O DE TAMANHO/LOCALIZA��O NOS ELEMENTOS DO LAYOUT
	// DO PAINEL
	public void setPadrao() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.weighty = 0.5;
		c.ipady = 20;
		c.weightx = 0.0;
		c.gridwidth = 0;
		c.gridx = 0;
	}

	// LABEL 'lNome'
	public JLabel lNome() {
		JLabel lNome = new JLabel("BIBLIOTECA");
		// DEFINE A POSI��O DO LABEL NO PAINEL
		c.gridx = 2;
		c.gridy = 0;
		// DEFINE A COR DO LABEL
		lNome.setForeground(Color.WHITE);
		lNome.setBackground(Color.WHITE);
		// DEFINE A FONTE E TAMANHO DO LABEL
		lNome.setFont(new Font("Arial", Font.BOLD, 20));
		return lNome;
	}

	// FUN��O PARA CARREGAR AS IMAGENS NO PAINEL
	private static BufferedImage loadImage(String file) throws IOException {
		return ImageIO.read(new File(file));
	}

	// FUN��O PARA TREMER O JFRAME
	public void shake(javax.swing.JFrame jframe) {
		try {
			int originalX = jframe.getLocation().x;
			int originalY = jframe.getLocation().y;
			long sleepTime = 30;

			for (int i = 0; i <= 2; i++) {
				jframe.setLocation(originalX + 5, originalY);
				Thread.sleep(sleepTime);
				jframe.setLocation(originalX + 5, originalY + 5);
				Thread.sleep(sleepTime);
				jframe.setLocation(originalX, originalY + 5);
				Thread.sleep(sleepTime);
				jframe.setLocation(originalX, originalY);
				Thread.sleep(sleepTime);
				jframe.setLocation(originalX - 5, originalY);
				Thread.sleep(sleepTime);
				jframe.setLocation(originalX - 5, originalY - 5);
				Thread.sleep(sleepTime);
				jframe.setLocation(originalX, originalY - 5);
				Thread.sleep(sleepTime);
			}

			jframe.setLocation(originalX, originalY);

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	// BOT�O 'Criar'
	public JButton botaoCriar() {
		JButton botaoCriar = new JButton("Criar Tabela Livros");
		botaoCriar.setToolTipText("Clique aqui para criar uma tebela 'livros' na biblioteca.");

		botaoCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!igi.isVisible() && !igm.isVisible()) {
					metodos.criaTabelaLivro();
				} else {
					Toolkit.getDefaultToolkit().beep();
					if (igi.isVisible()) {
						shake(igi);
					} else if (igm.isVisible()) {
						shake(igm);
					}
				}
			}
		});

		return botaoCriar;
	}

	// BOT�O 'Inserir Livro'
	public JButton botaoInserir() {
		JButton botaoInserir = new JButton("Inserir Livro");
		botaoInserir.setToolTipText("Clique aqui para inserir um livro na biblioteca.");

		botaoInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!igi.isVisible() && !igm.isVisible() && metodos.existeTabela()) {
					igi.setVisible(true);
				} else {                            
					if (igi.isVisible()) {
						Toolkit.getDefaultToolkit().beep();
						shake(igi);
					} else if (igm.isVisible()) {
						Toolkit.getDefaultToolkit().beep();
						shake(igm);
					}
				}
			}
		});

		return botaoInserir;
	}

	// BOT�O 'Deletar Livro'
	public JButton botaoDeletar() {
		JButton botaoDeletar = new JButton("Deletar Livro");
		botaoDeletar.setToolTipText("Clique aqui para deletar um t�tulo existente na biblioteca.");

		botaoDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!igi.isVisible() && !igm.isVisible() && metodos.existeTabela()) {
					metodos.deletaLivro();
				} else {
					if (igi.isVisible()) {
						Toolkit.getDefaultToolkit().beep();
						shake(igi);
					} else if (igm.isVisible()) {
						Toolkit.getDefaultToolkit().beep();
						shake(igm);
					}
				}
			}
		});

		return botaoDeletar;
	}

	// BOT�O 'Atualizar T�tulo'
	public JButton botaoAtualizar() {
		JButton botaoAtualizar = new JButton("Atualizar T�tulo");
		botaoAtualizar.setToolTipText("Clique aqui para atualizar um t�tulo de um livro existenete na biblioteca.");

		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!igi.isVisible() && !igm.isVisible() && metodos.existeTabela()) {
					metodos.atualizaTitulo();
				} else {
					if (igi.isVisible()) {
						Toolkit.getDefaultToolkit().beep();
						shake(igi);
					} else if (igm.isVisible()) {
						Toolkit.getDefaultToolkit().beep();
						shake(igm);
					}
				}
			}
		});

		return botaoAtualizar;
	}

	// BOT�O 'Deletar Tabela'
	public JButton botaoDeletarTabela() {
		JButton botaoDeletarTabela = new JButton("Deletar Tabela");
		botaoDeletarTabela.setToolTipText("Clique aqui para deletar todos os livros da biblioteca.");

		botaoDeletarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!igi.isVisible() && !igm.isVisible() && metodos.existeTabela()) {
					metodos.deletarTabela();
				} else {
					if (igi.isVisible()) {
						Toolkit.getDefaultToolkit().beep();
						shake(igi);
					} else if (igm.isVisible()) {
						Toolkit.getDefaultToolkit().beep();
						shake(igm);
					}
				}
			}
		});

		return botaoDeletarTabela;
	}

	// BOT�O 'Mostar Tabela'
	public JButton botaoMostrar() {
		JButton botaoMostar = new JButton("Mostar Tabela");
		botaoMostar.setToolTipText("Clique aqui para mostar todos os livros cadastrados na biblioteca.");

		botaoMostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!igi.isVisible() && !igm.isVisible() && metodos.existeTabela()) {
					igm = new InterfaceGraficaMostar();
					igm.setVisible(true);
				} else {
					if (igi.isVisible()) {
						Toolkit.getDefaultToolkit().beep();
						shake(igi);
					} else if (igm.isVisible()) {
						Toolkit.getDefaultToolkit().beep();
						shake(igm);
					}
				}
			}
		});

		return botaoMostar;
	}

	// BOT�O 'Sair'
	public JButton botaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.setToolTipText("Clique aqui para sair do programa.");

		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!igi.isVisible() && !igm.isVisible()) {
					System.exit(0);
				} else {
					Toolkit.getDefaultToolkit().beep();
					if (igi.isVisible()) {
						shake(igi);
					} else if (igm.isVisible()) {
						shake(igm);
					}
				}
			}
		});

		return botaoSair;
	}

}
