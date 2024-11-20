#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

// Estrutura para armazenar os dados de um Pokémon
typedef struct {
    int id;
    int generation;
    char name[100];
    char description[100];
    char types[2][20];  // Até dois tipos (type1 e type2)
    char abilities[3][30]; // Até três habilidades
    double weight;
    double height;
    int captureRate;
    bool isLegendary;
    char captureDate[11];  // Data no formato dd/mm/yyyy
} Pokemon;

// Função para remover caracteres indesejados de uma string
void cleanString(char *str) {
    char *src = str, *dst = str;
    while (*src) {
        // Ignora aspas simples e duplas, colchetes e espaços desnecessários
        if (*src == '"' || *src == '\'' || *src == '[' || *src == ']' || *src == ' ' || *src == '\t') {
            src++;
            continue;
        }
        // Remove caracteres não imprimíveis
        if (*src < 32 || *src > 126) {
            src++;
            continue;
        }
        *dst++ = *src++;
    }
    *dst = '\0';
}

// Função para ler os dados de um Pokémon de uma linha do CSV
Pokemon lerPokemon(char *linha) {
    Pokemon p;
    char abilities[100];
    char type1[20], type2[20];  // Strings temporárias para os tipos

    // Limpa a estrutura antes de usar
    memset(&p, 0, sizeof(Pokemon));

    // Lê os campos da linha
    sscanf(linha, "%d,%d,%[^,],%[^,],%[^,],%[^,],\"%[^\"]\",%lf,%lf,%d,%d,%[^\n]",
           &p.id, &p.generation, p.name, p.description, type1, type2,
           abilities, &p.weight, &p.height, &p.captureRate, (int*)&p.isLegendary, p.captureDate);

    // Limpa as strings de tipos e habilidades
    cleanString(type1);
    cleanString(type2);
    cleanString(abilities);

    // Armazena os tipos no Pokémon
    strncpy(p.types[0], type1, sizeof(p.types[0]) - 1);
    strncpy(p.types[1], type2, sizeof(p.types[1]) - 1);

    // Separa as habilidades
    char *token = strtok(abilities, ",");
    int i = 0;
    while (token != NULL && i < 3) {
        cleanString(token);  // Remove qualquer caractere extra
        strncpy(p.abilities[i], token, sizeof(p.abilities[i]) - 1);
        token = strtok(NULL, ",");
        i++;
    }

    return p;
}

// Função para formatar e imprimir os dados de um Pokémon
void imprimirPokemon(Pokemon *p) {
    // Formatação correta para tipos
    printf("[#%d -> %s: %s - ['%s'%s] - ['%s'%s%s] - %.1fkg - %.1fm - %d%% - %s - %d gen] - %s\n",
           p->id, p->name, p->description, 
           p->types[0], strlen(p->types[1]) > 0 ? "', '" : "", 
           p->abilities[0], 
           strlen(p->abilities[1]) > 0 ? "', '" : "", 
           strlen(p->abilities[2]) > 0 ? "', '" : "", 
           p->weight, p->height, p->captureRate, 
           p->isLegendary ? "true" : "false", p->generation, p->captureDate);
}

// Função para processar o arquivo CSV e procurar um Pokémon por ID
void processarArquivo(char *arquivoCsv, int idALer) {
    FILE *arquivo = fopen(arquivoCsv, "r");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo %s.\n", arquivoCsv);
        return;
    }

    char linha[1024];
    bool isFirstLine = true;
    while (fgets(linha, sizeof(linha), arquivo)) {
        if (isFirstLine) {
            isFirstLine = false; // Pular o cabeçalho
            continue;
        }
        Pokemon p = lerPokemon(linha);
        if (p.id == idALer) {
            imprimirPokemon(&p);
            fclose(arquivo);
            return;
        }
    }

    printf("Pokémon com ID %d não encontrado.\n", idALer);
    fclose(arquivo);
}

int main() {
    char input[10];
    int id;

    while (true) {
        //printf("Digite um ID de Pokémon ou 'FIM' para encerrar:\n");
        fgets(input, sizeof(input), stdin);
        input[strcspn(input, "\n")] = '\0';  // Remover o \n da entrada

        if (strcasecmp(input, "FIM") == 0) {
            break;
        }

        if (sscanf(input, "%d", &id) != 1) {
            //printf("Entrada inválida. Digite um número válido ou 'FIM' para encerrar.\n");
            continue;
        }

        // Substitua pelo caminho correto para o arquivo CSV
        processarArquivo("/tmp/pokemon.csv", id);
    }

    return 0;
}