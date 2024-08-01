package progra2;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class empleado implements Serializable {

    protected int codigo;
    protected String nombre;
    protected Calendar contratacion;

    public empleado(int code, String name) {
        codigo = code;
        nombre = name;
        contratacion = Calendar.getInstance();
    }

    public final int getCodigo() {
        return codigo;
    }

    public final String getNombre() {
        return nombre;
    }

    public final Calendar getContratacion() {
        return contratacion;
    }

    public final void setNombre(String name) {
        this.nombre = name;
    }

    public abstract double pagar();

    public abstract double bono();

    public String toString() {
        SimpleDateFormat fecha = new SimpleDateFormat("D/MM/YY");
        return "Empleado {Código: " + codigo + ", Nombre: " + nombre + ", Fecha Contratación: " + fecha.format(contratacion.getTime());
    }

}
