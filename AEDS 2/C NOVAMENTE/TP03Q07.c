#define _POSIX_C_SOURCE 200809L

#include <ctype.h>
#include <errno.h>
#include <math.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// De onde ler o CSV se não receber nenhum parâmetro na linha de comando.
#define DEFAULT_DB "/tmp/pokemon.csv"

/// Definições dos tipos de dados. ////////////////////////////////////////////

// Tipos possíveis de Pokémon.
enum PokeType
{
    NO_TYPE = 0,
    BUG,
    DARK,
    DRAGON,
    ELECTRIC,
    FAIRY,
    FIGHTING,
    FIRE,
    FLYING,
    GHOST,
    GRASS,
    GROUND,
    ICE,
    NORMAL,
    POISON,
    PSYCHIC,
    ROCK,
    STEEL,
    WATER
};

// Definição do tipo de inteiro que armazena o tipo do Pokémon. Deve ter bits
// suficientes para todos os tipos.
typedef uint8_t PokeType;

// Lista de habilidades de um Pokémon.
typedef struct
{
    char **list; // Lista dinâmica de strings dinâmicas.
    uint8_t num; // Quantidade de habilidades.
} PokeAbilities;

// Data.
typedef struct
{
    uint16_t y; // Ano.
    uint8_t m;  // Mês.
    uint8_t d;  // Dia.
} Date;

// O Pokémon em si.
typedef struct
{
    double weight;      // Peso em quilogramas.
    double height;      // Altura em metros.
    char *name;         // String dinâmica para o nome.
    char *description;  // String dinâmica para a descrição.
    Date capture_date;  // Data de captura.
    PokeType type[2];   // Tipos do Pokémon.
    uint16_t id;        // Chave: inteiro não-negativo de 16 bits.
    uint16_t capture_rate; // Determinante...
} Pokemon;

// Fila com alocação flexível.
typedef struct
{
    Pokemon *data;    // Ponteiro para os dados da fila.
    size_t size;      // Tamanho atual da fila.
    size_t capacity;  // Capacidade da fila (para alocação dinâmica).
} Queue;

// Função para inicializar a fila.
void init_queue(Queue *q, size_t initial_capacity)
{
    q->data = malloc(initial_capacity * sizeof(Pokemon));
    if (!q->data)
    {
        fprintf(stderr, "Falha na alocação de memória para a fila.\n");
        exit(1);
    }
    q->size = 0;
    q->capacity = initial_capacity;
}

// Função para enfileirar um Pokémon na fila.
void enqueue(Queue *q, Pokemon pokemon)
{
    if (q->size == q->capacity)
    {
        // Redimensionar a fila se necessário.
        q->capacity *= 2;
        q->data = realloc(q->data, q->capacity * sizeof(Pokemon));
        if (!q->data)
        {
            fprintf(stderr, "Falha ao realocar memória para a fila.\n");
            exit(1);
        }
    }
    q->data[q->size++] = pokemon;
}

// Função para desenfileirar um Pokémon da fila.
Pokemon dequeue(Queue *q)
{
    if (q->size == 0)
    {
        fprintf(stderr, "A fila está vazia.\n");
        exit(1);
    }
    Pokemon pokemon = q->data[0];
    // Deslocar os elementos da fila para frente.
    memmove(q->data, q->data + 1, (q->size - 1) * sizeof(Pokemon));
    q->size--;
    return pokemon;
}

// Função para liberar a memória alocada pela fila.
void free_queue(Queue *q)
{
    free(q->data);
    q->data = NULL;
    q->size = q->capacity = 0;
}

// Função para imprimir as informações de um Pokémon.
void print_pokemon(Pokemon *poke)
{
    printf("ID: %d\n", poke->id);
    printf("Nome: %s\n", poke->name);
    printf("Descrição: %s\n", poke->description);
    printf("Peso: %.2f kg\n", poke->weight);
    printf("Altura: %.2f m\n", poke->height);
    printf("Data de captura: %d-%d-%d\n", poke->capture_date.y, poke->capture_date.m, poke->capture_date.d);
    printf("Taxa de captura: %d\n", poke->capture_rate);
    printf("Tipo(s): %d, %d\n\n", poke->type[0], poke->type[1]);
}

// Função para ler um arquivo CSV e retornar um Pokémon.
Pokemon read_pokemon(FILE *file)
{
    Pokemon poke;
    char buffer[256];
    char *token;

    // Ler ID
    fscanf(file, "%d,", &poke.id);
    
    // Ler nome
    fscanf(file, "%255[^,],", buffer);
    poke.name = strdup(buffer);

    // Ler descrição
    fscanf(file, "%255[^,],", buffer);
    poke.description = strdup(buffer);

    // Ler peso
    fscanf(file, "%lf,", &poke.weight);

    // Ler altura
    fscanf(file, "%lf,", &poke.height);

    // Ler data de captura
    fscanf(file, "%hd-%hd-%hd,", &poke.capture_date.y, &poke.capture_date.m, &poke.capture_date.d);

    // Ler taxa de captura
    fscanf(file, "%hd,", &poke.capture_rate);

    // Ler tipos
    fscanf(file, "%hhd,%hhd\n", &poke.type[0], &poke.type[1]);

    return poke;
}

int main(int argc, char **argv)
{
    const char *filename = (argc > 1) ? argv[1] : DEFAULT_DB;
    FILE *file = fopen(filename, "r");

    if (!file)
    {
        fprintf(stderr, "Falha ao abrir o arquivo %s.\n", filename);
        return 1;
    }

    Queue queue;
    init_queue(&queue, 10);  // Inicializando a fila com capacidade de 10

    // Ler os dados do arquivo e enfileirar os Pokémons.
    while (!feof(file))
    {
        Pokemon poke = read_pokemon(file);
        enqueue(&queue, poke);
    }

    fclose(file);

    // Imprimir todos os Pokémons na fila.
    while (queue.size > 0)
    {
        Pokemon poke = dequeue(&queue);
        print_pokemon(&poke);
        free(poke.name);
        free(poke.description);
    }

    // Liberar a memória da fila.
    free_queue(&queue);

    return 0;
}