package sosrs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DoencaManager {
    private static DoencaManager instance;
    private DoencaDAO doencaDAO;
    private List<Doenca> doencas; // Definição da variável

    private DoencaManager() {
        doencaDAO = new DoencaDAO();
        doencas = new ArrayList<>(); // Inicialização da lista
        // Você pode carregar as doenças existentes do banco de dados aqui, se necessário
        carregarDoencas();
    }

    public static synchronized DoencaManager getInstance() {
        if (instance == null) {
            instance = new DoencaManager();
        }
        return instance;
    }

    public void adicionarDoenca(Doenca doenca) {
        try {
            doencaDAO.adicionarDoenca(doenca);
            doencas.add(doenca); // Adiciona a doença à lista local
        } catch (SQLException e) {
            e.printStackTrace();
            // Você pode exibir uma mensagem de erro para o usuário aqui
        }
    }

    public List<Doenca> getDoencas() {
        return new ArrayList<>(doencas); // Retorna uma cópia da lista
    }

    public Doenca getDoenca(String nome) {
        return doencas.stream()
            .filter(d -> d.getNome().equals(nome))
            .findFirst()
            .orElse(null);
    }

    public void atualizarDoenca(String nomeOriginal, Doenca doencaAtualizada) {
        Doenca doencaExistente = doencas.stream()
            .filter(d -> d.getNome().equals(nomeOriginal))
            .findFirst()
            .orElse(null);

        if (doencaExistente != null) {
            doencas.remove(doencaExistente);
            doencas.add(doencaAtualizada);
            try {
                doencaDAO.atualizarDoenca(nomeOriginal, doencaAtualizada); // Atualiza o banco de dados
            } catch (SQLException e) {
                e.printStackTrace();
                // Você pode exibir uma mensagem de erro para o usuário aqui
            }
        }
    }

    private void carregarDoencas() {
        try {
            doencas = doencaDAO.getDoencas(); // Carrega as doenças do banco de dados
        } catch (SQLException e) {
            e.printStackTrace();
            // Você pode exibir uma mensagem de erro para o usuário aqui
        }
    }
}