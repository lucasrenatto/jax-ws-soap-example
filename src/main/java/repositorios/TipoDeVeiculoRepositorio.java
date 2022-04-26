package repositorios;

import entidades.Categoria;
import entidades.Produto;
import entidades.TipoDeVeiculo;
import fabricas.FabricaDeConexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoDeVeiculoRepositorio {
    public void inserir (TipoDeVeiculo tipoDeVeiculo) {
        String sql = "INSERT tipo_de_veiculo (tip_nome) VALUES (?)";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, tipoDeVeiculo.getNome());

            comando.execute();
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao inserir um tipo", excecao);
        }
    }

    public void excluir (Integer id) {
        String sql = "DELETE FROM tipo_de_veiculo WHERE tip_id = ?";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            comando.execute();
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao excluir uma categoria", excecao);
        }
    }

    public void editar (TipoDeVeiculo tipoDeVeiculo) {
        String sql = "UPDATE tipo_de_veiculo SET tip_nome = ? WHERE tip_id = ?";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, tipoDeVeiculo.getNome());
            comando.setInt(2, tipoDeVeiculo.getId());

            comando.execute();
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao editar uma categoria", excecao);
        }
    }

    public List<TipoDeVeiculo> listar() {
        String sql = "SELECT tip_id, tip_nome FROM tipo_de_veiculo ORDER BY tip_nome";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            List<TipoDeVeiculo> tipoDeVeiculos = new ArrayList<>();

            while (resultado.next()) {
                TipoDeVeiculo tipoDeVeiculo = new TipoDeVeiculo();
                tipoDeVeiculo.setId(resultado.getInt("tip_id"));
                tipoDeVeiculo.setNome(resultado.getString("tip_nome"));

                tipoDeVeiculos.add(tipoDeVeiculo);
            }

            return tipoDeVeiculos;
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao listar as categorias", excecao);
        }
    }



    public TipoDeVeiculo buscarPorId(Integer id) {
        String sql = "SELECT tip_id, tip_nome FROM tipo_de_veiculo WHERE tip_id = ?";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            try (ResultSet resultado = comando.executeQuery()) {
                TipoDeVeiculo tipoDeVeiculo = null;

                if (resultado.next()) {
                    tipoDeVeiculo = new TipoDeVeiculo();
                    tipoDeVeiculo.setId(resultado.getInt("tip_id"));
                    tipoDeVeiculo.setNome(resultado.getString("tip_nome"));
                }

                return tipoDeVeiculo;
            }
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao buscar uma categoria", excecao);
        }
    }



}
