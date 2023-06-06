package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;              // Cria��o da vari�vel conn do tipo Connection.
		PreparedStatement st = null;         // Cria��o da vari�vel st do tipo PreparedStatement.
		try {
			conn = DB.getConnection();       // .getConnection() conecta com o BD.

			st = conn.prepareStatement(      // Pega o comando sql dentro dos par�ntes e retorna o resultado para um objeto PreparedStatement.
					"DELETE FROM department "// Deletar na tabela seller
					+ "WHERE "               // Onde
					+ "Id = ?"); 			 // Id = ?

			//.set+tipoDoCampo (coluna) chamado a partir de um objeto PreparedStatement permite inserir/atualizar dados no BD.
			st.setInt(1, 2); 		 		 // O primeiro placeholder recebe o valor de 5.

			int rowsAffected = st.executeUpdate(); // .executeUpdate() executa o comando sql armazenado em st e retorna o n� de linhas afetadas.

			System.out.println("Done! Rows affected: " + rowsAffected);
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage()); // Lan�amento de uma exce��o a partir de uma SQLException.
		} 
		finally {
			DB.closeStatement(st);           // chamada do m�todo que fecha o PreparedStatement.
			DB.closeConnection();			 // chamada do m�todo que fecha o Connection.
		}
	}
}