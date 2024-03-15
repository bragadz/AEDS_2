#include <stdio.h>
#include <string.h>

int main() {
    char input[100001];
    char beiju[100001];
    int pos = 0;

    while (fgets(input, sizeof(input), stdin) != NULL) {
        int len = strlen(input);

        for (int i = 0; i < len; i++) {
            if (input[i] == '[') {
                pos = 0;
            } else if (input[i] == ']') {
                pos = strlen(beiju);
            } else {
                memmove(beiju + pos + 1, beiju + pos, strlen(beiju) - pos + 1);
                beiju[pos] = input[i];
                pos++;
            }
        }

        beiju[strlen(beiju) - 1] = '\0';

        printf("%s\n", beiju);
        memset(beiju, 0, sizeof(beiju));
        pos = 0;
    }

    return 0;
}
