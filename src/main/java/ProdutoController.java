import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoController {
    private List<Produto> produtos;

    public ProdutoController() {
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public Optional<Produto> pesquisarProduto(String descricao) {
        return produtos.stream().filter(p -> p.getDescricao().equalsIgnoreCase(descricao)).findFirst();
    }

    public boolean alterarProduto(String descricao, Produto novoProduto) {
        Optional<Produto> produtoOptional = pesquisarProduto(descricao);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            produto.setTipo(novoProduto.getTipo());
            produto.setDescricao(novoProduto.getDescricao());
            produto.setPeso(novoProduto.getPeso());
            produto.setQuantidade(novoProduto.getQuantidade());
            produto.setUnidadeMedida(novoProduto.getUnidadeMedida());
            return true;
        }
        return false;
    }

    public boolean excluirProduto(String descricao) {
        Optional<Produto> produtoOptional = pesquisarProduto(descricao);
        if (produtoOptional.isPresent()) {
            produtos.remove(produtoOptional.get());
            return true;
        }
        return false;
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }
}
