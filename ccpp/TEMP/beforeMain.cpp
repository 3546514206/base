#include <stdlib.h>
#include <stdio.h>

static void __attribute__ ((constructor)) beforeMain(void)
{
    printf("Before main...\n");
}

int main(void)
{
    printf("Main!\n");
    return 0;
}