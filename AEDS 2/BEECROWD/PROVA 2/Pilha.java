import java.util.Stack;

public class Pilha {

    public static String inverter(String str) {
        Stack<Character> pilha = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char caractere = str.charAt(i);
            pilha.push(caractere);
        }

        StringBuilder strInvertida = new StringBuilder();

        while (!pilha.isEmpty()) {
            strInvertida.append(pilha.pop());
        }
        return strInvertida.toString();
    }

    public static void main(String[] args) {
        String str = "Olá";
        String invertida = inverter(str);
        System.out.println(invertida);
    }
}