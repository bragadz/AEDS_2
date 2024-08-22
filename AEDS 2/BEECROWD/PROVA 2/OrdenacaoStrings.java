import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class OrdenacaoStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numString = Integer.parseInt(sc.nextLine().trim());

        List<String> strings = new ArrayList<>();
        for(int i = 0; i < numString; i++){
            strings.add(sc.nextLine());
        }

        // Ordenar as strings pelo tamanho mantendo a ordem original se o tamanho for igual
        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() != s2.length()) {
                    return s1.length() - s2.length();
                } else {
                    // Manter a ordem original se o tamanho for igual
                    return strings.indexOf(s1) - strings.indexOf(s2);
                }
            }
        });

        // Imprimir as strings ordenadas separadas por um espaço
        for (int i = 0; i < strings.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(strings.get(i));
        }
        System.out.println(); // Nova linha após a saída

        sc.close();
    }
}
