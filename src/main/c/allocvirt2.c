#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    while (1) {
        size_t allocsize = 1000000;
        char *p = (char *) malloc(allocsize);
        for (int i = 0; i < allocsize; ++i) p[i] = 1;
        usleep(1);
    }
    return 0;
}
