#include <stdlib.h>
#include <stdio.h>

static void __attribute__ ((constructor(101))) beforeMain1(void)
{
    printf("Before main...1\n");
}
static void __attribute__ ((constructor(102))) beforeMain2(void)
{
    printf("Before main...2\n");
}
static void __attribute__ ((constructor(103))) beforeMain3(void)
{
    printf("Before main...3\n");
}

int main(void)
{
    printf("Main!\n");
    return 0;
}