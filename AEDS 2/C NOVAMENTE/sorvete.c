#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int inicio, fim;
} Intervalo;

// Função de comparação para ordenar os intervalos pelo início
int compara(const void *a, const void *b) {
    Intervalo *ia = (Intervalo *)a;
    Intervalo *ib = (Intervalo *)b;

    if (ia->inicio != ib->inicio) {
        return ia->inicio - ib->inicio;
    } else {
        return ia->fim - ib->fim;
    }
}

int main() {
    int P, S, teste = 1;

    while (1) {
        // Leitura de P (comprimento da praia) e S (número de sorveteiros)
        scanf("%d %d", &P, &S);

        if (P == 0 && S == 0) {
            break; // Termina a leitura quando P = 0 e S = 0
        }

        Intervalo intervalos[S];

        // Leitura dos intervalos dos sorveteiros
        for (int i = 0; i < S; i++) {
            scanf("%d %d", &intervalos[i].inicio, &intervalos[i].fim);
        }

        // Ordenar os intervalos pelo ponto inicial
        qsort(intervalos, S, sizeof(Intervalo), compara);

        // Unir intervalos sobrepostos
        int atualInicio = intervalos[0].inicio;
        int atualFim = intervalos[0].fim;

        printf("Teste %d\n", teste);
        for (int i = 1; i < S; i++) {
            if (intervalos[i].inicio <= atualFim) {
                // Estende o intervalo atual
                if (intervalos[i].fim > atualFim) {
                    atualFim = intervalos[i].fim;
                }
            } else {
                // Imprime o intervalo atual
                printf("%d %d\n", atualInicio, atualFim);
                atualInicio = intervalos[i].inicio;
                atualFim = intervalos[i].fim;
            }
        }

        // Imprimir o último intervalo unido
        printf("%d %d\n", atualInicio, atualFim);

        // Linha em branco ao final do teste
        printf("\n");

        teste++;
    }

    return 0;
}