import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// Definição dos tipos de Pokémon
public enum PokeType {
    NO_TYPE(0), BUG(1), DARK(2), DRAGON(3), ELECTRIC(4), FAIRY(5), FIGHTING(6), FIRE(7),
    FLYING(8), GHOST(9), GRASS(10), GROUND(11), ICE(12), NORMAL(13), POISON(14), PSYCHIC(15),
    ROCK(16), STEEL(17), WATER(18);

    private final int value;

    PokeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

// Classe para armazenar as habilidades de um Pokémon
class PokeAbilities {
    List<String> list; // Lista dinâmica de strings
    int num; // Quantidade de habilidades

    public PokeAbilities() {
        this.list = new ArrayList<>();
        this.num = 0;
    }

    // Adiciona habilidade à lista
    public void addAbility(String ability) {
        list.add(ability);
        num++;
    }
}

// Classe para armazenar a data de captura
class Date {
    int y; // Ano
    int m; // Mês
    int d; // Dia

    public Date(int y, int m, int d) {
        this.y = y;
        this.m = m;
        this.d = d;
    }
}

// Classe para armazenar as informações de um Pokémon
class Pokemon {
    double weight; // Peso em quilogramas
    double height; // Altura em metros
    String name; // Nome do Pokémon
    String description; // Descrição do Pokémon
    Date captureDate; // Data de captura
    PokeType[] type = new PokeType[2]; // Tipos do Pokémon (no máximo 2)
    int id; // ID do Pokémon
    int captureRate; // Taxa de captura
    byte generation; // Geração
    boolean isLegendary; // Se é ou não um Pokémon lendário

    public Pokemon() {
        // Inicializando o array de tipos
        type[0] = PokeType.NO_TYPE;
        type[1] = PokeType.NO_TYPE;
    }
}

// Função para ler o arquivo CSV e popular a lista de Pokémon
public class PokemonDatabase {

    private static final String DEFAULT_DB = "/tmp/pokemon.csv";

    public static List<Pokemon> loadPokemonData(String filename) {
        List<Pokemon> pokemonList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                
                Pokemon pokemon = new Pokemon();
                
                // Lendo e atribuindo os dados de cada Pokémon
                pokemon.id = Integer.parseInt(tokenizer.nextToken().trim()); // ID
                pokemon.name = tokenizer.nextToken().trim(); // Nome
                pokemon.description = tokenizer.nextToken().trim(); // Descrição
                pokemon.weight = Double.parseDouble(tokenizer.nextToken().trim()); // Peso
                pokemon.height = Double.parseDouble(tokenizer.nextToken().trim()); // Altura
                
                // Lendo a data de captura
                int year = Integer.parseInt(tokenizer.nextToken().trim());
                int month = Integer.parseInt(tokenizer.nextToken().trim());
                int day = Integer.parseInt(tokenizer.nextToken().trim());
                pokemon.captureDate = new Date(year, month, day);
                
                // Lendo tipos
                pokemon.type[0] = PokeType.values()[Integer.parseInt(tokenizer.nextToken().trim())];
                pokemon.type[1] = PokeType.values()[Integer.parseInt(tokenizer.nextToken().trim())];
                
                pokemon.captureRate = Integer.parseInt(tokenizer.nextToken().trim()); // Taxa de captura
                pokemon.generation = Byte.parseByte(tokenizer.nextToken().trim()); // Geração
                pokemon.isLegendary = Boolean.parseBoolean(tokenizer.nextToken().trim()); // Lendário
                
                pokemonList.add(pokemon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return pokemonList;
    }

    public static void main(String[] args) {
        // Verifica se um arquivo foi passado como parâmetro, senão usa o padrão
        String filename = args.length > 0 ? args[0] : DEFAULT_DB;
        
        List<Pokemon> pokemons = loadPokemonData(filename);
        
        // Exibindo os dados dos Pokémons carregados
        for (Pokemon pokemon : pokemons) {
            System.out.println("ID: " + pokemon.id);
            System.out.println("Nome: " + pokemon.name);
            System.out.println("Descrição: " + pokemon.description);
            System.out.println("Peso: " + pokemon.weight);
            System.out.println("Altura: " + pokemon.height);
            System.out.println("Data de Captura: " + pokemon.captureDate.y + "-" + pokemon.captureDate.m + "-" + pokemon.captureDate.d);
            System.out.println("Tipos: " + pokemon.type[0] + ", " + pokemon.type[1]);
            System.out.println("Taxa de Captura: " + pokemon.captureRate);
            System.out.println("Geração: " + pokemon.generation);
            System.out.println("Lendário: " + pokemon.isLegendary);
            System.out.println("------------");
        }
    }
}
