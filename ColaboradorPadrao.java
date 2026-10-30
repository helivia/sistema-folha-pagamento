public class ColaboradorPadrao extends Colaborador {

    public ColaboradorPadrao(int matricula, String nome) {
        super(matricula, nome);
    }

    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase();
    }

    @Override
    public void exibirDados() {
        System.out.println("Tipo: Padrão");
        super.exibirDados();
        System.out.printf("Salário final: R$ %.2f%n", calcularSalarioFinal());
    }
}