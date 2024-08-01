package progra2;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Empresa {

    static Scanner lea = new Scanner(System.in);
    static List<empleado> empleados = new ArrayList<>();

    public static void main(String[] args) {
        int op;

        do {
            System.out.println("1- Agregar Empleado");
            System.out.println("2- Pagar Empleado");
            System.out.println("3- Lista de Empleados");
            System.out.println("4- Sub Menu especifico");
            System.out.println("5- Salir");
            System.out.print("Escoja Opcion: ");
            op = lea.nextInt();

            switch (op) {
                case 1:
                    hire();
                    break;
                case 2:
                    pay();
                    break;
                case 3:
                    list();
                    break;
                case 4:
                    submenu();
                    break;
            }
        } while (op != 5);
    }

    private static empleado search(int cod) {
        for (empleado e : empleados) {
            if (e.getCodigo() == cod) {
                return e;
            }
        }
        return null;
    }

    private static void hire() {
        try {
            System.out.println("Ingrese el tipo de empleado (COMUN, VENTA, TEMPORAL, HORAS): ");
            String tipo = lea.next().toUpperCase();

            System.out.println("Ingrese el codigo del empleado: ");
            int codigo = lea.nextInt();

            System.out.println("Ingrese el nombre del empleado: ");
            String nombre = lea.next();

            if (search(codigo) != null) {
                System.out.println("Codigo de empleado ya existente. Regresando al menú principal.");
                return;
            }

            double salario;
            switch (tipo) {
                case "COMUN":
                    System.out.println("Ingrese el salario del empleado: ");
                    salario = lea.nextDouble();
                    empleados.add(new EmpleadoComun(codigo, nombre, salario));
                    break;
                case "VENTA":
                    System.out.println("Ingrese el salario del empleado: ");
                    salario = lea.nextDouble();
                    empleados.add(new EmpleadoPorVenta(codigo, nombre, salario));
                    break;
                case "TEMPORAL":
                    empleados.add(new EmpleadoTemporal(codigo, nombre));
                    break;
                case "HORAS":
                    empleados.add(new EmpleadoPorHoras(codigo, nombre));
                    break;
                default:
                    System.out.println("Tipo de empleado no reconocido. Regresando al menú principal.");
                    lea.nextLine();
                    return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida. Asegúrese de ingresar datos validos.");
            lea.nextLine();
        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado: " + e.getMessage());
        }
    }

    private static void pay() {
        System.out.println("Ingrese el codigo del empleado: ");
        int codigo = lea.nextInt();
        empleado e = search(codigo);

        if (e != null) {
            System.out.println("Pago: " + e.pagar());
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void list() {
        for (empleado e : empleados) {
            System.out.println(e);
        }
    }

    private static void submenu() {
        int op;
        do {
            System.out.println("1-Fecha Fin Contrato a Temporales");
            System.out.println("2-Ingresar Venta");
            System.out.println("3-Ingresar Horas de Trabajo");
            System.out.println("4-Regresar al Menu Principal");
            System.out.print("Escoja Opcion: ");
            op = lea.nextInt();

            switch (op) {
                case 1:
                    setFin();
                    break;
                case 2:
                    ventas();
                    break;
                case 3:
                    horas();
                    break;
            }
        } while (op != 4);
    }

    private static void setFin() {
        System.out.println("Ingrese el codigo del empleado: ");
        int codigo = lea.nextInt();
        empleado e = search(codigo);

        if (e != null && e instanceof EmpleadoTemporal) {
            EmpleadoTemporal temp = (EmpleadoTemporal) e;
            System.out.println("Ingrese año de fin de contrato: ");
            int year = lea.nextInt();
            System.out.println("Ingrese mes de fin de contrato: ");
            int mes = lea.nextInt();
            System.out.println("Ingrese dia de fin de contrato: ");
            int dia = lea.nextInt();
            temp.setFinContrato(year, mes, dia);
        } else {
            System.out.println("Empleado no encontrado o no es temporal.");
        }
    }

    private static void ventas() {
        System.out.println("Ingrese el codigo del empleado: ");
        int codigo = lea.nextInt();
        empleado e = search(codigo);

        if (e != null && e instanceof EmpleadoPorVenta) {
            EmpleadoPorVenta v = (EmpleadoPorVenta) e;
            System.out.println("Ingrese el monto de la venta: ");
            double monto = lea.nextDouble();
            v.addVenta(monto);
        } else {
            System.out.println("Empleado no encontrado o no es por venta.");
        }
    }

    private static void horas() {
        System.out.println("Ingrese el codigo del empleado: ");
        int codigo = lea.nextInt();
        empleado e = search(codigo);

        if (e != null && e instanceof EmpleadoPorHoras) {
            EmpleadoPorHoras h = (EmpleadoPorHoras) e;
            System.out.println("Ingrese las horas trabajadas: ");
            int horas = lea.nextInt();
            if (horas > 0) {
                h.setHorasT(horas);
            } else {
                System.out.println("La cantidad de horas debe ser positiva.");
            }
        } else {
            System.out.println("Empleado no encontrado o no es por horas.");
        }
    }

}
