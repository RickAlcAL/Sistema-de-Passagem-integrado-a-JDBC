package BancoDeDados.ConexaoInicial;

import BancoDeDados.EnumsConexao.SituacaoConexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoInicial {
    static void main(String[] args) throws SQLException {
        //Conexão geral do banco de dados com java
        String url = "jdbc:mysql://127.0.0.1:3306";
        String user = "root";
        String password = "";

        try (Connection conexao = DriverManager.getConnection(url,user,password)){
            System.out.println(SituacaoConexao.APROVADA.getMsgUser());
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
