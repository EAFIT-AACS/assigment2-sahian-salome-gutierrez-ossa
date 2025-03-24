import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ALGORITHM_1_LFCO_2025_SSGO {

    /** Genera una lista de n cadenas válidas.
     Las cadenas válidas consisten en una cantidad igual de a seguidas por la misma cantidad de b . **/
    public List<String> generarStringsValidos(int n) {
        List<String> StringsValidos = new ArrayList<String>();
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            // Generamos una longitud aleatoria entre 0 y 7
            int length = rand.nextInt(8); 
            StringBuilder sb = new StringBuilder();

            // Añadimos 'a' 'length' veces
            for (int j = 0; j < length; j++) sb.append("a");

            // Añadimos 'b' 'length' veces
            for (int j = 0; j < length; j++) sb.append("b");

            // Añadimos la cadena generada a la lista
            StringsValidos.add(sb.toString());
        }
        return StringsValidos;
    }

    /** Genera una lista de n cadenas inválidas.
      Las cadenas inválidas son aquellas que no cumplen la regla: mismo número de a seguidos por b.**/
    public List<String> generarStringsInvalidos(int n) {
        List<String> StringsInvalidos = new ArrayList<String>();
        Random rand = new Random();

        // Continuamos hasta generar 'n' cadenas inválidas
        while (StringsInvalidos.size() < n) {
            int length = rand.nextInt(5) + 1; // Longitud base (al menos 1)

            StringBuilder sb = new StringBuilder();

            // Generamos aleatoriamente 'a' o 'b', longitud total = 2 * length
            for (int j = 0; j < length * 2; j++) {
                sb.append(rand.nextBoolean() ? 'a' : 'b');
            }

            String s = sb.toString();

            // Solo añadimos la cadena si es invalida
            if (!VerificarStringValido(s)) StringsInvalidos.add(s);
        }
        return StringsInvalidos;
    }

    // Verifica si una cadena es válida.
    private boolean VerificarStringValido(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') count++;     // Incrementamos por cada a
            else if (c == 'b') count--; // Decrementamos por cada b

            // Si en algún punto hay más b que a, ya es inválida
            if (count < 0) return false;
        }
        // Al final, debe haber mismo número de a y b
        return count == 0;
    }

    /* Imprime la lista de cadenas */
    public void imprimirlista(List<String> lista) {
        for (String str : lista) {
            System.out.println("   '" + str + "'");
        }
    }

}

