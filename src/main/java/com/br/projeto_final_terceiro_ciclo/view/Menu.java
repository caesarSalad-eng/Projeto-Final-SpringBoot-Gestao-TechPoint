package com.br.projeto_final_terceiro_ciclo.view;

import com.br.projeto_final_terceiro_ciclo.controller.*;
import com.br.projeto_final_terceiro_ciclo.service.PessoaFisicaService;
import com.br.projeto_final_terceiro_ciclo.service.PessoaJuridicaService;
import com.br.projeto_final_terceiro_ciclo.service.ProdutoService;
import com.br.projeto_final_terceiro_ciclo.service.PedidoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;
import java.util.List;

@Component
public class Menu implements CommandLineRunner {

    private final Scanner sc = new Scanner(System.in);

    private final PessoaFisicaService pessoaFisicaService;
    private final PessoaJuridicaService pessoaJuridicaService;
    private final ProdutoService produtoService;
    private final PedidoService pedidoService;

    public Menu(ApplicationContext applicationContext, PessoaFisicaService pessoaFisicaService, PessoaJuridicaService pessoaJuridicaService, ProdutoService produtoService, PedidoService pedidoService) {

        this.pessoaFisicaService = pessoaFisicaService;

        this.pessoaJuridicaService = pessoaJuridicaService;

        this.produtoService = produtoService;

        this.pedidoService = pedidoService;

    }

    @Override
    public void run(String... args) throws Exception {

        exibirMenu();

    }

    public void exibirMenu() {

        int opcao = -1;

        do {
            System.out.println("\n=== TechPoint Informática ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Produto");
            System.out.println("3. Realizar Pedido");
            System.out.println("4. Sair");
            System.out.print("Escolha: ");

            try {

                opcao = sc.nextInt();
                sc.nextLine(); //limpa o buffer do teclado

                switch (opcao) {

                    case 1:

                        cadastrarCliente();

                        break;

                    case 2:

                        cadastrarProduto();

                        break;

                    case 3:

                        realizarPedido();

                        break;

                    case 4:

                        System.out.println("Saindo...");

                        System.exit(0);

                        break;

                    default:

                        System.out.println("Opção inválida! Tente novamente.");

                        break;
                }

            } catch (Exception e) {
                System.out.println("Entrada inválida! Digite apenas números correspondentes às opções.");
                sc.nextLine(); // limpa o buffer pra evitar loop infinito
            }

        } while (opcao != 0);
    }

    //Métodos com nome autoexplicativo
    private void cadastrarCliente() {

        System.out.println("==Cadastro de cliente==");
        System.out.print("É pessoa física (1) ou jurídica (2)? ");

        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Endereço: ");
        String endereco = sc.nextLine();

        if (tipo == 1) {

            System.out.print("CPF: ");
            String cpf = sc.nextLine();

            PessoaFisica pf = new PessoaFisica(0, nome, telefone, email, endereco, cpf);
            pessoaFisicaService.cadastrar(pf);

        } else if (tipo == 2) {

            System.out.print("CNPJ: ");
            String cnpj = sc.nextLine();

            PessoaJuridica pj = new PessoaJuridica(0, nome, telefone, email, endereco, cnpj);
            pessoaJuridicaService.cadastrar(pj);

        } else {

            System.out.println("Tipo inválido!");
        }
    }

    private void cadastrarProduto() {

        System.out.println("==Cadastro de produto==");
        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();

        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        System.out.print("Preço: ");
        double preco = sc.nextDouble();

        System.out.print("Estoque inicial: ");
        int estoque = sc.nextInt();
        sc.nextLine();

        Produto produto = new Produto(0, nome, categoria, preco, estoque);
        produtoService.cadastrar(produto);

    }

