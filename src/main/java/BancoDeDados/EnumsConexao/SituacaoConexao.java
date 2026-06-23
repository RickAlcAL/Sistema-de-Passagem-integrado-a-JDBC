package BancoDeDados.EnumsConexao;

public enum SituacaoConexao {
    //Enums de mensagens d osistema
    APROVADA ("Conexão aprovada com sucesso !!!"),
    AGUARDO ("Aguarde estabelecendo conexão"),
    ERRO ("Erro, erro de conexão");

    private final String msgUser;

    SituacaoConexao (String msgUser){
        this.msgUser = msgUser;
    }

    public String getMsgUser() {
        return msgUser;
    }
}
