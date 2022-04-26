package servicos;


import entidades.Veiculo;
import jakarta.jws.WebService;
import repositorios.TipoDeVeiculoRepositorio;
import repositorios.VeiculoRepositorio;

import java.util.List;

@WebService(endpointInterface = "servicos.IVeiculoServico")
public class VeiculoServico implements IVeiculoServico {
    private VeiculoRepositorio veiculoRepositorio = new VeiculoRepositorio();

    @Override
    public void inserirVeiculo(Veiculo veiculo) {
        veiculoRepositorio.inserir(veiculo);
    }

    @Override
    public List<Veiculo> listarVeiculo() {
        return veiculoRepositorio.listar();
    }

    @Override
    public void excluirVeiculo(Integer id) {
        veiculoRepositorio.excluir(id);
    }

    @Override
    public void editarVeiculo(Veiculo veiculo) {
        veiculoRepositorio.editar((veiculo));
    }

    @Override
    public Veiculo buscarPorIdVeiculo(Integer id) {
        return veiculoRepositorio.buscarPorId(id);
    }
}
