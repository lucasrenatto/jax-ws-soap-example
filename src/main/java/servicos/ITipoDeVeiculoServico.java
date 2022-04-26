package servicos;

import entidades.Categoria;
import entidades.TipoDeVeiculo;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface ITipoDeVeiculoServico {

    @WebMethod
    void inserirTipo(@WebParam(name = "tipoDeVeiculo") TipoDeVeiculo tipoDeVeiculo);

    @WebMethod
    List<TipoDeVeiculo> listarTipo();

    @WebMethod
    void excluirTipo (@WebParam (name="id") Integer id);

    @WebMethod
    void editarTipo(@WebParam (name= "tipoDeVeiculo") TipoDeVeiculo tipoDeVeiculo);

    @WebMethod
    TipoDeVeiculo buscarTipoPorId(@WebParam(name ="id")Integer id);
}
