package sosrs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoencaDAO {
    public void adicionarDoenca(Doenca doenca) throws SQLException {
        String sql = "INSERT INTO doencas (nome, sintomas, tratamento, informacoes, picada) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, doenca.getNome());
            stmt.setString(2, String.join(",", doenca.getSintomas()));
            stmt.setString(3, doenca.getTratamento());
            stmt.setString(4, doenca.getInformacoes());
            stmt.setBoolean(5, doenca.isPicada());
            stmt.executeUpdate();
        }
    }

    public List<Doenca> getDoencas() throws SQLException {
        List<Doenca> doencas = new ArrayList<>();
        String sql = "SELECT * FROM doencas";
        try (Connection connection = Conexao.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                List<String> sintomas = List.of(rs.getString("sintomas").split(","));
                String tratamento = rs.getString("tratamento");
                String informacoes = rs.getString("informacoes");
                boolean picada = rs.getBoolean("picada");

                doencas.add(new Doenca(id, nome, sintomas, tratamento, informacoes, picada));
            }
        }
        return doencas;
    }

    public Doenca getDoenca(String nome) throws SQLException {
        String sql = "SELECT * FROM doencas WHERE nome = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    List<String> sintomas = List.of(rs.getString("sintomas").split(","));
                    String tratamento = rs.getString("tratamento");
                    String informacoes = rs.getString("informacoes");
                    boolean picada = rs.getBoolean("picada");

                    return new Doenca(id, nome, sintomas, tratamento, informacoes, picada);
                }
            }
        }
        return null;
    }

    public void atualizarDoenca(String nomeOriginal, Doenca doencaAtualizada) throws SQLException {
        String sql = "UPDATE doencas SET nome = ?, sintomas = ?, tratamento = ?, informacoes = ?, picada = ? WHERE nome = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, doencaAtualizada.getNome());
            stmt.setString(2, String.join(",", doencaAtualizada.getSintomas()));
            stmt.setString(3, doencaAtualizada.getTratamento());
            stmt.setString(4, doencaAtualizada.getInformacoes());
            stmt.setBoolean(5, doencaAtualizada.isPicada());
            stmt.setString(6, nomeOriginal);
            stmt.executeUpdate();
        }
    }
}
