package sosrs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoencaManager {
    private static DoencaManager instance;
    private DoencaDAO doencaDAO;
    private List<Doenca> doencas;

    private DoencaManager() {
        doencaDAO = new DoencaDAO();
        doencas = new ArrayList<>();
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
            doencas.add(doenca);
        } catch (SQLException e) {
            // Log the error
            System.err.println("Erro ao adicionar doença: " + e.getMessage());
        }
    }

    public List<Doenca> getDoencas() {
        return new ArrayList<>(doencas);
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
                doencaDAO.atualizarDoenca(nomeOriginal, doencaAtualizada);
            } catch (SQLException e) {
                // Log the error
                System.err.println("Erro ao atualizar doença: " + e.getMessage());
            }
        }
    }

    public void excluirDoenca(String nomeDoenca) {
        try {
            doencaDAO.excluirDoenca(nomeDoenca);
            doencas.removeIf(d -> d.getNome().equals(nomeDoenca));
        } catch (SQLException e) {
            // Log the error
            System.err.println("Erro ao excluir doença: " + e.getMessage());
        }
    }

    private void carregarDoencas() {
        try {
            doencas = doencaDAO.getDoencas();
        } catch (SQLException e) {
            // Log the error
            System.err.println("Erro ao carregar doenças: " + e.getMessage());
        }
    }
}
