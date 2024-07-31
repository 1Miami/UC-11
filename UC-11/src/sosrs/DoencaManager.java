package sosrs;

import java.util.ArrayList;
import java.util.List;

public class DoencaManager {
    private List<Doenca> doencas;
    private static DoencaManager instance;

    private DoencaManager() {
        doencas = new ArrayList<>();
    }

    public static DoencaManager getInstance() {
        if (instance == null) {
            instance = new DoencaManager();
        }
        return instance;
    }

    public void adicionarDoenca(Doenca doenca) {
        doencas.add(doenca);
    }

    public List<Doenca> getDoencas() {
        return doencas;
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
       
    }
    
}

    
}
