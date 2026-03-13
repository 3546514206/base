# AGENTS.md - Coding Guidelines for Agentic AI

This repository contains multi-language examples covering language features, data structures, algorithms, design patterns, and application cases.

## Build/Test Commands

### Java (Maven)
```bash
mvn clean install                              # Full build
mvn compile                                    # Compile only
mvn test                                       # Run all tests
mvn test -Dtest=ClassName                      # Run single test class
mvn test -Dtest=ClassName#methodName            # Run single test method
```

### Java (Gradle)
```bash
gradle build                                   # Build project
gradle test                                    # Run all tests
gradle test --tests "ClassName"                 # Run single test class
gradle test --tests "ClassName.method"         # Run single test method
```

### Spring Boot
```bash
mvn spring-boot:run                            # Run application
mvn test                                       # Run tests
```

### Python
```bash
pip install -r requirements.txt                # Install dependencies
python main.py                                 # Run script
pytest                                         # Run all tests
pytest test_file.py                            # Run single test file
pytest test_file.py::test_function             # Run single test
pytest -v                                      # Verbose output
python -m unittest test_module                  # Run unittest
```

### Go
```bash
go build                                       # Build binary
go run main.go                                 # Run directly
go mod tidy                                    # Clean dependencies
go test ./...                                  # Run all tests
go test -v ./...                               # Verbose test output
go test -run TestName ./path/to/pkg            # Run specific test
go test -run TestName ./path/to/pkg -v         # Run specific test verbose
```

### JavaScript/TypeScript
```bash
npm install                                    # Install dependencies
npm run build                                  # Build project
npm run dev                                    # Development server
npm test                                       # Run all tests
npm test -- testName                           # Run specific test
npm test -- --testNamePattern="pattern"        # Run tests matching pattern
```

### C/C++
```bash
cmake .. && make                               # CMake build
make                                           # Makefile build
./test_executable                              # Run tests
```

### Kotlin
```bash
gradle build                                   # Build project
gradle run                                     # Run application
gradle test                                    # Run all tests
gradle test --tests "TestClass.method"         # Run single test
```

### Scala
```bash
sbt compile                                    # Compile
sbt run                                        # Run application
sbt test                                       # Run all tests
```

## Lint Commands

| Language | Lint Command | Config File |
|----------|--------------|-------------|
| JavaScript/Vue | `npm run lint` | `.eslintrc.js` |
| TypeScript | `npm run lint` | `.eslintrc` or `tsconfig.json` |
| Python | `flake8` or `pylint` | `setup.cfg` or `pyproject.toml` |
| Go | `go vet` or `golint` | Built-in |
| Java | `mvn checkstyle:check` | `checkstyle.xml` |
| Kotlin | `ktlint` | `.editorconfig` |

## Code Style Guidelines

### General
- **Encoding**: UTF-8
- **Line endings**: LF (Unix-style)
- **Final newline**: Required
- **Trailing whitespace**: Trim on save

### Java
- **Indentation**: 4 spaces
- **Naming**: PascalCase for classes, camelCase for methods/variables
- **Imports**: Organize alphabetically, avoid wildcard imports
- **Java version**: Primary Java 8 (maven.compiler.source=8), some projects use Java 21
- **Package naming**: `edu.zjnu.*` prefix
- **Javadoc**: Include `@author`, `@date`, `@description` in class headers
- **Test naming**: `*Test.java` suffix, use JUnit 4/5

### Python
- **Indentation**: 4 spaces
- **Naming**: snake_case for functions/variables, PascalCase for classes
- **Imports**: Standard library first, then third-party, then local
- **Virtual environments**: Use `venv`, place in `.venv/` directory
- **Test naming**: `test_*.py` prefix or `*_test.py` suffix

### Go
- **Indentation**: Tabs (Go standard)
- **Naming**: Exported functions start with uppercase, test functions prefixed with `Test`
- **Test files**: `*_test.go` suffix
- **Module naming**: lowercase with underscores (e.g., `_leetcode_006`)
- **Comments**: Chinese documentation acceptable (project convention)

### JavaScript/TypeScript
- **Indentation**: 2 spaces
- **Quotes**: Single quotes preferred
- **Semicolons**: Required (standard style)
- **Naming**: camelCase for variables/functions, PascalCase for classes
- **ESLint**: Extends `standard` config
- **TypeScript**: `strict: true`, `esModuleInterop: true`, target ES6+
- **Test naming**: `*.test.js` or `*.spec.ts` suffix

### Vue
- **Indentation**: 2 spaces
- **Style**: Standard + Vue plugin
- **Build**: Webpack or Vite

### C/C++
- **Standard**: C++11 or later
- **Build**: CMake 3.21+
- **Indentation**: Follow `.editorconfig` settings

## Error Handling Patterns

### Java
```java
try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
    // ...
} catch (IOException e) {
    logger.error("Error message", e);
    throw new RuntimeException("Context message", e);
}
```

### Python
```python
try:
    result = operation()
except ValueError as e:
    logger.error(f"Error: {e}")
    raise
```

### Go
```go
result, err := someFunction()
if err != nil {
    log.Printf("Error: %v", err)
    return err
}
```

## Project Structure Patterns

```
base/
├── AI/             # AI-related projects
├── Java/           # Maven/Gradle projects, design patterns, LeetCode
├── Python/         # Scripts, FastAPI, ML examples
├── JavaScript/     # Node.js, Electron apps
├── TypeScript/     # TypeScript examples
├── Go/             # Go modules, concurrency examples
├── Vue/            # Vue 2/3 projects
├── Spring/         # Spring Boot examples
├── CCPP/           # C/C++ CMake projects
├── Kotlin/         # Kotlin examples
├── Scala/          # Scala examples
├── Android/        # Android Gradle projects
└── Shell/          # Shell scripts
```

## Repository Conventions

- **Directory naming**: Projects prefixed with `_` for grouping (e.g., `_leetcode_001`, `_jfree_demo`)
- **Test location**: Test files alongside source or in `test/` directory
- **Config files**: Configuration files at project root (pom.xml, package.json, go.mod, tsconfig.json)
- **Maven projects**: Use `<groupId>edu.zjnu</groupId>`, Java 8 target, UTF-8 encoding
- **Go modules**: Use lowercase with underscores (e.g., `module _leetcode_006`)
- **Code comments**: Chinese documentation acceptable in this codebase
- **Virtual environments**: Python projects use `.venv/` directory (gitignored)