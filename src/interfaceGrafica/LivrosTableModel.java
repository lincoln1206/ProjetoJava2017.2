package interfaceGrafica;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import bancoDados.Livro;

public class LivrosTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -6476980683033075963L;
	private final List<Livro> livros;
	
	public LivrosTableModel(List<Livro> livros) {
		this.livros = livros;
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return livros.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Livro l = livros.get(rowIndex);
	     
	     switch (columnIndex) {
	     case 0:
	       return l.getIsbn();
	     case 1:
	       return l.getTitulo();
	     case 2:
	       return l.getEditora();
	     case 3:
	    	return l.getAno();
	     }
		return null;
	}
	
	@Override
	 public String getColumnName(int column) {
	   switch (column) {
	   case 0:
	     return "ISBN";
	   case 1:
	     return "T�tulo";
	   case 2:
	     return "Editora";
	   case 3:
		   return "Ano";
	   }
	   return "";
	 }

}