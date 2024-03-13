#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>


char gerarLetraAleatoria() {
    return 'a' + (rand() % 26);
}

void substituirLetraRecursivo(char* palavra, char letraAntiga, char letraNova, int index) {
    if (palavra[index] == '\0') {
        return;
    }

    if (palavra[index] == letraAntiga) {
        palavra[index] = letraNova; 
    }

    substituirLetraRecursivo(palavra, letraAntiga, letraNova, index + 1); 
}


void substituirLetrasRecursivo(char* palavra) {
    char primeiraLetra = gerarLetraAleatoria();
    char segundaLetra = gerarLetraAleatoria();

    substituirLetraRecursivo(palavra, primeiraLetra, segundaLetra, 0); 
}

int main() {
    char palavra[100];

    srand(4);

    while (1) {
        fgets(palavra, sizeof(palavra), stdin);
        palavra[strcspn(palavra, "\n")] = '\0'; 

        if (strcmp(palavra, "FIM") == 0) 
            break;

        substituirLetrasRecursivo(palavra);
        printf("%s\n", palavra); 
    }

    return 0;
}