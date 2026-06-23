package Entities.ClasseExecutiva;

import BancoDeDados.ClassConection.ClassConection;
import Entities.Passagem.Passagem;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class  ClasseExecutiva extends Passagem   {
    //Atributos da classe
    private String bagagemDireito = "1 mochila/bolsa de mão (até 10kg) e 2 malas de até 23kg despachadas.";
    private String inclusos= "Serviço de bordo vip e Sala vip do aeroporto";
    private String nomeClasse = "Classe Executiva";
    Connection conexao = ClassConection.getInstance();
    Statement stmt = conexao.createStatement();
    public ClasseExecutiva(String nomePassageiro, String numeroVoo) throws SQLException {
        super(nomePassageiro, numeroVoo);
        super.precoOriginal += getPrecoOriginal()*0.5;
    }

    public ClasseExecutiva() throws SQLException {
        super();
    }
    //To string que exibe para o usuario sua situção da classe escolhida
    @Override
    public String toString() {
        return "===================================\n"+
                "Classe Executiva\n" +
                String.format("Preco: R$ %.2f", precoOriginal) +
                "\nNumero do Voo: " + numeroVoo +
                "\nNome do Passageiro: " + nomePassageiro +
                "\nBagagem: " + bagagemDireito +
                "\nInclusos: " + inclusos +
                "\n===================================";


    }
    //Metodo usado para coletar os dados do usuario e inserir no banco de dados
    @Override
    public void colocandoDados() throws SQLException {
        String query = "INSERT INTO Passagem (NumeroVoo, NomePassageiro, PrecoOriginal) VALUES ("
                + numeroVoo + ", '"
                + nomePassageiro + "', "
                + precoOriginal + ")";

        stmt.execute(query);

        String query2 = "INSERT INTO tipodeclassedevoo (NomeDaClasse, PrecoDaClasse, SituacaoBagagem, Inclusao, IDpassagem) VALUES ('"
                + nomeClasse + "', "
                + precoOriginal + ", '"
                + bagagemDireito + "', '"
                + inclusos + "', "
                + "LAST_INSERT_ID())";

        stmt.execute(query2);
    }
    //Metodo usado para atualizar os dados do usuario caso ele tenha errado
    @Override
    public void atualizarDados(String novoNome, String novoVoo) throws SQLException {

        String query = "UPDATE Passagem SET "
                + "NomePassageiro = '" + novoNome + "', "
                + "NumeroVoo = " + novoVoo + " "
                + "WHERE IDpassagem = LAST_INSERT_ID()";

        stmt.execute(query);
    }
}
