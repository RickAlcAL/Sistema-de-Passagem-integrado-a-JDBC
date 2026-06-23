package Entities.Passagem;

import BancoDeDados.ClassConection.ClassConection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Passagem {
    //Atributos centrai e chaves para o sistema
    protected String nomePassageiro;
    protected String numeroVoo;
    protected double precoOriginal= 500.00;
    Connection conexao = ClassConection.getInstance();
    Statement stmt = conexao.createStatement();


    public Passagem(String nomePassageiro, String numeroVoo) throws SQLException {
        this.nomePassageiro = nomePassageiro;
        this.numeroVoo = numeroVoo;
    }

    public Passagem() throws SQLException {

    }

    public String getNomePassageiro() {
        return nomePassageiro;
    }

    public void setNomePassageiro(String nomePassageiro) {
        this.nomePassageiro = nomePassageiro;
    }

    public double getPrecoOriginal() {
        return precoOriginal;
    }

    public String getNumeroVoo() {
        return numeroVoo;
    }

    public void setNumeroVoo(String numeroVoo) {
        this.numeroVoo = numeroVoo;
    }
    //To string que exibe para o usuario sua situção da classe escolhida
    public void colocandoDados () throws SQLException {
        String query = "Insert into Passagem (NumeroVoo,NomePassageiro,PrecoOriginal) values ('"
                + numeroVoo
                + nomePassageiro
                + precoOriginal + "')";

        stmt.execute(query);
    }
    //Metodo usado para coletar os dados do usuario e inserir no banco de dados
    public void atualizarDados(String novoNome, String novoVoo) throws SQLException {
        String query = "UPDATE Passagem SET "
                + "NomePassageiro = '" + novoNome + "', "
                + "NumeroVoo = " + novoVoo + " "
                + "WHERE IDpassagem = LAST_INSERT_ID()";

        stmt.execute(query);
    }

    //Selecionando os dados do usuario para fazer uma verificação de existencia
    public boolean selecionandoDados (String nomePassageiro, String numeroVoo) throws SQLException {
        String query = "SELECT * FROM Passagem WHERE NomePassageiro = '" + nomePassageiro + "' AND NumeroVoo = " + numeroVoo;

        java.sql.ResultSet rs = stmt.executeQuery(query);
        return rs.next();


    }
    //Utilizando do metodo acima para verificar o cadastro do usuario apartir do select usado
    public boolean verificadorCadastro (String nomePassageiro, String numeroVoo) throws SQLException {

        try {
            if (selecionandoDados(nomePassageiro,numeroVoo)){
                System.out.println("Usuario ja cadastrado tente novamente");

                return true;

            }else {
                colocandoDados();
                return false;
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    //Utilizando o delete para caso o usuario não queira mais o cadastro da passagem
    public void deletarUltimoCadastro() throws SQLException {
        // Apaga a última passagem inserida usando o ID gerado por ela
        String query = "DELETE FROM Passagem WHERE IDpassagem = LAST_INSERT_ID()";
        stmt.execute(query);
    }
}
