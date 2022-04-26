package servicos;

import entidades.Produto;
import entidades.TipoDeVeiculo;
import entidades.Veiculo;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface IVeiculoServico {

    @WebMethod
    void inserirVeiculo(@WebParam(name = "veiculo") Veiculo veiculo);

    @WebMethod
    List<Veiculo> listarVeiculo();

    @WebMethod
    void excluirVeiculo (@WebParam (name="id") Integer id);

    @WebMethod
    void editarVeiculo(@WebParam (name= "veiculo") Veiculo veiculo);

    @WebMethod
    Veiculo buscarPorIdVeiculo(@WebParam(name ="id")Integer id);

}
