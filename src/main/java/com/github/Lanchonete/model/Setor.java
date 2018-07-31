
package main.java.com.github.Lanchonete.model;


public enum Setor {
    
    GARCOM("GARÇOM"), CAIXA("CAIXA"), COZINHA("COZINHA"), GERENCIA("GERÊNCIA");
     
    private final String setor;

    private Setor(String setor) {
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }

}
