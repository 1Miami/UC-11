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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<String> sintomas) {
        this.sintomas = sintomas;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public boolean isPicada() {
        return picada;
    }

    public void setPicada(boolean picada) {
        this.picada = picada;
    }

    @Override
    public String toString() {
        return "Doença: " + nome + "\nSintomas: " + String.join(", ", sintomas) + 
               "\nTratamento: " + tratamento + "\nInformações: " + informacoes + 
               "\nPicada: " + (picada ? "Sim" : "Não");
    }
}
