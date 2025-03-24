import java.util.Stack;

public class  ALGORITHM_2_LFCO_2025_SSGO {
    private Stack<Character> stack; // Pila para procesar la cadena

    public  ALGORITHM_2_LFCO_2025_SSGO() {
        this.stack = new Stack<Character>();
    }

    public boolean procesar(String input) {
        stack.clear(); // Limpiar la pila antes de procesar una nueva cadena

        for (char c : input.toCharArray()) {
            if (c == 'a') {
                stack.push('a'); // Apilar a
            } else if (c == 'b') {
                if (stack.isEmpty()) return false; // // Si encontramos un 'b' pero la pila está vacía, no hay un 'a' que le corresponda
                stack.pop(); // Desapilar a al encontrar b
            } else {
                return false; // Caracter inválido
            }
        }
        return stack.isEmpty(); // La pila debe estar vacía al final
    }
}
