import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Definição da classe Pokemon
public class Pokemon {
    // Atributos privados
    private int id;
    private int generation;
    private String name;
    private String description;
    private List<String> types;
    private List<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private Date captureDate;

    // Formato de data
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Construtor com parâmetros
    public Pokemon(int id, int generation, String name, String description, List<String> types, List<String> abilities,
            double weight, double height, int captureRate, boolean isLegendary, Date captureDate) {
        this.id = id;
        this.generation = generation;
        this.name = name;
        this.description = description;
        this.types = types;
        this.abilities = abilities;
        this.weight = weight;
        this.height = height;
        this.captureRate = captureRate;
        this.isLegendary = isLegendary;
        this.captureDate = captureDate;
    }

    // Construtor vazio para tratar valores padrão
    public Pokemon() {
        this(0, 0, "", "", new ArrayList<>(), new ArrayList<>(), 0.0, 0.0, 0, false, new Date());
    }

    // Métodos GET e SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public void setLegendary(boolean legendary) {
        isLegendary = legendary;
    }

    public Date getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
    }

    // Método clone
    public Pokemon clone() {
        return new Pokemon(id, generation, name, description, new ArrayList<>(types), new ArrayList<>(abilities),
                weight, height, captureRate, isLegendary, captureDate);
    }

    // Método imprimir
    public void imprimir() {
        // Converte as listas para o formato desejado (com aspas simples e separadores
        // adequados)
        String tiposFormatados = formatarLista(types);
        String habilidadesFormatadas = formatarLista(abilities);

        System.out.printf("[#%d -> %s: %s - %s - %s - %.1fkg - %.1fm - %d%% - %b - %d gen] - %s\n",
                id, name, description, tiposFormatados, habilidadesFormatadas, weight, height,
                captureRate, isLegendary, generation, dateFormat.format(captureDate));
    }

    // Método auxiliar para formatar a lista com aspas simples
    private String formatarLista(List<String> lista) {
        List<String> listaCorrigida = new ArrayList<>();
        for (String item : lista) {
            // Remove aspas duplas ao redor dos itens
            item = item.replace("\"", "").trim();
            listaCorrigida.add(item);
        }
        return "['" + String.join("', '", listaCorrigida) + "']";
    }

    // Método ler para ler uma linha do arquivo CSV
    public static Pokemon ler(String linha) {
        try {
            String[] campos = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Para lidar com vírgulas em campos
                                                                                    // entre aspas

            // Coluna 0: ID
            int id = Integer.parseInt(campos[0].trim());

            // Coluna 1: Geração
            int generation = Integer.parseInt(campos[1].trim());

            // Coluna 2: Nome
            String name = campos[2].trim();

            // Coluna 3: Descrição
            String description = campos[3].trim();

            // Colunas 4 e 5: Tipos
            List<String> types = new ArrayList<>();
            types.add(campos[4].trim()); // type1
            if (!campos[5].trim().isEmpty()) {
                types.add(campos[5].trim()); // type2, se existir
            }

            // Coluna 6: Habilidades
            String abilitiesField = campos[6].trim();
            abilitiesField = abilitiesField.replace("[", "").replace("]", "").replace("'", "").trim();
            List<String> abilities = Arrays.asList(abilitiesField.split(",\\s*"));

            // Coluna 7: Peso (tratar valores vazios)
            double weight = campos[7].trim().isEmpty() ? 0.0 : Double.parseDouble(campos[7].trim());

            // Coluna 8: Altura (tratar valores vazios)
            double height = campos[8].trim().isEmpty() ? 0.0 : Double.parseDouble(campos[8].trim());

            // Coluna 9: Taxa de captura
            int captureRate = Integer.parseInt(campos[9].trim());

            // Coluna 10: É lendário
            boolean isLegendary = Integer.parseInt(campos[10].trim()) == 1;

            // Coluna 11: Data de captura
            Date captureDate = dateFormat.parse(campos[11].trim());

            return new Pokemon(id, generation, name, description, types, abilities, weight, height, captureRate,
                    isLegendary, captureDate);
        } catch (IndexOutOfBoundsException | NumberFormatException | ParseException e) {
            e.printStackTrace();
            return new Pokemon(); // Retorna um Pokémon padrão em caso de erro
        }
    }

    // Função para processar o arquivo e um ID de Pokémon
    public static void processarArquivo(String arquivoCsv, int idALer) {
        Map<Integer, Pokemon> pokemons = new HashMap<>();

        // Leitura do arquivo CSV
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCsv), "UTF-8"))) {
            String linha;
            boolean isFirstLine = true; // Variável para pular a primeira linha (cabeçalho)
            while ((linha = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Pula a primeira linha
                    continue; // Vai para a próxima linha
                }
                Pokemon pokemon = Pokemon.ler(linha);
                pokemons.put(pokemon.getId(), pokemon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Processar ID digitado
        if (pokemons.containsKey(idALer)) {
            pokemons.get(idALer).imprimir();
        } else {
            System.out.println("Pokémon com ID " + idALer + " não encontrado.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Verifica o diretório de execução
        // System.out.println("Diretório atual: " + new File(".").getAbsolutePath());

        // Lendo IDs e processando imediatamente após entrada
        while (true) {
            // System.out.println("Digite um ID de Pokémon ou 'FIM' para encerrar:");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            try {
                int id = Integer.parseInt(input);
                processarArquivo("/tmp/pokemon.csv", id); // Verifique se o arquivo pokemon.csv está no diretório
                                                          // correto
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número válido ou 'FIM' para encerrar.");
            }
        }
        scanner.close();
    }
}
