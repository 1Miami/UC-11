package sosrs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoencaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/sosrs";
    private static final String USER = "root"; // Substitua pelo seu usuário do MySQL
    private static final String PASSWORD = "root"; // Substitua pela sua senha do MySQL

    private Connection conectarBanco() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void adicionarDoenca(Doenca doenca) throws SQLException {
        String sql = "INSERT INTO doencas (nome, sintomas, tratamento, informacoes, picada) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectarBanco();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, doenca.getNome());
            pstmt.setString(2, String.join(",", doenca.getSintomas()));
            pstmt.setString(3, doenca.getTratamento());
            pstmt.setString(4, doenca.getInformacoes());
            pstmt.setBoolean(5, doenca.isPicada());
            pstmt.executeUpdate();
        }
    }

    public List<Doenca> getDoencas() throws SQLException {
        List<Doenca> doencas = new ArrayList<>();
        String sql = "SELECT * FROM doencas"; // Correção aqui
        try (Connection conn = conectarBanco();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                String sintomas = rs.getString("sintomas");
                String tratamento = rs.getString("tratamento");
                String informacoes = rs.getString("informacoes");
                boolean picada = rs.getBoolean("picada");

                Doenca doenca = new Doenca(nome, List.of(sintomas.split(",")), tratamento, informacoes, picada);
                doencas.add(doenca);
            }
        }
        return doencas;
    }

    public void atualizarDoenca(String nomeOriginal, Doenca doencaAtualizada) throws SQLException {
        String sql = "UPDATE doencas SET nome = ?, sintomas = ?, tratamento = ?, informacoes = ?, picada = ? WHERE nome = ?"; // Correção aqui
        try (Connection conn = conectarBanco();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, doencaAtualizada.getNome());
            pstmt.setString(2, String.join(",", doencaAtualizada.getSintomas()));
            pstmt.setString(3, doencaAtualizada.getTratamento());
            pstmt.setString(4, doencaAtualizada.getInformacoes());
            pstmt.setBoolean(5, doencaAtualizada.isPicada());
            pstmt.setString(6, nomeOriginal);
            pstmt.executeUpdate();
        }
    }

    public void excluirDoenca(String nomeDoenca) throws SQLException {
        String sql = "DELETE FROM doencas WHERE nome = ?"; // Correção aqui
        try (Connection conn = conectarBanco();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nomeDoenca);
            pstmt.executeUpdate();
        }
    }
}
