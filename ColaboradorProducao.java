public class ColaboradorProducao extends Colaborador {

    private int quantidadePecas;
    private double valorPorPeca;

    public ColaboradorProducao(int matricula, String nome, int quantidadePecas, double valorPorPeca) {
        super(matricula, nome);
        this.quantidadePecas = quantidadePecas;
        this.valorPorPeca = valorPorPeca;
    }

    public int getQuantidadePecas() {
        return quantidadePecas;
    }

    public void setQuantidadePecas(int quantidadePecas) {
        this.quantidadePecas = quantidadePecas;
    }

    public double getValorPorPeca() {
        return valorPorPeca;
    }

    public void setValorPorPeca(double valorPorPeca) {
        this.valorPorPeca = valorPorPeca;
    }

    // produtividade = quantidadePecas × valorPorPeca
    public double calcularProdutividade() {
        return quantidadePecas * valorPorPeca;
    }

    // salário final = salário base + produtividade
    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase() + calcularProdutividade();
    }

    @Override
    public void exibirDados() {
        System.out.println("Tipo: Produção");
        super.exibirDados();
        System.out.println("Quantidade de peças: " + quantidadePecas);
        System.out.printf("Valor por peça: R$ %.2f%n", valorPorPeca);
        System.out.printf("Produtividade: R$ %.2f%n", calcularProdutividade());
        System.out.printf("Salário final: R$ %.2f%n", calcularSalarioFinal());
    }
}