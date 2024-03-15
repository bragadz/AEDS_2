import java.util.Scanner;

public class Pokemon {

    public static int pokemon(String[] nomePokemons, int N) {

        Scanner sc = new Scanner(System.in);

        String novoPokemon;

        int pokemonsQnt = 0;

        //System.out.println("Quantos pokemons voce jah capturou: ");

        N = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < N; i++) {
            novoPokemon = sc.nextLine();

            boolean pokemonRepetido = false;
            for (int j = 0; j < i; j++) {
                if (nomePokemons[j] != null && nomePokemons[j].equals(novoPokemon)) {
                    pokemonRepetido = true;
                }
            }

            if (!pokemonRepetido) {
                nomePokemons[i] = novoPokemon;
                pokemonsQnt++;
            }
        }
        sc.close();
        return pokemonsQnt;
    }

    public static void main(String[] args) {

        int N = 0;

        String[] nomePokemons = new String[151];

        int pokemonsCapturados = pokemon(nomePokemons, N);

        System.out.println("Falta(m) " + (151 - pokemonsCapturados) + " pomekon(s).");

    }
}