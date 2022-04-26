package repositorios;

import entidades.Categoria;
import entidades.Produto;
import entidades.TipoDeVeiculo;
import entidades.Veiculo;
import fabricas.FabricaDeConexoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoRepositorio {
    //vei_marca, vei_modelo, vei_ano_frabric, vei_ano_model, vei_preco, vei_tip_id
    public void inserir (Veiculo veiculo) {
        String sql = "INSERT veiculo (vei_marca, vei_modelo, vei_ano_fabric, vei_ano_model, vei_preco, vei_tip_id) VALUES (?, ?, ?, ?,?,?)";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, veiculo.getMarca());
            comando.setString(2, veiculo.getModelo());
            comando.setInt(3,veiculo.getAnoFabric());
            comando.setInt(4,veiculo.getAnoModel());
            comando.setDouble(5,veiculo.getPreco());
            comando.setInt(6, veiculo.getTipoDeVeiculo().getId());

            comando.execute();
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao inserir um registro", excecao);
        }

    }

    public void excluir (Integer id) {
        String sql = "DELETE FROM veiculo WHERE vei_id = ?";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            comando.execute();
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao excluir um registro", excecao);
        }
    }

    public void editar (Veiculo veiculo) {
        String sql = "UPDATE veiculo SET vei_marca = ?, vei_modelo = ?, vei_ano_fabric = ?, vei_ano_model = ?, vei_preco = ?, vei_tip_id = ? WHERE vei_id = ?";

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, veiculo.getMarca());
            comando.setString(2, veiculo.getModelo());
            comando.setInt(3,veiculo.getAnoFabric());
            comando.setInt(4,veiculo.getAnoModel());
            comando.setDouble(5,veiculo.getPreco());
            comando.setInt(6, veiculo.getTipoDeVeiculo().getId());
            comando.setInt(7,veiculo.getId());

            comando.execute();
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao editar um registro", excecao);
        }
    }

    public List<Veiculo> listar() {
        String sql = String.join(
                "\n",
                "SELECT vei_id, vei_marca, vei_modelo, vei_ano_fabric,vei_ano_model,vei_preco, tip_id, tip_nome",
                "FROM veiculo INNER JOIN tipo_de_veiculo ON vei_tip_id = tip_id",
                "ORDER BY vei_marca"
        );

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            List<Veiculo> veiculos= new ArrayList<>();

            while (resultado.next()) {
                TipoDeVeiculo tipoDeVeiculo = new TipoDeVeiculo();
                tipoDeVeiculo.setId(resultado.getInt("tip_id"));
                tipoDeVeiculo.setNome(resultado.getString("tip_nome"));

                Veiculo veiculo = new Veiculo();
                veiculo.setId(resultado.getInt("vei_id"));
                veiculo.setMarca(resultado.getString("vei_marca"));
                veiculo.setModelo(resultado.getString("vei_modelo"));
                veiculo.setAnoFabric(resultado.getInt("vei_ano_fabric"));
                veiculo.setAnoModel(resultado.getInt("vei_ano_model"));
                veiculo.setPreco(resultado.getDouble("vei_preco"));

                veiculo.setTipoDeVeiculo(tipoDeVeiculo);

                veiculos.add(veiculo);
            }

            return veiculos;
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao listar os veiculos", excecao);
        }


    }

    public Veiculo buscarPorId(Integer id) {
        String sql = String.join(
                "\n",
                "SELECT vei_id, vei_marca, vei_modelo, vei_ano_fabric,vei_ano_model,vei_preco, tip_id, tip_nome",
                "FROM veiculo INNER JOIN tipo_de_veiculo ON vei_tip_id = tip_id",
                "WHERE vei_id = ?"
        );

        try (Connection conexao = FabricaDeConexoes.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            try (ResultSet resultado = comando.executeQuery()){

                Veiculo veiculo = null;

                if (resultado.next()) {
                    TipoDeVeiculo tipoDeVeiculo = new TipoDeVeiculo();
                    tipoDeVeiculo.setId(resultado.getInt("tip_id"));
                    tipoDeVeiculo.setNome(resultado.getString("tip_nome"));

                    veiculo = new Veiculo();
                    veiculo.setId(resultado.getInt("vei_id"));
                    veiculo.setMarca(resultado.getString("vei_marca"));
                    veiculo.setModelo(resultado.getString("vei_modelo"));
                    veiculo.setAnoFabric(resultado.getInt("vei_ano_fabric"));
                    veiculo.setAnoModel(resultado.getInt("vei_ano_model"));
                    veiculo.setPreco(resultado.getDouble("vei_preco"));
                    veiculo.setTipoDeVeiculo(tipoDeVeiculo);
                }

                return veiculo;
            }
        } catch (SQLException excecao) {
            throw new RuntimeException("Erro ao buscar um registro", excecao);
        }
    }


}
