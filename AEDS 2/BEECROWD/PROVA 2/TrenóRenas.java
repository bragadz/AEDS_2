import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Classe que representa uma rena
class Rena {
    String nome;
    int peso;
    int idade;
    float altura;

    public Rena(String nome, int peso, int idade, float altura) {
        this.nome = nome;
        this.peso = peso;
        this.idade = idade;
        this.altura = altura;
    }

    // Getters para acessar as características
    public String getNome() {
        return nome;
    }

    public int getPeso() {
        return peso;
    }

    public int getIdade() {
        return idade;
    }

    public float getAltura() {
        return altura;
    }
    
    // Comparador para ordenar as renas
    public static Comparator<Rena> comparador = new Comparator<Rena>() {
        @Override
        public int compare(Rena r1, Rena r2) {
            if (r1.peso != r2.peso) {
                return Integer.compare(r2.peso, r1.peso); // decrescente
            }
            if (r1.idade != r2.idade) {
                return Integer.compare(r1.idade, r2.idade); // crescente
            }
            if (r1.altura != r2.altura) {
                return Float.compare(r1.altura, r2.altura); // crescente
            }
            return r1.nome.compareTo(r2.nome); // crescente
        }
    };
}

// Classe principal que executa o programa
public class TrenóRenas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int T = scanner.nextInt(); // número de casos de teste
        for (int i = 1; i <= T; i++) {
            int N = scanner.nextInt(); // total de renas
            int M = scanner.nextInt(); // renas que irão puxar o trenó
            
            List<Rena> renas = new ArrayList<>();
            
            // Lendo as informações das renas
            for (int j = 0; j < N; j++) {
                String nome = scanner.next();
                int peso = scanner.nextInt();
                int idade = scanner.nextInt();
                float altura = scanner.nextFloat();
                
                renas.add(new Rena(nome, peso, idade, altura));
            }
            
            // Ordenando as renas
            Collections.sort(renas, Rena.comparador);
            
            // Exibindo o resultado
            System.out.println("CENARIO " + i);
            for (int j = 0; j < M; j++) {
                System.out.println((j + 1) + " " + renas.get(j).getNome());
            }
        }
        
        scanner.close();
    }
}