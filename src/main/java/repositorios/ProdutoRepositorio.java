package repositorios;

import entidades.Categoria;
import entidades.Produto;
import fabricas.FabricaDeConexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositorio {
    public void inserir (Produto produto) {
        String sql = "INSERT produto (pro_nome, pro_quantidade, pro_preco, pro_cat_id) VALUES (?, ?, ?, ?)";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, produto.getNome());
            comando.setInt(2, produto.getQuantidade());
            comando.setDouble(3, produto.getPreco());
            comando.setInt(4, produto.getCategoria().getId());

            comando.execute();
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao inserir um registro", excecao);
        }
    }

    public void excluir (Integer id) {
        String sql = "DELETE FROM produto WHERE pro_id = ?";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            comando.execute();
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao excluir um registro", excecao);
        }
    }

    public void editar (Produto produto) {
        String sql = "UPDATE produto SET pro_nome = ?, pro_quantidade = ?, pro_preco = ?, pro_cat_id = ? WHERE pro_id = ?";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, produto.getNome());
            comando.setInt(2, produto.getQuantidade());
            comando.setDouble(3, produto.getPreco());
            comando.setInt(4, produto.getCategoria().getId());
            comando.setInt(5, produto.getId());

            comando.execute();
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao editar um registro", excecao);
        }
    }

    public List<Produto> listar() {
        String sql = String.join(
                "\n",
                "SELECT pro_id, pro_nome, pro_quantidade, pro_preco, cat_id, cat_nome",
                "FROM produto INNER JOIN categoria ON pro_cat_id = cat_id",
                "ORDER BY pro_nome"
        );

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            List<Produto> produtos = new ArrayList<>();

            while (resultado.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(resultado.getInt("cat_id"));
                categoria.setNome(resultado.getString("cat_nome"));

                Produto produto = new Produto();
                produto.setId(resultado.getInt("pro_id"));
                produto.setNome(resultado.getString("pro_nome"));
                produto.setPreco(resultado.getDouble("pro_preco"));
                produto.setQuantidade(resultado.getInt("pro_quantidade"));
                produto.setCategoria(categoria);

                produtos.add(produto);
            }

            return produtos;
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao listar os registros", excecao);
        }
    }

    public Produto buscarPorId(Integer id) {
        String sql = String.join(
                "\n",
                "SELECT pro_id, pro_nome, pro_quantidade, pro_preco, cat_id, cat_nome",
                "FROM produto INNER JOIN categoria ON pro_cat_id = cat_id",
                "WHERE pro_id = ?"
        );

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            try (ResultSet resultado = comando.executeQuery()){

                Produto produto = null;

                if (resultado.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(resultado.getInt("cat_id"));
                    categoria.setNome(resultado.getString("cat_nome"));

                    produto = new Produto();
                    produto.setId(resultado.getInt("pro_id"));
                    produto.setNome(resultado.getString("pro_nome"));
                    produto.setPreco(resultado.getDouble("pro_preco"));
                    produto.setQuantidade(resultado.getInt("pro_quantidade"));
                    produto.setCategoria(categoria);
                }

                return produto;
            }
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao buscar um registro", excecao);
        }
    }
}
