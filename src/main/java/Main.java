import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoController produtoController = new ProdutoController();

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Pesquisar Produto");
            System.out.println("3. Alterar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("5. Listar Produtos");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Tipo: ");
                    String tipo = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Peso (kg): ");
                    double peso = scanner.nextDouble();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Unidade de Medida (metro, metro quadrado, litro, kg): ");
                    String unidadeMedida = scanner.nextLine();

                    try {
                        Produto produto = new Produto(tipo, descricao, peso, quantidade, unidadeMedida);
                        produtoController.adicionarProduto(produto);
                        System.out.println("Produto cadastrado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Descrição do produto a pesquisar: ");
                    String descricaoPesquisa = scanner.nextLine();
                    produtoController.pesquisarProduto(descricaoPesquisa)
                        .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Produto não encontrado."));
                    break;
                case 3:
                    System.out.print("Descrição do produto a alterar: ");
                    String descricaoAlterar = scanner.nextLine();
                    System.out.print("Novo Tipo: ");
                    String novoTipo = scanner.nextLine();
                    System.out.print("Nova Descrição: ");
                    String novaDescricao = scanner.nextLine();
                    System.out.print("Novo Peso (kg): ");
                    double novoPeso = scanner.nextDouble();
                    System.out.print("Nova Quantidade: ");
                    int novaQuantidade = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Nova Unidade de Medida (metro, metro quadrado, litro, kg): ");
                    String novaUnidadeMedida = scanner.nextLine();

                    try {
                        Produto novoProduto = new Produto(novoTipo, novaDescricao, novoPeso, novaQuantidade, novaUnidadeMedida);
                        if (produtoController.alterarProduto(descricaoAlterar, novoProduto)) {
                            System.out.println("Produto alterado com sucesso!");
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Descrição do produto a excluir: ");
                    String descricaoExcluir = scanner.nextLine();
                    if (produtoController.excluirProduto(descricaoExcluir)) {
                        System.out.println("Produto excluído com sucesso!");
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;
                case 5:
                    System.out.println("Lista de Produtos:");
                    produtoController.listarProdutos().forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
