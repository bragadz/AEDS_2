#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char name[100];
    char outrasInformacoes[200];
} Registro;

// Função para comparar dois registros (usada na função de pesquisa binária)
int comparar(const void *a, const void *b) {
    return strcmp(((Registro *)a)->name, ((Registro *)b)->name);
}

// Função para realizar a pesquisa binária
int pesquisaBinaria(Registro registros[], int tamanho, const char *nomeParaPesquisar, int *comparacoes) {
    int esquerda = 0;
    int direita = tamanho - 1;

    while (esquerda <= direita) {
        int meio = esquerda + (direita - esquerda) / 2;
        (*comparacoes)++; // Contar a comparação

        int comparacao = strcmp(registros[meio].name, nomeParaPesquisar);
        if (comparacao == 0) {
            return 1; // Nome encontrado
        } else if (comparacao < 0) {
            esquerda = meio + 1;
        } else {
            direita = meio - 1;
        }
    }
    return 0; // Nome não encontrado
}

int main() {
    Registro registros[100];
    int numRegistros = 0;

    // Inserção de registros
    while (1) {
        char entrada[300];
        fgets(entrada, sizeof(entrada), stdin);
        entrada[strcspn(entrada, "\n")] = 0; // Remover nova linha

        if (strcmp(entrada, "FIM") == 0) {
            break;
        }

        sscanf(entrada, "%s %[^\n]", registros[numRegistros].name, registros[numRegistros].outrasInformacoes);
        numRegistros++;
    }

    // Ordena os registros antes da pesquisa
    qsort(registros, numRegistros, sizeof(Registro), comparar);

    // Pesquisas
    while (1) {
        char nomeParaPesquisar[100];
        fgets(nomeParaPesquisar, sizeof(nomeParaPesquisar), stdin);
        nomeParaPesquisar[strcspn(nomeParaPesquisar, "\n")] = 0; // Remover nova linha

        if (strcmp(nomeParaPesquisar, "FIM") == 0) {
            break;
        }

        int comparacoes = 0; // Resetar contagem de comparações para cada pesquisa
        int encontrado = pesquisaBinaria(registros, numRegistros, nomeParaPesquisar, &comparacoes);
        
        // Imprime o resultado na tela
        printf("%s", encontrado ? "SIM\n" : "NAO\n");
    }

    return 0;
}
