#include <stdio.h>
#include <string.h>

void combinador(char *palavra1, char *palavra2, char *resultado)
{
    int i = 0, j = 0, k = 0;

    while (i < strlen(palavra1) && j < strlen(palavra2))
    {
        resultado[k++] = palavra1[i++];
        resultado[k++] = palavra2[j++];
    }

    while (i < strlen(palavra1))
    {
        resultado[k++] = palavra1[i++];
    }

    while (j < strlen(palavra2))
    {
        resultado[k++] = palavra2[j++];
    }

    resultado[k] = '\0';
}

int main()
{
    char palavra1[1000];
    char palavra2[1000];
    char palavraCompleta[200];

    while (1)
    {
        // Lê as duas strings separadas por espaço
        if (scanf("%s %s", palavra1, palavra2) == EOF)
        {
            break; // Sair do loop se EOF for encontrado
        }

        combinador(palavra1, palavra2, palavraCompleta);

        printf("%s\n", palavraCompleta);
    }
    return 0;
}