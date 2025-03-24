import java.util.*;

public class ALGORITHM_3_LFCO_2025_SSGO {

    // Definimos constantes para los colores en la salida de la consola
    public static final String RESET = "\u001B[0m";
    public static final String CIAN = "\u001B[36m";
    public static final String ROJO = "\u001B[31m"; 

    public void derivacion(String entrada) {
        // Creamos una pila para manejar los símbolos del análisis sintáctico
        Stack<Character> pila = new Stack<Character>();
        // Lista para almacenar los pasos en la derivación y la tabla 
        List<String[]> filasTabla = new ArrayList<String[]>();
        // Bandera que indica si la cadena ha sido aceptada
        boolean cadenaAceptada = false;

        // Inicializamos la cadena de entrada y la forma sentencial inicial
        String cadenaEntrada = entrada;
        String formaSentencial = "S";
        pila.push('S'); // Iniciamos la pila con el símbolo inicial

        // Mostramos la cadena de entrada en la consola
        System.out.println("\n Input string: '" + entrada + "'" + RESET + "\n");

        // Agregamos la configuración inicial a la tabla de análisis
        filasTabla.add(new String[]{"Inicial", formaSentencial, formatoConfiguracion(cadenaEntrada, pila)});

        // Proceso de derivación utilizando la pila
        while (!pila.isEmpty()) {
            char cima = pila.pop(); // Extraemos el símbolo en la cima de la pila

            // Si la cima es 'S', aplicamos la producción S → aX si es posible
            if (cima == 'S') {
                if (!cadenaEntrada.isEmpty() && cadenaEntrada.charAt(0) == 'a') {
                    pila.push('X'); // Apilamos 'X'
                    formaSentencial = formaSentencial.replaceFirst("S", "aX"); // Reemplazamos en la forma sentencial
                    cadenaEntrada = cadenaEntrada.substring(1); // Consumimos el primer carácter
                    filasTabla.add(new String[]{"(1)", formaSentencial, formatoConfiguracion(cadenaEntrada, pila)});
                } else {
                    break; // Si no se puede aplicar la regla, detenemos el análisis
                }
            }
            // Si la cima es 'X'
            else if (cima == 'X') {
                //Si el carácter de entrada es a, aplicamos la producción X → aXB
                if (!cadenaEntrada.isEmpty() && cadenaEntrada.charAt(0) == 'a') {
                    pila.push('B');
                    pila.push('X'); // Primero X y luego B para respetar el orden de la producción
                    formaSentencial = formaSentencial.replaceFirst("X", "aXB");
                    cadenaEntrada = cadenaEntrada.substring(1);// Consumimos el primer carácter
                    filasTabla.add(new String[]{"(2)", formaSentencial, formatoConfiguracion(cadenaEntrada, pila)});
                } 
                //Si el carácter de entrada es b, aplicamos la producción X → b
                else if (!cadenaEntrada.isEmpty() && cadenaEntrada.charAt(0) == 'b') {
                    formaSentencial = formaSentencial.replaceFirst("X", "b");
                    cadenaEntrada = cadenaEntrada.substring(1);// Consumimos el primer carácter
                    filasTabla.add(new String[]{"(3)", formaSentencial, formatoConfiguracion(cadenaEntrada, pila)});
                } 
                else {
                    break;
                }
            }
            // Si la cima es 'B', aplicamos la producción B → b si es posible
            else if (cima == 'B') {
                if (!cadenaEntrada.isEmpty() && cadenaEntrada.charAt(0) == 'b') {
                    formaSentencial = formaSentencial.replaceFirst("B", "b");
                    cadenaEntrada = cadenaEntrada.substring(1);// Consumimos el primer carácter
                    filasTabla.add(new String[]{"(4)", formaSentencial, formatoConfiguracion(cadenaEntrada, pila)});
                } else {
                    break;
                }
            }
        }

        // Verificamos si la cadena ha sido completamente consumida y la pila está vacía
        if (cadenaEntrada.isEmpty() && pila.isEmpty()) {
            String[] ultimaFila = filasTabla.get(filasTabla.size() - 1);
            if (!ultimaFila[2].equals("(q, ε, ε)")) {
                formaSentencial = cadenaEntrada.isEmpty() ? "ε" : "S";
                filasTabla.add(new String[]{"(5)", formaSentencial, "(q, ε, ε)"});
            }
            cadenaAceptada = true; // La cadena ha sido aceptada
        }

        // Imprimimos la tabla con los pasos de la derivación
        imprimirTabla(filasTabla);

        // Mostramos el resultado final en la consola
        if (cadenaAceptada) {
            System.out.println("\n✅ " + CIAN + "Accepted string" + RESET);
        } else {
            System.out.println("\n❌ " + ROJO + "Rejected string" + RESET);
        }
    }

    // Método para formatear la configuración actual del autómata
    private String formatoConfiguracion(String cadena, Stack<Character> pila) {
        StringBuilder pilaTexto = new StringBuilder();
        for (int i = pila.size() - 1; i >= 0; i--) {
            pilaTexto.append(pila.get(i)); // Representamos la pila de arriba hacia abajo (LIFO)
        }
        return "(q, " + (cadena.isEmpty() ? "ε" : cadena) + ", " + (pilaTexto.isEmpty() ? "ε" : pilaTexto.toString()) + ")";
    }

    // Método para imprimir la tabla con los pasos del análisis
    private void imprimirTabla(List<String[]> filas) {
        // Definimos las líneas de separación para el formato de la tabla
        String lineaHorizontal = "╠════════════════╬══════════════════════════════════════════╬══════════════════════════════════╣";
        String lineaSuperior = "╔════════════════╦══════════════════════════════════════════╦══════════════════════════════════╗";
        String lineaInferior = "╚════════════════╩══════════════════════════════════════════╩══════════════════════════════════╝";
        String formato = "║ %-14s ║ %-40s ║ %-32s ║%n";

        // Imprimimos la cabecera de la tabla
        System.out.println(CIAN + lineaSuperior + RESET);
        System.out.printf(CIAN + "║ %-14s ║ %-40s ║ %-32s ║%n" + RESET, "Rule", "Sentential forms", "Configuration of M");
        System.out.println(CIAN + lineaHorizontal + RESET);

        // Iteramos sobre cada fila de la tabla y la imprimimos
        for (String[] fila : filas) {
            System.out.printf(formato, fila[0], fila[1], fila[2]);
        }

        // Imprimimos la línea inferior para cerrar la tabla
        System.out.println(CIAN + lineaInferior + RESET);
    }
}

