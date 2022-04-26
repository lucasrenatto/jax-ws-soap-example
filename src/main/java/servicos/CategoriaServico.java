package servicos;

import entidades.Categoria;
import jakarta.jws.WebService;
import repositorios.CategoriaRepositorio;

import java.util.List;
@WebService(endpointInterface = "servicos.ICategoriaServico")
public class CategoriaServico implements ICategoriaServico {
    private CategoriaRepositorio categoriaRepositorio = new CategoriaRepositorio();
    @Override
    public void inserir(Categoria categoria) {
    categoriaRepositorio.inserir(categoria);
    }

    @Override
    public List<Categoria> listar() {
        return categoriaRepositorio.listar();
    }

    @Override
    public void excluir(Integer id) {
        categoriaRepositorio.excluir(id);

    }

    @Override
    public void editar(Categoria categoria) {
        categoriaRepositorio.editar(categoria);
    }

    @Override
    public Categoria buscarPorId(Integer id) {
        return categoriaRepositorio.buscarPorId(id);
    }


}
