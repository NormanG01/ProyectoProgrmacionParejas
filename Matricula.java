import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Matricula {
    public static void main(String[] args) {
        Estudiante estudiante = new Estudiante();
        estudiante.ingresarInformacion();
        estudiante.ingresarAsignaturas();
        estudiante.mostrarReporte();
    }
}

class Estudiante {
    private String numeroRecibo;
    private String numeroInscripcion;
    private String nombresApellidos;
    private String numeroCarnet;
    private String carrera;
    private String turno;
    private String planEstudio;
    private String semestre;
    private String fechaMatricula;
    private ArrayList<Asignatura> asignaturas;

    public Estudiante() {
        asignaturas = new ArrayList<>();
    }

    public void ingresarInformacion() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Número de recibo: ");
        numeroRecibo = scanner.nextLine();
        System.out.print("Número de inscripción: ");
        numeroInscripcion = scanner.nextLine();
        System.out.print("Nombres y apellidos: ");
        nombresApellidos = scanner.nextLine();
        System.out.print("Número de carnet: ");
        numeroCarnet = scanner.nextLine();
        System.out.print("Carrera: ");
        carrera = scanner.nextLine();
        System.out.print("Turno: ");
        turno = scanner.nextLine();
        System.out.print("Plan de estudio: ");
        planEstudio = scanner.nextLine();
        System.out.print("Semestre: ");
        semestre = scanner.nextLine();
        System.out.print("Fecha de matrícula (YYYY-MM-DD): ");
        fechaMatricula = scanner.nextLine();
    }

    public void ingresarAsignaturas() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de asignaturas a inscribir (max 7): ");
        int numAsignaturas = obtenerEnteroValidado(scanner, 1, 7);
        if (numAsignaturas > 7) {
            System.out.println("Error: La cantidad de asignaturas no puede ser mayor que 7.");
            return;
        }

        int totalCreditos = 0;

        for (int i = 0; i < numAsignaturas; i++) {
            System.out.println("\nIngresando datos para la asignatura " + (i + 1) + " de " + numAsignaturas);
            System.out.print("Código de la asignatura: ");
            String codigo = scanner.next();
            System.out.print("Nombre de la asignatura: ");
            String nombre = scanner.next();
            System.out.print("Grupo: ");
            String grupo = scanner.next();
            System.out.print("Aula: ");
            String aula = scanner.next();

            int creditos = obtenerEnteroValidado(scanner, 1, 4);
            int frecuencia = obtenerEnteroValidado(scanner, 1, 3);
            String retiro = obtenerRetiroValidado(scanner);

            if (creditos > 4 || totalCreditos + creditos > 28 || frecuencia > 3) {
                System.out.println("Error en los datos de la asignatura. Verifique los requisitos.");
                return;
            }

            totalCreditos += creditos;
            asignaturas.add(new Asignatura(codigo, nombre, grupo, aula, creditos, frecuencia, retiro));
        }
    }

    private int obtenerEnteroValidado(Scanner scanner, int min, int max) {
        int valor = 0;
        boolean entradaValida = false;

        do {
            try {
                System.out.print("Ingrese un número entero entre " + min + " y " + max + ": ");
                valor = scanner.nextInt();

                if (valor >= min && valor <= max) {
                    entradaValida = true;
                } else {
                    System.out.println("Error: El valor debe estar entre " + min + " y " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número entero válido.");
                scanner.next(); // Limpiar el búfer del scanner
            }
        } while (!entradaValida);

        return valor;
    }

    private String obtenerRetiroValidado(Scanner scanner) {
        String retiro = "";
        boolean entradaValida = false;

        do {
            System.out.print("Retiro (S/N): ");
            retiro = scanner.next().toUpperCase();

            if (retiro.equals("S") || retiro.equals("N")) {
                entradaValida = true;
            } else {
                System.out.println("Error: Ingrese 'S' para sí o 'N' para no.");
            }

            // Limpiar el búfer del scanner después de leer el token
            scanner.nextLine();
        } while (!entradaValida);

        return retiro;
    }

    public void mostrarReporte() {
        System.out.println("\nReporte de matrícula\n");
        System.out.println("Número de recibo: " + numeroRecibo);
        System.out.println("Número de inscripción: " + numeroInscripcion);
        System.out.println("Nombres y apellidos: " + nombresApellidos);
        System.out.println("Número de carnet: " + numeroCarnet);
        System.out.println("Carrera: " + carrera);
        System.out.println("Turno: " + turno);
        System.out.println("Plan de estudio: " + planEstudio);
        System.out.println("Semestre: " + semestre);
        System.out.println("Fecha de matrícula: " + fechaMatricula + "\n");

        System.out.println("Asignaturas\n");
        System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s%-15s%-10s",
                "Código", "Nombre", "Grupo", "Aula", "Créditos", "Frecuencia", "Retiro"));
        for (Asignatura asignatura : asignaturas) {
            System.out.println(asignatura.formatearParaReporte());
        }
    }
}

class Asignatura {
    private String codigo;
    private String nombre;
    private String grupo;
    private String aula;
    private int creditos;
    private int frecuencia;
    private String retiro;

    public Asignatura(String codigo, String nombre, String grupo, String aula, int creditos, int frecuencia,
            String retiro) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.grupo = grupo;
        this.aula = aula;
        this.creditos = creditos;
        this.frecuencia = frecuencia;
        this.retiro = retiro;
    }

    public String formatearParaReporte() {
        return String.format("%-10s%-20s%-10s%-10s%-10d%-15d%-10s",
                codigo, nombre, grupo, aula, creditos, frecuencia, retiro);
    }
}
