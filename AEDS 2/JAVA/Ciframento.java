import java.util.Scanner;

public class Ciframento {

    public static String criptografar(String palavra) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < palavra.length(); i++) {
            char letra = palavra.charAt(i);

            if (Character.isLetter(letra)){
            char base = (Character.isUpperCase(letra)) ? 'A' : 'a';
            letra = (char) (((letra - base + 3) % 127) + base);

            resultado.append(letra);
            } else if(letra == 65533){
                resultado.append((char) 65533);
            } else {
                resultado.append((char) (letra + 3));
            }
        }
        return resultado.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // System.out.println("Digite o texto: ");

        String palavra = sc.nextLine();

        while (!palavra.equals("FIM")) {
            System.out.println(criptografar(palavra));
            palavra = sc.nextLine();
        }

        sc.close();
    }
}