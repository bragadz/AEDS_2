import java.io.RandomAccessFile;
import java.util.*;

public class Arquivo {    
    public static void main(String[] args) throws Exception{

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); 
        double numeros;

        RandomAccessFile arquivo = new RandomAccessFile("arq.txt", "rw"); // criando o arquivo 

        for(int i = 0; i < n; i++){
            numeros = sc.nextDouble();
            arquivo.writeDouble(numeros);
        }

        arquivo.close(); // fecha o arquivo
        arquivo = new RandomAccessFile("arq.txt", "r"); //cria o arquivo

        for(int i = n -1; i >=0; i--){

            arquivo.seek(i * 8);
            double num = arquivo.readDouble();

            if(num == (int)num){
                System.out.println((int)num);
            }else{
                System.out.println(num);
            }
        }
        arquivo.close(); // fecha o arquivo
        sc.close(); // Fecha o scanner
    }
}