    private void realizarPedido() {

        System.out.println("==Realizar pedido==");

        System.out.print("ID do cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        Optional<PessoaFisica> optPf = pessoaFisicaService.consultar(idCliente);
        Optional<PessoaJuridica> optPj = pessoaJuridicaService.consultar(idCliente);

        Cliente cliente = null;

        if (optPf.isPresent()) {

            cliente = optPf.get();

        } else if (optPj.isPresent()) {

            cliente = optPj.get();

        }

        if (cliente == null) {

            System.out.println("Cliente não encontrado. Deseja cadastrá-lo agora? (S/N): ");
            String resposta = sc.nextLine().trim().toUpperCase();

            if (resposta.equalsIgnoreCase("S")) {

                cadastrarClienteRapido(); // o ID é gerado automaticamente no service

                List<PessoaFisica> pfs = pessoaFisicaService.listar();
                List<PessoaJuridica> pjs = pessoaJuridicaService.listar();

                if (!pfs.isEmpty()) cliente = pfs.getLast();

                else if (!pjs.isEmpty()) cliente = pjs.get(pjs.size() - 1);

                if (cliente == null) {

                    System.out.println("Falha ao recuperar cliente recém-cadastrado. Retornando...");

                    return;

                }

            } else {

                System.out.println("Operação cancelada. Retornando ao menu...");

                return;
            }
        }


        Pedido pedido = new Pedido(0, cliente);

        int codigoProduto;

        do {
            System.out.print("Código do produto (0 para finalizar): ");
            codigoProduto = sc.nextInt();
            sc.nextLine();

            if (codigoProduto != 0) {

                Optional<Produto> optProduto = produtoService.consultar(codigoProduto);

                if (optProduto.isEmpty()) {

                    System.out.print("Produto não encontrado. Deseja cadastrá-lo agora? (S/N): ");
                    String resposta = sc.nextLine().trim().toUpperCase();

                    if (resposta.equals("S")) {

                        cadastrarProdutoRapido();

                        List<Produto> produtos = produtoService.listar();

                        if (!produtos.isEmpty()) {

                            optProduto = Optional.of(produtos.get(produtos.size() - 1));
                        }

                    } else {

                        System.out.println("Produto ignorado. Voltando...");

                        continue;
                    }
                }

                if (optProduto.isPresent()) {

                    Produto produto = optProduto.get();
                    System.out.print("Quantidade: ");
                    int qtd = sc.nextInt();
                    sc.nextLine();

                    if (qtd <= produto.getEstoque()) {

                        pedido.adicionarItem(new ItemPedido(produto, qtd));

                    } else {

                        System.out.println("Estoque insuficiente!");
                    }
                }
            }

        } while (codigoProduto != 0);

            pedidoService.cadastrar(pedido);

            System.out.println("\nPedido realizado com sucesso!");
            System.out.println(pedido);
            System.out.println("Itens do pedido:");
            pedido.getItens().forEach(System.out::println);
            System.out.println("Total: R$ " + pedido.calcularTotal());

    }

    private void cadastrarClienteRapido() {

        System.out.println("==Cadastro rápido de cliente==");
        System.out.print("É pessoa física (1) ou jurídica (2)? ");

        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Endereço: ");
        String endereco = sc.nextLine();

        if (tipo == 1) {

            System.out.print("CPF: ");
            String cpf = sc.nextLine();

            PessoaFisica pf = new PessoaFisica(0, nome, telefone, email, endereco, cpf);
            pessoaFisicaService.cadastrar(pf);

        } else if (tipo == 2) {

            System.out.print("CNPJ: ");
            String cnpj = sc.nextLine();

            PessoaJuridica pj = new PessoaJuridica(0, nome, telefone, email, endereco, cnpj);
            pessoaJuridicaService.cadastrar(pj);

        } else {

            System.out.println("Tipo inválido! Cadastro cancelado.");
        }
    }

    private void cadastrarProdutoRapido() {

        System.out.println("\n--- Cadastro Rápido de Produto ---");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        double preco = 0;

        while (true) {

            try {

                System.out.print("Preço (R$): ");
                preco = sc.nextDouble();
                sc.nextLine();

                break;

            } catch (Exception e) {

                System.out.println("Valor inválido! Digite um número válido para o preço.");
                sc.nextLine(); // limpar buffer

            }
        }

        System.out.print("Estoque inicial: ");
        int estoque = sc.nextInt();
        sc.nextLine();

        Produto novo = new Produto(0, nome, categoria, preco, estoque);
        produtoService.cadastrar(novo);

    }

}
