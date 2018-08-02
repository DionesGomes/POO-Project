
package main.java.com.github.Lanchonete.model;


public enum Setor {
    
    /*Parametros que destingue o setor dos usuários*/
    
    GARCOM("GARÇOM"), CAIXA("CAIXA"), COZINHA("COZINHA"), GERENCIA("GERÊNCIA");
    /*OBS: Caso o parametro passado não bata com as opçõe acima, uma exceção e gerada*/ 
    private final String setor;
    
    /*Construtor*/
    private Setor(String setor) {
        this.setor = setor;
    }

    /*Getter*/
    public String getSetor() {
        return setor;
    }

}
