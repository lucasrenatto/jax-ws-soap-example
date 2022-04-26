package servicos;

import entidades.Categoria;
import entidades.Produto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;
@WebService
public interface IProdutoServico {
    @WebMethod
    void novo(@WebParam(name = "produto") Produto produto);

    @WebMethod
    void remover (@WebParam(name = "id") Integer id);

    @WebMethod
    void alterar(@WebParam(name = "produto") Produto produto);

    @WebMethod
    List<Produto> buscarTudo();

    @WebMethod
    Produto buscarPorId(@WebParam(name = "id") Integer id);


}
