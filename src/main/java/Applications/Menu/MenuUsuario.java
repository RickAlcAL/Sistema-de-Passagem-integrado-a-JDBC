package Applications.Menu;

import BancoDeDados.ClassConection.ClassConection;
import Entities.ClasseEconomica.ClasseEconomica;
import Entities.ClasseExecutiva.ClasseExecutiva;
import Entities.Enums.RespostasSistema;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuUsuario {


    public void menuUser () throws SQLException {
        ClasseExecutiva exec = new ClasseExecutiva();
        Scanner sc = new Scanner(System.in);
        String nome = null, numeroVoo = null;
        while (true){
            System.out.println("Bem-vindo ao sistema de passagens aéreas!");
            try {
                System.out.println("Digite seu nome completo:");
                nome= sc.nextLine();
                System.out.println("Digite o número do voo:");
                numeroVoo= sc.nextLine();
            }catch (IllegalArgumentException e){
                System.out.println("Campo invalido ou vazio");
                return;
            }
            if (exec.verificadorCadastro(nome,numeroVoo)){
                continue;
            }
            break;

        }
        String classe;

        do {
            System.out.println("Digite em qual classe gostaria de compara sua passagem (economica ou executiva):");
            classe = sc.nextLine().toLowerCase();

            if (!classe.equals("economica") && !classe.equals("executiva")) {
                System.out.println(RespostasSistema.RESPOSTA_INVALIDA);
            }
        } while (!classe.equals("economica") && !classe.equals("executiva"));

        if (classe.equals("economica")) {
            boolean despachar= false;
            char opcao;

            do {
                System.out.println("Deseja despachar mala? Custo adicional de R$120,00 (s/n)");
                opcao = sc.next().charAt(0);

                if (opcao!= 's' && opcao!= 'n') {
                    System.out.println(RespostasSistema.RESPOSTA_INVALIDA);
                }
            } while (opcao!= 's' && opcao!= 'n');

            if (opcao == 's') {
                despachar = true;
            }
            ClasseEconomica economica= new ClasseEconomica(nome, numeroVoo, despachar);
            economica.despacharMala();

            System.out.println(
                    RespostasSistema.PEDIDO_REALIZADO+ "\n" +
                            economica.toString()
            );
            economica.colocandoDados();
            System.out.println("Seus dados estão corretos ? (s/n)" +
                    "Nome: " +nome + " | Numero do voo: " +numeroVoo);
            char conf = sc.next().charAt(0);
            sc.nextLine();
            try {
                if (conf == 'n'){
                    sc.nextLine();
                    System.out.println("Deseja deletar ou corrigir ?");
                    String respostaUser = sc.nextLine();
                    if (respostaUser.equalsIgnoreCase("deletar")){
                        exec.deletarUltimoCadastro();
                    }else {
                        System.out.println("Digite o nome correto agora: ");
                        String nomeNovo = sc.nextLine();
                        System.out.println("Digite o numero do voo correto agora: ");
                        String vooNovo = sc.nextLine();

                        System.out.println("Dados atualizados");
                        economica.atualizarDados(nomeNovo,vooNovo);
                    }

                }else if (conf == 's'){
                    System.out.println("Tenha um otimo voo :)");
                    sc.close();
                }
            }catch (IllegalArgumentException e){
                System.out.println(RespostasSistema.RESPOSTA_INVALIDA);
            }
        }
        else {
            ClasseExecutiva executiva= new ClasseExecutiva(nome, numeroVoo);

            System.out.println(
                    RespostasSistema.PEDIDO_REALIZADO+ "\n" +
                            executiva.toString()
            );
            executiva.colocandoDados();
            System.out.println("Seus dados estão corretos ? (s/n)" +
                    "Nome: " +nome + " | Numero do voo: " +numeroVoo);
            char conf = sc.next().charAt(0);
            sc.nextLine();
            try {
                if (conf == 'n'){
                    sc.nextLine();
                    System.out.println("Deseja deletar ou corrigir ?");
                    String respostaUser = sc.nextLine();
                    if (respostaUser.equalsIgnoreCase("deletar")){
                        exec.deletarUltimoCadastro();
                    }else {
                        System.out.println("Digite o nome correto agora: ");
                        String nomeNovo = sc.nextLine();
                        System.out.println("Digite o numero do voo correto agora: ");
                        String vooNovo = sc.nextLine();

                        System.out.println("Dados atualizados");
                        executiva.atualizarDados(nomeNovo,vooNovo);
                    }

                }else if (conf == 's'){
                    System.out.println("Tenha um otimo voo :)");
                    sc.close();
                }
            }catch (IllegalArgumentException e){
                System.out.println(RespostasSistema.RESPOSTA_INVALIDA);
            }
        }
    }
}
