# JBang Integration Test Pattern - Standard Guidelines

## Overview

This document establishes the standard pattern for creating JBang integration test scripts in the Spring AI Examples repository. As of Phase 3a.6, all JBang scripts must use centralized utilities to eliminate code duplication.

## The Standard Pattern

### Template for New JBang Scripts

```java
///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.zeroturnaround:zt-exec:1.12
//DEPS com.fasterxml.jackson.core:jackson-databind:2.17.1
//JAVA 17
//FILES ExampleInfo.json
//SOURCES [RELATIVE_PATH]/integration-testing/jbang-lib/IntegrationTestUtils.java

/*
 * Integration test launcher for [MODULE_NAME]
 * Uses centralized utilities from IntegrationTestUtils
 */

public class Run[ModuleName] {
    
    public static void main(String... args) throws Exception {
        IntegrationTestUtils.runIntegrationTest("[module-name]");
    }
}
```

## Key Components

### 1. JBang Header
- Must include exact dependencies (zt-exec and jackson-databind)
- Java 17 is the standard version
- Always include `ExampleInfo.json` as a file dependency

### 2. SOURCES Directive
The path to `IntegrationTestUtils.java` varies by module depth:

```
Module at: kotlin/kotlin-hello-world (2 levels deep)
Use: //SOURCES ../../../integration-testing/jbang-lib/IntegrationTestUtils.java

Module at: models/chat/helloworld (3 levels deep)  
Use: //SOURCES ../../../../integration-testing/jbang-lib/IntegrationTestUtils.java

Module at: model-context-protocol/client/starter/example (4 levels deep)
Use: //SOURCES ../../../../../integration-testing/jbang-lib/IntegrationTestUtils.java
```

### 3. Class Naming Convention
- Class name: `Run[ModuleName]` (PascalCase)
- File name: `Run[ModuleName].java`
- Examples:
  - `RunHelloworld.java`
  - `RunKotlinHelloWorld.java`
  - `RunStarterDefaultClient.java`

### 4. Module Name Parameter
Pass the module name to `runIntegrationTest()`:
- Use the directory name or a descriptive identifier
- This is used for logging and progress reporting
- Examples:
  - `"helloworld"`
  - `"kotlin-hello-world"`
  - `"starter-default-client"`

## What NOT to Do

### ❌ Don't Copy Old Patterns
```java
// DON'T DO THIS - Old pattern with inline logic
public class RunExample {
    public static void main(String... args) throws Exception {
        // 100+ lines of test logic here
        // Building, running, verifying, etc.
    }
}
```

### ❌ Don't Modify Individual Scripts
- Don't add custom logic to individual test scripts
- Don't override behavior for specific tests
- All customization should be in `ExampleInfo.json`

### ❌ Don't Use Wrong Paths
```java
// WRONG - Missing '../' levels
//SOURCES integration-testing/jbang-lib/IntegrationTestUtils.java

// WRONG - Absolute path
//SOURCES /home/user/projects/spring-ai-examples/integration-testing/jbang-lib/IntegrationTestUtils.java
```

## Benefits of This Pattern

1. **Consistency**: All tests behave identically
2. **Maintainability**: Fix bugs in one place
3. **Simplicity**: New tests are trivial to create
4. **Reliability**: Proven test execution logic
5. **Debugging**: Standardized logging and error reporting

## Creating a New Integration Test

### Step 1: Create Directory
```bash
mkdir -p your-module/integration-tests
```

### Step 2: Create Configuration
```bash
cat > your-module/integration-tests/ExampleInfo.json << 'EOF'
{
  "timeoutSec": 300,
  "successRegex": [
    "BUILD SUCCESS",
    "Started.*Application"
  ],
  "requiredEnv": ["OPENAI_API_KEY"]
}
EOF
```

### Step 3: Create JBang Script
1. Count the directory depth of your module
2. Use the template above
3. Replace `[RELATIVE_PATH]` with correct `../` count
4. Replace `[MODULE_NAME]` and `[ModuleName]` appropriately
5. Save as `RunYourModule.java`

### Step 4: Test Locally
```bash
cd your-module/integration-tests
jbang RunYourModule.java
```

## Troubleshooting

### "Could not find: IntegrationTestUtils.java"
- Count your module's directory depth
- Add the correct number of `../` to reach repository root
- Then append `integration-testing/jbang-lib/`

### "Cannot find symbol: IntegrationTestUtils"
- Verify the SOURCES directive is present
- Check the path is correct
- Ensure no typos in the class name

### Different Behavior Than Expected
- Check `ExampleInfo.json` configuration
- All behavior customization happens through configuration
- Don't modify the JBang script itself

## Migration Guide

When updating an old script:

1. **Backup** the old script (for reference)
2. **Create** new script using the template
3. **Verify** the configuration in `ExampleInfo.json`
4. **Test** locally to ensure it works
5. **Delete** the backup once confirmed working

## Future Enhancements

All enhancements to integration testing should be made in `IntegrationTestUtils.java`:
- New features
- Bug fixes
- Performance improvements
- Additional validation

Individual JBang scripts should remain minimal and unchanged.

## Summary

The centralized JBang pattern is mandatory for all new integration tests. This ensures consistency, maintainability, and reliability across the entire test suite. When in doubt, look at existing examples like `RunHelloworld.java` for reference.