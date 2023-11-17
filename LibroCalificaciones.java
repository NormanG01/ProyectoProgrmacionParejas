import java.util.Scanner;

public class LibroCalificaciones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario la cantidad de estudiantes
        System.out.print("Ingrese la cantidad de estudiantes: ");
        int cantidadEstudiantes = scanner.nextInt();

        // Inicializar un arreglo para almacenar las notas
        int[] notas = new int[cantidadEstudiantes];

        // Solicitar al usuario ingresar las notas de cada estudiante
        for (int i = 0; i < cantidadEstudiantes; i++) {
            System.out.print("Ingrese la nota del estudiante " + (i + 1) + ": ");
            notas[i] = scanner.nextInt();
        }

        // Inicializar contadores para cada categoría
        int[] contadorCategorias = new int[5];

        // Contar las notas en cada categoría
        for (int nota : notas) {
            if (nota >= 0 && nota <= 59) {
                contadorCategorias[0]++;
            } else if (nota >= 60 && nota <= 69) {
                contadorCategorias[1]++;
            } else if (nota >= 70 && nota <= 79) {
                contadorCategorias[2]++;
            } else if (nota >= 80 && nota <= 89) {
                contadorCategorias[3]++;
            } else if (nota >= 90 && nota <= 100) {
                contadorCategorias[4]++;
            }
        }

        // Mostrar el gráfico de barras
        String[] categorias = { "0-59 (Reprobado)", "60-69 (Regular)", "70-79 (Bueno)", "80-89 (Muy bueno)",
                "90-100 (Excelente)" };

        System.out.println("Distribución de calificaciones:");

        for (int i = 0; i < 5; i++) {
            System.out.print(categorias[i] + ": ");

            // Imprimir asteriscos (*) para representar la cantidad de notas en la categoría
            for (int j = 0; j < contadorCategorias[i]; j++) {
                System.out.print("*");
            }
            System.out.println(" (" + contadorCategorias[i] + ")");
        }

        scanner.close();
    }
}
// NormanG01