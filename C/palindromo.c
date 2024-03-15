#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool verificaPalindromo(const char *str, int inicio, int fim)
{
    if (inicio >= fim)
    {
        return true;
    }

    if (str[inicio] != str[fim])
    {
        return false;
    }

    return verificaPalindromo(str, inicio + 1, fim - 1);
}

bool ehPalindromo(const char *str)
{
    int inicio = 0;
    int fim = strlen(str) - 1;

    return verificaPalindromo(str, inicio, fim);
}

int main()
{
    char linha[653];

    while (1)
    {
        // printf("Digite uma palavra (ou pressione Enter para sair): ");

        if (fgets(linha, sizeof(linha), stdin) == NULL || linha[0] == '\n')
        {
            break; // Sair do loop se pressionar Enter ou EOF
        }

        // Remover o caractere de nova linha, se presente
        linha[strcspn(linha, "\n")] = '\0';

        if (ehPalindromo(linha))
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