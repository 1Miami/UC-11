package sosrs;

import java.util.List;

public class Doenca {
    private String nome;
    private List<String> sintomas;
    private String tratamento;
    private String informacoes;
    private boolean picada;

    public Doenca(String nome, List<String> sintomas, String tratamento, String informacoes, boolean picada) {
        this.nome = nome;
        this.sintomas = sintomas;
        this.tratamento = tratamento;
        this.informacoes = informacoes;
        this.picada = picada;
    }

    // Getter para os sintomas
    public List<String> getSintomas() {
        return sintomas;
    }

    // Outros getters e setters

    @Override
    public String toString() {
        return "Doença: " + nome + "\nSintomas: " + String.join(", ", sintomas) + 
               "\nTratamento: " + tratamento + "\nInformações: " + informacoes + 
               "\nPicada: " + (picada ? "Sim" : "Não");
    }
}
