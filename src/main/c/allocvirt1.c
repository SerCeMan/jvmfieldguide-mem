#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    while (1) {
        size_t allocsize = 1000000;
        char *p = (char *) malloc(allocsize);
        //
        usleep(1);
    }
    return 0;
}
