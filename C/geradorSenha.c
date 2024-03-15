#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// Função para substituir caracteres recursivamente
char *subChar(char *str, int i) {
  if (i >= strlen(str)) {
    return str;
  }

  srand(4); // Define a semente para gerar números pseudoaleatórios

  char charUm = 'a' + (rand() % 26);
  char charDois = 'a' + (rand() % 26);
  // Cria uma cópia da string para modificação
  char *modifiedStr = strdup(str);

  if (str[i] == charUm) {
    modifiedStr[i] = charDois;
  }

  // Chama recursivamente a função com o próximo índice
  return subChar(modifiedStr, i + 1);
}

int main() {
  char str[1000];

  // Leitura da entrada
  fgets(str, sizeof(str), stdin);
  str[strcspn(str, "\n")] = '\0'; // Remove a quebra de linha

  while (strcmp(str, "FIM") != 0) {
    // Imprime o resultado da função subChar
    printf("%s\n", subChar(str, 0));

    // Lê a próxima entrada
    fgets(str, sizeof(str), stdin);
    str[strcspn(str, "\n")] = '\0'; // Remove a quebra de linha
  }

  return 0;
}