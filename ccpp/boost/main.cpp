
#include "thread_sample.hpp"
#include "atomic_sample.hpp"


int main() {

//    thread_testing_space::test_thread();

    atomic_tester *tester = new atomic_tester;
    tester->test_sample_case();


    delete tester;
    return 0;
}

