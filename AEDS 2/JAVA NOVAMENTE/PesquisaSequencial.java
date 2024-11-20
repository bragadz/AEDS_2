import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PesquisaSequencial {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Registro> registros = new ArrayList<>();

        // Inserção de registros
        //System.out.println("Insira os registros (name e outras informações), ou digite 'FIM' para finalizar:");
        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("FIM")) {
                break;
            }
            String[] partes = entrada.split(" ");
            String name = partes[0]; // Primeiro elemento é o nome
            String outrasInformacoes = entrada.substring(name.length()).trim(); // O restante da entrada
            registros.add(new Registro(name, outrasInformacoes));
        }

        // Pesquisas
        //System.out.println("Insira os nomes para pesquisa, ou digite 'FIM' para finalizar:");
        List<String> resultados = new ArrayList<>();
        while (true) {
            String nomeParaPesquisar = scanner.nextLine();
            if (nomeParaPesquisar.equalsIgnoreCase("FIM")) {
                break;
            }
            boolean encontrado = pesquisar(registros, nomeParaPesquisar);
            resultados.add(encontrado ? "SIM" : "NAO");
        }

        // Saída dos resultados
        //System.out.println("Resultados das pesquisas:");
        for (String resultado : resultados) {
            System.out.println(resultado);
        }

        // Gravação em arquivo de log
        String nomeArquivo = "matricula_sequencial.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (String resultado : resultados) {
                writer.write(resultado + "\t");
            }
            writer.write("\n"); // Nova linha no final
            writer.write("Número de comparações: " + resultados.size() + "\n");
            writer.write("Tempo de execução: (tempo em milissegundos)\n"); // Aqui você pode adicionar lógica para medir o tempo de execução.
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

        scanner.close();
    }

    private static boolean pesquisar(List<Registro> registros, String nomeParaPesquisar) {
        for (Registro registro : registros) {
            if (registro.getName().equalsIgnoreCase(nomeParaPesquisar)) {
                return true; // Nome encontrado
            }
        }
        return false; // Nome não encontrado
    }
}

class Registro {
    private String name;
    private String outrasInformacoes;

    public Registro(String name, String outrasInformacoes) {
        this.name = name;
        this.outrasInformacoes = outrasInformacoes;
    }

    public String getName() {
        return name;
    }

    public String getOutrasInformacoes() {
        return outrasInformacoes;
    }
}
