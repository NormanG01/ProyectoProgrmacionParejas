import java.util.Scanner;

public class ActasDeNotas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("*** Ingrese los Datos Generales del Curso ***");
            System.out.print("Nombre del curso: ");
            String nombreCurso = scanner.nextLine();
            System.out.print("Período lectivo: ");
            String periodoLectivo = scanner.nextLine();
            System.out.print("Carrera: ");
            String carrera = scanner.nextLine();
            System.out.print("Grupo: ");
            String grupo = scanner.nextLine();
            System.out.print("Modalidad: ");
            String modalidad = scanner.nextLine();
            System.out.print("Código del curso: ");
            String codigoCurso = scanner.nextLine();
            System.out.print("Código de asignatura: ");
            String codigoAsignatura = scanner.nextLine();
            System.out.print("Código de programa de asignatura: ");
            String codigoProgramaAsignatura = scanner.nextLine();

            int cantidadEstudiantes = 0;
            while (cantidadEstudiantes <= 0) {
                System.out.print("Cantidad de estudiantes: ");
                try {
                    cantidadEstudiantes = Integer.parseInt(scanner.nextLine());
                    if (cantidadEstudiantes <= 0) {
                        System.out.println("La cantidad de estudiantes debe ser mayor que 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ingrese un valor numérico válido para la cantidad de estudiantes.");
                }
            }

            int[] carnet = new int[cantidadEstudiantes];
            String[] nombres = new String[cantidadEstudiantes];
            double[] primerParcial = new double[cantidadEstudiantes];
            double[] segundoParcial = new double[cantidadEstudiantes];
            double[] sistematicos = new double[cantidadEstudiantes];
            double[] notaFinal = new double[cantidadEstudiantes];
            boolean[] desercion = new boolean[cantidadEstudiantes];

            for (int i = 0; i < cantidadEstudiantes; i++) {
                System.out.println("\n*** Datos del Estudiante " + (i + 1) + " ***");
                carnet[i] = ingresarEnteroValidado(scanner, "Número de carnet: ");
                nombres[i] = ingresarTextoValidado(scanner, "Nombres y Apellidos: ");

                System.out.print("¿El estudiante realizará proyecto de curso? (Sí/No): ");
                String respuestaProyecto = scanner.nextLine();

                if (respuestaProyecto.equalsIgnoreCase("Sí")) {
                    segundoParcial[i] = ingresarDoubleValidado(scanner, "Nota del Proyecto (máximo 35.00): ", 0.0,
                            35.0);
                } else {
                    primerParcial[i] = ingresarDoubleValidado(scanner, "Primer parcial (máximo 35.00): ", 0.0, 35.0);
                    segundoParcial[i] = ingresarDoubleValidado(scanner, "Segundo parcial (máximo 35.00): ", 0.0, 35.0);
                }

                sistematicos[i] = ingresarDoubleValidado(scanner, "Sistemáticos (máximo 30.00): ", 0.0, 30.0);
                notaFinal[i] = primerParcial[i] + segundoParcial[i] + sistematicos[i];

                if (notaFinal[i] < 60.00) {
                    double notaPrimerConvocatoria = ingresarDoubleValidado(scanner,
                            "Examen de primera convocatoria (máximo 70.00): ", 0.0, 70.0);
                    notaFinal[i] += notaPrimerConvocatoria;

                    if (notaFinal[i] < 60.00) {
                        double notaSegundaConvocatoria = ingresarDoubleValidado(scanner,
                                "Examen de segunda convocatoria (máximo 100.00): ", 0.0, 100.0);
                        notaFinal[i] += notaSegundaConvocatoria;
                    }
                } else {
                    desercion[i] = false;
                }

                if (notaFinal[i] < 60.00) {
                    desercion[i] = ingresarBooleanoValidado(scanner, "¿El estudiante desertó el curso? (Sí/No): ");
                }
            }

            // Ordenar los estudiantes alfabéticamente por apellidos
            String[] apellidos = new String[cantidadEstudiantes];
            for (int i = 0; i < cantidadEstudiantes; i++) {
                String[] nombrePartes = nombres[i].split(" ");
                apellidos[i] = nombrePartes[nombrePartes.length - 1];
            }

            for (int i = 0; i < cantidadEstudiantes - 1; i++) {
                for (int j = i + 1; j < cantidadEstudiantes; j++) {
                    if (apellidos[i].compareTo(apellidos[j]) > 0) {
                        // Intercambiar apellidos
                        String tempApellido = apellidos[i];
                        apellidos[i] = apellidos[j];
                        apellidos[j] = tempApellido;

                        // Intercambiar carnet
                        int tempCarnet = carnet[i];
                        carnet[i] = carnet[j];
                        carnet[j] = tempCarnet;

                        // Intercambiar nombres
                        String tempNombre = nombres[i];
                        nombres[i] = nombres[j];
                        nombres[j] = tempNombre;

                        // Intercambiar primer parcial
                        double tempPrimerParcial = primerParcial[i];
                        primerParcial[i] = primerParcial[j];
                        primerParcial[j] = tempPrimerParcial;

                        // Intercambiar segundo parcial
                        double tempSegundoParcial = segundoParcial[i];
                        segundoParcial[i] = segundoParcial[j];
                        segundoParcial[j] = tempSegundoParcial;

                        // Intercambiar sistematicos
                        double tempSistematicos = sistematicos[i];
                        sistematicos[i] = sistematicos[j];
                        sistematicos[j] = tempSistematicos;

                        // Intercambiar nota final
                        double tempNotaFinal = notaFinal[i];
                        notaFinal[i] = notaFinal[j];
                        notaFinal[j] = tempNotaFinal;

                        // Intercambiar desercion
                        boolean tempDesercion = desercion[i];
                        desercion[i] = desercion[j];
                        desercion[j] = tempDesercion;
                    }
                }
            }

            // Generar el acta de notas
            generarActaDeNotas(nombreCurso, periodoLectivo, carrera, grupo, modalidad, codigoCurso, codigoAsignatura,
                    codigoProgramaAsignatura, cantidadEstudiantes, carnet, nombres, primerParcial, segundoParcial,
                    sistematicos, notaFinal, desercion);

            // Generar el reporte estadístico
            generarReporteEstadistico(cantidadEstudiantes, notaFinal);

            // Preguntar al usuario si desea continuar
            System.out.print("\n¿Desea utilizar la aplicación de nuevo? (Sí/No): ");
            String respuesta = scanner.nextLine();
            continuar = respuesta.equalsIgnoreCase("Sí");
        }
    }

    public static void generarActaDeNotas(String nombreCurso, String periodoLectivo, String carrera, String grupo,
            String modalidad, String codigoCurso, String codigoAsignatura, String codigoProgramaAsignatura,
            int cantidadEstudiantes, int[] carnet, String[] nombres, double[] primerParcial, double[] segundoParcial,
            double[] sistematicos, double[] notaFinal, boolean[] desercion) {
        System.out.println("\n*** Acta de Notas del Curso ***");
        System.out.println("Nombre del curso: " + nombreCurso);
        System.out.println("Período lectivo: " + periodoLectivo);
        System.out.println("Carrera: " + carrera);
        System.out.println("Grupo: " + grupo);
        System.out.println("Modalidad: " + modalidad);
        System.out.println("Código del curso: " + codigoCurso);
        System.out.println("Código de asignatura: " + codigoAsignatura);
        System.out.println("Código de programa de asignatura: " + codigoProgramaAsignatura);
        System.out.println("\nNúmero de estudiantes: " + cantidadEstudiantes);

        System.out.println(
                "\n| Carnet | Nombres y Apellidos | Primer Parcial | Segundo Parcial | Sistemáticos | Nota Final | Deserción |");
        for (int i = 0; i < cantidadEstudiantes; i++) {
            String resultadoDesercion = desercion[i] ? "Sí" : "No";
            System.out.printf("| %6d | %-20s | %13.2f | %14.2f | %11.2f | %9.2f | %-8s |\n", carnet[i], nombres[i],
                    primerParcial[i], segundoParcial[i], sistematicos[i], notaFinal[i], resultadoDesercion);
        }
    }

    public static void generarReporteEstadistico(int cantidadEstudiantes, double[] notaFinal) {
        int matriculaInicial = cantidadEstudiantes;
        int matriculaEfectiva = matriculaInicial;
        int deserciones = 0;
        int aprobados = 0;
        int reprobados = 0;
        double notaMinima = notaFinal[0];
        double notaMaxima = notaFinal[0];
        double sumaNotas = notaFinal[0];

        if (notaFinal[0] >= 60) {
            aprobados++;
        } else {
            reprobados++;
        }

        for (int i = 1; i < cantidadEstudiantes; i++) {
            if (notaFinal[i] < 60) {
                deserciones++;
            } else {
                aprobados++;
            }

            if (notaFinal[i] > notaMaxima) {
                notaMaxima = notaFinal[i];
            }

            if (notaFinal[i] < notaMinima) {
                notaMinima = notaFinal[i];
            }

            sumaNotas += notaFinal[i];
        }

        double promedioNotas = sumaNotas / cantidadEstudiantes;
        double porcentajeAprobados = (double) aprobados / cantidadEstudiantes * 100;
        double porcentajeReprobados = (double) reprobados / cantidadEstudiantes * 100;
        int matriculaEfectivaDeserciones = matriculaEfectiva - deserciones;

        System.out.println("\n*** Reporte Estadístico del Curso ***");
        System.out.println("Matrícula inicial: " + matriculaInicial);
        System.out.println("Matrícula efectiva (Matrícula inicial - Deserciones): " + matriculaEfectivaDeserciones);
        System.out.println("Número de deserciones: " + deserciones);
        System.out.println("Cantidad de aprobados: " + aprobados);
        System.out.println("% de aprobados: " + porcentajeAprobados + "%");
        System.out.println("Cantidad de reprobados: " + reprobados);
        System.out.println("% de reprobados: " + porcentajeReprobados + "%");
        System.out.println("Nota mínima: " + notaMinima);
        System.out.println("Nota máxima: " + notaMaxima);
        System.out.println("Promedio de notas: " + promedioNotas);
    }

    // Métodos de validación de entrada de datos
    public static int ingresarEnteroValidado(Scanner scanner, String mensaje) {
        int valor = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(scanner.nextLine());
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un valor entero válido.");
            }
        }

        return valor;
    }

    public static double ingresarDoubleValidado(Scanner scanner, String mensaje, double min, double max) {
        double valor = 0.0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print(mensaje);
            try {
                valor = Double.parseDouble(scanner.nextLine());
                if (valor >= min && valor <= max) {
                    entradaValida = true;
                } else {
                    System.out.println("Ingrese un valor entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un valor numérico válido.");
            }
        }

        return valor;
    }

    public static String ingresarTextoValidado(Scanner scanner, String mensaje) {
        String valor = "";
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print(mensaje);
            valor = scanner.nextLine().trim();

            if (!valor.isEmpty()) {
                entradaValida = true;
            } else {
                System.out.println("Ingrese un valor válido.");
            }
        }

        return valor;
    }

    public static boolean ingresarBooleanoValidado(Scanner scanner, String mensaje) {
        boolean valor = false;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print(mensaje);
            String respuesta = scanner.nextLine();

            if (respuesta.equalsIgnoreCase("Sí") || respuesta.equalsIgnoreCase("No")) {
                valor = respuesta.equalsIgnoreCase("Sí");
                entradaValida = true;
            } else {
                System.out.println("Ingrese 'Sí' o 'No'.");
            }
        }

        return valor;
    }
}
