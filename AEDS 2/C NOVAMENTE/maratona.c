#include <stdio.h>

int main(int argc, char const *argv[])
{
    int N;

    scanf("%i", &N);

    int bolas[N];

    for (int i = 0; i < N; i++)
    {
        scanf("%i", &bolas[i]);
    }

    while (N > 1)
    {
        for (int i = 0; i < N - 1; i++)
        {
            if (bolas[i] == bolas[i + 1])
            {
                bolas[i] = 1;
            }
            else
            {
                bolas[i] = -1;
            }
        }
        N--;
    }

    if (bolas[0] == 1)
    {
        printf("preta");
    }
    else
    {
        printf("branca");
    }

    return 0;
}
