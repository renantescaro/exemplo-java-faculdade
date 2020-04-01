package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidade.Empresa;

public class EmpresaDao {
	
	private Empresa setParametros(ResultSet rs) throws SQLException{
		Empresa empresa = new Empresa();

		empresa.setId(rs.getInt("id"));
		empresa.setNome(rs.getString("nome"));
		empresa.setCnpj(rs.getString("cnpj"));
		empresa.setIE(rs.getString("ie"));
		empresa.setDataAbertura(rs.getDate("data_abertura"));

		return empresa;
	}
	
	public List<Empresa> selecionarTudo() throws SQLException{
		
		Conexao conexao = new Conexao();
		String sql = "SELECT * FROM empresa";
		
		conexao.conectar();
		PreparedStatement statement = conexao.cnx.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		
		List<Empresa> empresas = new ArrayList<Empresa>();
		
		while(rs.next()){
			Empresa empresa = this.setParametros(rs);
			empresas.add(empresa);
		}
		
		conexao.fecharConexao();
		
		return empresas;
	}
	
	public Empresa selecionarPorId(int id) throws SQLException{
		
		Conexao conexao = new Conexao();
		Empresa empresa = new Empresa();
		
		String sql = "SELECT * FROM empresa WHERE id=?";
		
		conexao.conectar();
		PreparedStatement statement = conexao.cnx.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet rs = statement.executeQuery();
		
		if(rs != null){		
			rs.next();
			empresa = this.setParametros(rs);
		}
		
		conexao.fecharConexao();
		
		return empresa;
	}
	
	public boolean inserir(Empresa empresa) throws SQLException {
		
		Conexao conexao = new Conexao();
		String sql = "INSERT INTO empresa(nome, cnpj, ie, data_abertura) VALUES(?,?,?,?)";
		
		conexao.conectar();
		PreparedStatement statement = conexao.cnx.prepareStatement(sql);
		
		statement.setString(1, empresa.getNome());
		statement.setString(2, empresa.getCnpj());
		statement.setString(3, empresa.getIE());
		statement.setDate(4, empresa.getDataAbertura());
		
		return statement.execute();
	}
	
	public boolean deletarPorId(int id) throws SQLException{
		
		Conexao conexao = new Conexao();
		String sql = "DELETE FROM empresa WHERE id=?";
		
		conexao.conectar();
		PreparedStatement statement = conexao.cnx.prepareStatement(sql);
		
		statement.setInt(1, id);
		
		return statement.execute();
	}
	
	public boolean atualizar(Empresa empresa) throws SQLException{
		
		Conexao conexao = new Conexao();
		String sql = "UPDATE empresa SET nome=?,cnpj=?,ie=?,data_abertura=? "+
					 "WHERE id=?";
		
		conexao.conectar();
		PreparedStatement statement = conexao.cnx.prepareStatement(sql);
		
		statement.setString(1, empresa.getNome());
		statement.setString(2, empresa.getCnpj());
		statement.setString(3, empresa.getIE());
		statement.setDate(4, empresa.getDataAbertura());
		statement.setInt(5, empresa.getId());
		
		return statement.execute();
	}
}
