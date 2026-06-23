package BancoDeDados.ClassConection;

import BancoDeDados.EnumsConexao.SituacaoConexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClassConection {
    public static Connection getInstance() throws SQLException {
        try {
            //Metodo de conexão do banco de dados
            String url = "jdbc:mysql://127.0.0.1:3306/sistema_de_passagem_aerea";
            String user = "root";
            String password = "";
            System.out.println(SituacaoConexao.AGUARDO.getMsgUser());
            System.out.println(SituacaoConexao.APROVADA.getMsgUser());
            return DriverManager.getConnection(url,user,password);
        }catch (Exception e){
            throw  new RuntimeException(SituacaoConexao.ERRO.getMsgUser() + e);
        }
    }
}
