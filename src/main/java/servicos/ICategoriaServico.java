package servicos;

import entidades.Categoria;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface ICategoriaServico {
    @WebMethod
    void inserir(@WebParam (name = "categoria") Categoria categoria);

    @WebMethod
    List<Categoria> listar();

    @WebMethod
    void excluir (@WebParam (name="id") Integer id);

    @WebMethod
    void editar(@WebParam (name= "categoria") Categoria categoria);

    @WebMethod
    Categoria buscarPorId(@WebParam(name ="id")Integer id);


}
