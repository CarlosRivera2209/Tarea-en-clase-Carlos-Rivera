package progra2;

public class EmpleadoComun extends empleado implements Empleable {

    protected double salario;

    public EmpleadoComun(int code, String name, double salary) {
        super(code, name);
        salario = salary;
    }

    public void setSalario(double salary) {
        this.salario = salary;
    }

    public double getSalario() {
        return salario;
    }

    public double deduct() {
        return salario * Deductible.TASA_DEDECCION;
    }

    public double pagar() {
        return salario - deduct();
    }

    public double bono() {
        return 0;
    }

    public boolean validForIncrease() {
        return true;
    }

    public void increaseIncome() {
        if (validForIncrease()) {
            salario += salario * 0.1;
        }
    }

    public String toString() {
        return super.toString() + ", Salario: " + salario;
    }
}
