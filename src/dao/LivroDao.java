package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Editora;
import entidade.Escritor;
import entidade.Livro;

public class LivroDao {
	
	public List<Livro> selecionarTudo() throws SQLException{
		
		Conexao conexao = new Conexao();
		EscritorDao escritorDao = new EscritorDao();
		EditoraDao editoraDao = new EditoraDao();
		
		String sql = "SELECT * FROM livro";
		
		conexao.conectar();
		PreparedStatement statement = conexao.cnx.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		
		List<Livro> livros = new ArrayList<Livro>();
		
		while(rs.next()){
			Livro livro = new Livro();
			
			Escritor escritor = escritorDao.selecionarPorId(rs.getInt("escritor_id"));
			Editora editora = editoraDao.selecionarPorId(rs.getInt("editora_id"));
			
			livro.setId(rs.getInt("id"));
			livro.setNome(rs.getString("nome"));
			livro.setNumeroPaginas(rs.getInt("numero_paginas"));
			livro.setIdioma(rs.getString("idioma"));
			livro.setDescricao(rs.getString("descricao"));
			livro.setISBN(rs.getLong("isbn"));
			livro.setEditora(editora);
			livro.setEscritor(escritor);
			
			livros.add(livro);
		}
		
		conexao.fecharConexao();
		
		return livros;
	}
	
	public Livro selecionarPorId(int id) throws SQLException{
		
		Conexao conexao = new Conexao();
		Livro livro = new Livro();
		EscritorDao escritorDao = new EscritorDao();
		EditoraDao editoraDao = new EditoraDao();
		
		String sql = "SELECT * FROM livro WHERE id=?";

		conexao.conectar();
		PreparedStatement statement = conexao.cnx.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet rs = statement.executeQuery();
		
		if(rs != null){		
			rs.next();
			
			Escritor escritor = escritorDao.selecionarPorId(rs.getInt("escritor_id"));
			Editora editora = editoraDao.selecionarPorId(rs.getInt("editora_id"));
			
			livro.setId(rs.getInt("id"));
			livro.setNome(rs.getString("nome"));
			livro.setNumeroPaginas(rs.getInt("numero_paginas"));
			livro.setIdioma(rs.getString("idioma"));
			livro.setDescricao(rs.getString("descricao"));
			livro.setISBN(rs.getLong("isbn"));
			livro.setEditora(editora);
			livro.setEscritor(escritor);
		}
		
		conexao.fecharConexao();
		
		return livro;
	}
	
	public boolean inserir(Livro livro) throws SQLException {
		
		Conexao conexao = new Conexao();
		
		String sql = "INSERT INTO livro(nome, editora_id, escritor_id, idioma, isbn, numero_paginas) "+
					 "VALUES(?,?,?,?,?,?)";

		conexao.conectar();
		PreparedStatement statement = conexao.cnx.prepareStatement(sql);
		
		statement.setString(1, livro.getNome());
		statement.setInt(2, livro.getEditora().getId());
		statement.setInt(3, livro.getEscritor().getId());
		statement.setString(4, livro.getIdioma());
		statement.setLong(5, livro.getISBN());
		statement.setInt(6, livro.getNumeroPaginas());

		return statement.execute();
	}
	
	public boolean deletarPorId(int id) throws SQLException{
		
		Conexao conexao = new Conexao();		
		String sql = "DELETE FROM livro WHERE id=?";
		
		conexao.conectar();
		PreparedStatement statement = conexao.cnx.prepareStatement(sql);
		
		statement.setInt(1, id);
		
		return statement.execute();
	}
	
	public boolean atualizar(Livro livro) throws SQLException{
		
		Conexao conexao = new Conexao();
		String sql = "UPDATE livro SET descricao=?, editora_id=?, escritor_id=?, idioma=?, isbn=?, numero_paginas=? "+
					 "WHERE id=?";
		
		conexao.conectar();
		PreparedStatement statement = conexao.cnx.prepareStatement(sql);
		
		statement.setString(1, livro.getNome());
		statement.setInt(2, livro.getEditora().getId());
		statement.setInt(3, livro.getEscritor().getId());
		statement.setString(4, livro.getIdioma());
		statement.setLong(5, livro.getISBN());
		statement.setInt(6, livro.getNumeroPaginas());
		statement.setInt(7, livro.getId());

		return statement.execute();
	}
}
