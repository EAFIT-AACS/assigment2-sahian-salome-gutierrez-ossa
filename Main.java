import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creamos una instancia de la clase que genera cadenas válidas e inválidas
        ALGORITHM_1_LFCO_2025_SSGO generacionStrings = new ALGORITHM_1_LFCO_2025_SSGO();


        System.out.println("\n\u001B[34m★────────────────────────────────────────★");
        System.out.println("   Algorithm 1: String Generation");
        System.out.println("★────────────────────────────────────────★\u001B[0m\n");

        // Generamos 5 cadenas válidas y 5 inválidas
        List<String> StringsValidos = generacionStrings.generarStringsValidos(5);
        List<String> StringsInvalidos = generacionStrings.generarStringsInvalidos(5);

        // Mostramos las cadenas válidas 
        System.out.println("\033[32m✅ Valid strings:\033[0m");
        generacionStrings.imprimirlista(StringsValidos);

        // Mostramos las cadenas inválidas
        System.out.println("\n\033[31m❌ Invalid strings:\033[0m");
        generacionStrings.imprimirlista(StringsInvalidos);

        // Creamos una instancia del autómata de pila (PDA) para analizar las cadenas
        ALGORITHM_2_LFCO_2025_SSGO pda = new ALGORITHM_2_LFCO_2025_SSGO();

    
        System.out.println("\n\u001B[34m★────────────────────────────────────────★");
        System.out.println("   Algorithm 2: PDA String Analysis");
        System.out.println("★────────────────────────────────────────★\u001B[0m\n");

        // Lista para almacenar las cadenas que son aceptadas por el PDA
        List<String> StringsAceptados = new ArrayList<String>();

        // Procesamos las cadenas válidas e inválidas y guardamos solo las aceptadas e imprimimos las cadenas aceptadas o rechazadas por el pda
        StringsAceptados.addAll(imprimirResultados(StringsValidos, pda));
        StringsAceptados.addAll(imprimirResultados(StringsInvalidos, pda));

        
        System.out.println("\n\u001B[34m★────────────────────────────────────────★");
        System.out.println(" Algorithm 3: PDA tree generation");
        System.out.println("★────────────────────────────────────────★\u001B[0m\n");

        // Creamos una instancia del generador de árbol de derivación
        ALGORITHM_3_LFCO_2025_SSGO arbol = new ALGORITHM_3_LFCO_2025_SSGO();

        // Procesamos cada cadena aceptada para generar su árbol de derivación
        for (String s : StringsAceptados) {
            arbol.derivacion(s);
        }
    }

    /** Método auxiliar para procesar una lista de cadenas con el PDA y mostrar
      si son aceptadas o rechazadas. Retorna una lista con las cadenas aceptadas **/
    public static List<String> imprimirResultados(List<String> cadenas, ALGORITHM_2_LFCO_2025_SSGO pda) {
        List<String> aceptados = new ArrayList<String>();
        for (String s : cadenas) {
            // Procesamos la cadena con el PDA
            boolean resultado = pda.procesar(s);

            // Mostramos el resultado en consola y guardamos las aceptadas
            if (resultado) {
                System.out.println("The string '" + s + "' is \033[32maccepted\033[0m by the PDA.");
                aceptados.add(s);
            } else {
                System.out.println("The string '" + s + "' is \033[31mrejected\033[0m by the PDA.");
            }
        }
        return aceptados;
    }
}
