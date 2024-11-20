#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdbool.h>

bool verficaPalindromo(const char *str)
{
    int esquerda = 0;
    int direita = strlen(str) - 1;

    while (esquerda < direita)
    {
        if (str[esquerda] != str[direita]) {
            return false; // NÃO é um palíndromo
        }
        esquerda++;
        direita--;
    }

    return true;
}

int main()
{
    char palavra[100];

    while (fgets(palavra, sizeof(palavra), stdin))
    {
        // Remover o \n no final da string lida
        palavra[strcspn(palavra, "\n")] = '\0';

        if (verficaPalindromo(palavra))
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
    }

    return 0;
}
