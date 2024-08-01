package progra2;

import java.util.Calendar;

public final class EmpleadoPorVenta extends EmpleadoComun {

    private double ventas[], tasa;

    public EmpleadoPorVenta(int code, String name, double salary) {
        super(code, name, salary);
        ventas = new double[12];
        tasa = 0.05;
    }

    private int mesActual() {
        Calendar mes = Calendar.getInstance();
        return mes.get(Calendar.MONTH);
    }

    public void addVenta(double monto) {
        ventas[mesActual()] += monto;
    }

    public double comision() {
        return ventas[mesActual()] * tasa;
    }

    public double pagar() {
        return super.pagar() + comision();
    }

    public String toString() {
        return super.toString() + ", comisión: " + comision();
    }

    public void increaseIncome() {
        if (tasa < 0.2) {
            tasa += 0.01;
        }
    }
}
