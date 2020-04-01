package dao;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import entidade.Pessoa;

public class PessoaDao {
	
	private Pessoa setParametros(ResultSet rs) throws SQLException{
		Pessoa pessoa = new Pessoa();
		
		pessoa.setId(rs.getInt("id"));
		pessoa.setNome(rs.getString("nome"));
		pessoa.setCpf(rs.getString("cpf"));
		pessoa.setRg(rs.getString("rg"));
		pessoa.setDataNascimento(rs.getDate("data_nascimento"));
		pessoa.setObservacao(rs.getString("observacao"));
		
		return pessoa;
	}
	
	public List<Pessoa> selecionarTudo() throws SQLException{
		
		Conexao conexao = new Conexao();
		
		String sql = "SELECT * FROM pessoa";
		
		conexao.conectar();
		PreparedStatement statement = (PreparedStatement) conexao.cnx.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		while(rs.next()){
			Pessoa pessoa = this.setParametros(rs);
			pessoas.add(pessoa);
		}
		
		conexao.fecharConexao();
		
		return pessoas;
	}
	
	public Pessoa selecionarPorId(int id) throws SQLException{
		
		Conexao conexao = new Conexao();
		Pessoa pessoa = new Pessoa();
		
		String sql = "SELECT * FROM pessoa WHERE id=?";

		conexao.conectar();
		PreparedStatement statement = (PreparedStatement) conexao.cnx.prepareStatement(sql);
		
		statement.setInt(1, id);
		
		ResultSet rs = statement.executeQuery();
		
		if(rs != null){		
			rs.next();
			
			pessoa = this.setParametros(rs);
		}
		
		conexao.fecharConexao();
		
		return pessoa;
	}
	
	public boolean inserir(Pessoa pessoa) throws SQLException {
		
		Conexao conexao = new Conexao();
		
		String sql = "INSERT INTO pessoa(nome, cpf, rg, data_nascimento, observacao) "+
				"VALUES(?,?,?,?,?)";
		
		conexao.conectar();
		PreparedStatement statement = (PreparedStatement) conexao.cnx.prepareStatement(sql);
		
		statement.setString(1, pessoa.getNome());
		statement.setString(2, pessoa.getCpf());
		statement.setString(3, pessoa.getRg());
		statement.setDate(4, (Date) pessoa.getDataNascimento());
		statement.setString(5, pessoa.getObservacao());
		
		return statement.execute();
	}
	
	public boolean deletarPorId(int id) throws SQLException{
		
		Conexao conexao = new Conexao();
		
		String sql = "DELETE FROM pessoa WHERE id="+id;

		conexao.conectar();
		PreparedStatement statement = (PreparedStatement) conexao.cnx.prepareStatement(sql);
		
		statement.setInt(1, id);
		
		return statement.execute();
	}
	
	public boolean atualizar(Pessoa pessoa) throws SQLException{
		
		Conexao conexao = new Conexao();
		String sql = "UPDATE pessoa SET nome=?,cpf=?,rg=?,data_nascimento=?,observacao=? WHERE id=?";

		conexao.conectar();
		PreparedStatement statement = (PreparedStatement) conexao.cnx.prepareStatement(sql);
		
		statement.setString(1, pessoa.getNome());
		statement.setString(2, pessoa.getCpf());
		statement.setString(3, pessoa.getRg());
		statement.setDate(4, (Date) pessoa.getDataNascimento());
		statement.setString(5, pessoa.getObservacao());
		statement.setInt(6, pessoa.getId());
		
		return statement.execute();
	}
}
