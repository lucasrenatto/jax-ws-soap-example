package repositorios;

import entidades.Categoria;
import fabricas.FabricaDeConexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorio {

    public void inserir (Categoria categoria) {
        String sql = "INSERT categoria (cat_nome) VALUES (?)";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, categoria.getNome());

            comando.execute();
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao tentar inserir uma categoria", excecao);
        }
    }

    public List<Categoria> listar() {
        String sql = "SELECT cat_id, cat_nome FROM categoria ORDER BY cat_nome";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            List<Categoria> categorias = new ArrayList<>();

            while (resultado.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(resultado.getInt("cat_id"));
                categoria.setNome(resultado.getString("cat_nome"));

                categorias.add(categoria);
            }

            return categorias;
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao listar as categorias", excecao);
        }
    }

    public void excluir (Integer id) {
        String sql = "DELETE FROM categoria WHERE cat_id = ?";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            comando.execute();
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao excluir uma categoria", excecao);
        }
    }

    public void editar (Categoria categoria) {
        String sql = "UPDATE categoria SET cat_nome = ? WHERE cat_id = ?";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, categoria.getNome());
            comando.setInt(2, categoria.getId());

            comando.execute();
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao editar uma categoria", excecao);
        }
    }

    public Categoria buscarPorId(Integer id) {
        String sql = "SELECT cat_id, cat_nome FROM categoria WHERE cat_id = ?";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            try (ResultSet resultado = comando.executeQuery()) {
                Categoria categoria = null;

                if (resultado.next()) {
                    categoria = new Categoria();
                    categoria.setId(resultado.getInt("cat_id"));
                    categoria.setNome(resultado.getString("cat_nome"));
                }

                return categoria;
            }
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao buscar uma categoria", excecao);
        }
    }
}
