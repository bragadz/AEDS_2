package BEECROWD;

import java.util.Scanner;

public class Main {

    public static String combinarString(String str1, String str2){
        StringBuilder combinadas = new StringBuilder();

        int tamTotalString = Math.max(str1.length(), str2.length());

        for(int i = 0; i < tamTotalString; i++){
            if(i < str1.length()){
                combinadas.append(str1.charAt(i));
            }
            if(i < str2.length()){
                combinadas.append(str2.charAt(i));
            }
        }
        return combinadas.toString();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //System.out.println("quantos casos deseja testar ?\n");
        int N = sc.nextInt();
        sc.nextLine();

        //vou digitar o texto
        for(int i = 0; i < N; i++){
            String str1 = sc.next();
            String str2 = sc.next();

            String combinadas = combinarString(str1, str2);
            System.out.println(combinadas);
        }
        sc.close();
    }
}