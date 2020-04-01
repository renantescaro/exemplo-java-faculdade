package controller;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.EditoraDao;
import dao.EmpresaDao;
import dao.EscritorDao;
import dao.LivroDao;
import entidade.Editora;
import entidade.Empresa;
import entidade.Escritor;
import entidade.Livro;


public class Main {
	public static void main(String[] args) throws SQLException, ParseException{

		LivroDao livroDao = new LivroDao();
		
		Livro livro = livroDao.selecionarPorId(1);
		livro.setNumeroPaginas(303);
		
		livroDao.atualizar(livro);
	}
}
