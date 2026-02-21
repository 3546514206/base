///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.zeroturnaround:zt-exec:1.12
//DEPS com.fasterxml.jackson.core:jackson-databind:2.17.1
//JAVA 17
//FILES ExampleInfo.json
//SOURCES ../../../integration-testing/jbang-lib/IntegrationTestUtils.java

/*
 * Integration test launcher for kotlin-hello-world
 * Refactored to use centralized utilities
 */

public class RunKotlinHelloWorld {
    
    public static void main(String... args) throws Exception {
        IntegrationTestUtils.runIntegrationTest("kotlin-hello-world");
    }
}
