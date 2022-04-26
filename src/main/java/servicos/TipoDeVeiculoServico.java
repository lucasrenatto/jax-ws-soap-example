package servicos;

import entidades.Categoria;
import entidades.TipoDeVeiculo;
import jakarta.jws.WebService;
import repositorios.TipoDeVeiculoRepositorio;

import java.util.List;

@WebService(endpointInterface = "servicos.ITipoDeVeiculoServico")
public class TipoDeVeiculoServico  implements ITipoDeVeiculoServico{
    private TipoDeVeiculoRepositorio tipoDeVeiculoRepositorio = new TipoDeVeiculoRepositorio();
    @Override
    public void inserirTipo(TipoDeVeiculo tipoDeVeiculo) {
        tipoDeVeiculoRepositorio.inserir(tipoDeVeiculo);
    }

    @Override
    public List<TipoDeVeiculo> listarTipo() {
        return tipoDeVeiculoRepositorio.listar();
    }

    @Override
    public void excluirTipo(Integer id) {
    tipoDeVeiculoRepositorio.excluir(id);
    }

    @Override
    public void editarTipo(TipoDeVeiculo tipoDeVeiculo) {
        tipoDeVeiculoRepositorio.editar((tipoDeVeiculo));
    }

    @Override
    public TipoDeVeiculo buscarTipoPorId(Integer id) {
        return tipoDeVeiculoRepositorio.buscarPorId(id);
    }
}
