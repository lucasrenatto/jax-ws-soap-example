package servicos;

import entidades.Produto;
import jakarta.jws.WebService;
import repositorios.ProdutoRepositorio;

import java.util.List;

@WebService(endpointInterface = "servicos.IProdutoServico")
public class ProdutoServico implements  IProdutoServico {
    private ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio();
    @Override
    public void novo(Produto produto) {
        produtoRepositorio.inserir(produto);
    }

    @Override
    public void remover(Integer id) {
        produtoRepositorio.excluir(id);
    }

    @Override
    public void alterar(Produto produto) {
        produtoRepositorio.editar((produto));
    }

    @Override
    public List<Produto> buscarTudo() {
        return produtoRepositorio.listar();
    }

    @Override
    public Produto buscarPorId(Integer id) {
        return produtoRepositorio.buscarPorId(id);
    }
}
