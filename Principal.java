import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Principal {

    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Colaborador> colaboradores = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ", 0, 5);

            switch (opcao) {
                case 1:
                    cadastrarPadrao();
                    break;
                case 2:
                    cadastrarComissionado();
                    break;
                case 3:
                    cadastrarProducao();
                    break;
                case 4:
                    gerarFolha();
                    break;
                case 5:
                    exibirResumo();
                    break;
                case 0:
                    System.out.println("Encerrando o programa. Até logo!");
                    break;
            }
        } while (opcao != 0);

        sc.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===== FOLHA DE PAGAMENTO =====");
        System.out.println("1 - Cadastrar Colaborador Padrão");
        System.out.println("2 - Cadastrar Colaborador Comissionado");
        System.out.println("3 - Cadastrar Colaborador Produção");
        System.out.println("4 - Gerar Folha de Pagamento");
        System.out.println("5 - Exibir Resumo da Folha");
        System.out.println("0 - Sair do Programa");
    }

    // ---------- Cadastros ----------

    private static void cadastrarPadrao() {
        System.out.println("\n--- Cadastro: Colaborador Padrão ---");
        int matricula = lerMatricula();
        String nome = lerTexto("Nome completo: ");

        colaboradores.add(new ColaboradorPadrao(matricula, nome));
        System.out.println("Colaborador cadastrado com sucesso!");
    }

    private static void cadastrarComissionado() {
        System.out.println("\n--- Cadastro: Colaborador Comissionado ---");
        int matricula = lerMatricula();
        String nome = lerTexto("Nome completo: ");
        double vendas = lerDecimal("Valor das vendas (R$): ", 0, Double.MAX_VALUE);
        double percentual = lerDecimal("Percentual de comissão (0 a 100): ", 0, 100);

        colaboradores.add(new ColaboradorComissionado(matricula, nome, vendas, percentual));
        System.out.println("Colaborador cadastrado com sucesso!");
    }

    private static void cadastrarProducao() {
        System.out.println("\n--- Cadastro: Colaborador Produção ---");
        int matricula = lerMatricula();
        String nome = lerTexto("Nome completo: ");
        int pecas = lerInteiro("Quantidade de peças: ", 0, Integer.MAX_VALUE);
        double valorPeca = lerDecimal("Valor por peça (R$): ", 0, Double.MAX_VALUE);

        colaboradores.add(new ColaboradorProducao(matricula, nome, pecas, valorPeca));
        System.out.println("Colaborador cadastrado com sucesso!");
    }

    // ---------- Folha e resumo ----------

    private static void gerarFolha() {
        if (colaboradores.isEmpty()) {
            System.out.println("\nNenhum colaborador cadastrado.");
            return;
        }

        System.out.println("\n===== FOLHA DE PAGAMENTO =====");
        for (Colaborador c : colaboradores) {
            System.out.println("-----------------------------");
            c.exibirDados();
        }
        System.out.println("-----------------------------");
    }

    private static void exibirResumo() {
        if (colaboradores.isEmpty()) {
            System.out.println("\nNenhum colaborador cadastrado.");
            return;
        }

        int padrao = 0, comissionados = 0, producao = 0;
        double total = 0;

        for (Colaborador c : colaboradores) {
            if (c instanceof ColaboradorComissionado) {
                comissionados++;
            } else if (c instanceof ColaboradorProducao) {
                producao++;
            } else {
                padrao++;
            }
            total += c.calcularSalarioFinal();
        }

        System.out.println("\n===== RESUMO DA FOLHA =====");
        System.out.println("Total de colaboradores: " + colaboradores.size());
        System.out.println("Padrão: " + padrao);
        System.out.println("Comissionados: " + comissionados);
        System.out.println("Produção: " + producao);
        System.out.printf("Total da folha: R$ %.2f%n", total);
        System.out.printf("Média salarial: R$ %.2f%n", total / colaboradores.size());
    }

    // ---------- Leitura com validação ----------

    // Lê uma linha do teclado. Se a entrada acabar (sem mais linhas), encerra com aviso.
    private static String lerLinha() {
        try {
            return sc.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("\nEntrada encerrada. Finalizando o programa.");
            System.exit(0);
            return "";
        }
    }

    private static int lerMatricula() {
        while (true) {
            int matricula = lerInteiro("Matrícula: ", 1, Integer.MAX_VALUE);
            if (matriculaExiste(matricula)) {
                System.out.println("Essa matrícula já está cadastrada. Tente outra.");
            } else {
                return matricula;
            }
        }
    }

    private static boolean matriculaExiste(int matricula) {
        for (Colaborador c : colaboradores) {
            if (c.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }

    private static String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = lerLinha().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("O campo não pode ficar vazio.");
        }
    }

    private static int lerInteiro(String mensagem, int minimo, int maximo) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(lerLinha().trim());
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
                System.out.println("Valor fora do permitido. Tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private static double lerDecimal(String mensagem, double minimo, double maximo) {
        while (true) {
            System.out.print(mensagem);
            try {
                // aceita vírgula ou ponto
                double valor = Double.parseDouble(lerLinha().trim().replace(",", "."));
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
                System.out.println("Valor inválido. Números negativos não são permitidos.");
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }
}