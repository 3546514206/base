# Integration Testing Troubleshooting Guide

## Quick Reference

| Symptom | Most Likely Cause | Quick Fix |
|---------|-------------------|-----------|
| ❌ Port 8080 in use | Previous test didn't clean up | `lsof -ti:8080 \| xargs -r kill` |
| ❌ NoSuchElementException | Interactive app, missing config | Add `"requiresUserInteraction": true` |
| ❌ Docker Compose error | Path/working directory issue | Check compose.yaml location |
| ❌ Missing env variable | API key not set | `export OPENAI_API_KEY=your-key` |
| ❌ AI validation failed | Application didn't start properly | Check Spring Boot log for actual errors |
| ❌ Pattern not found | Output changed or wrong regex | Update successRegex patterns |

## Common Issues & Solutions

### 1. Port Conflicts

#### Symptom
```
Port 8080 was already in use
org.springframework.boot.web.server.PortInUseException
```

#### Root Cause
Previous Spring Boot processes didn't terminate cleanly

#### Solutions
```bash
# Quick fix - kill processes on port 8080
lsof -ti:8080 | xargs -r kill

# Comprehensive cleanup (used in run-integration-tests.sh)
./integration-testing/scripts/run-integration-tests.sh --clean-logs

# Check what's using the port
lsof -i :8080
```

#### Prevention
- Use `run-integration-tests.sh` which includes automatic port cleanup
- Allow processes to shut down gracefully between tests
- Consider dynamic port assignment for parallel testing

### 2. Interactive Application Failures

#### Symptom
```
java.util.NoSuchElementException: No line found
Application exited with code: 1
```

#### Root Cause
Applications using `Scanner.nextLine()` fail when no input is available in automated testing

#### Solution
Configure the test to allow non-zero exit codes for interactive applications:

```json
{
  "aiValidation": {
    "enabled": true,
    "validationMode": "fallback",
    "successCriteria": {
      "requiresUserInteraction": true
    }
  }
}
```

#### How It Works
- Test validates application startup and readiness indicators
- Allows exit code 1 as expected behavior
- Focuses on "ready for interaction" rather than "completed interaction"

#### Examples
- `agents/reflection` - Shows "Let's chat!" then fails on Scanner.nextLine()
- `sqlite/chatbot` - Shows "Starting interactive chat session" then fails
- `brave-chatbot` - Shows "I am your AI assistant" then fails

### 3. Docker Compose Path Issues

#### Symptom
```
Docker Compose file 'kotlin/rag-with-kotlin/compose.yaml' does not exist
```

#### Root Cause
Application looks for Docker Compose file relative to project root, but runs from module directory

#### Solutions

**Option 1: Setup Commands**
```json
{
  "setupCommands": [
    "docker-compose up -d",
    "sleep 10"
  ],
  "cleanupCommands": [
    "docker-compose down"
  ]
}
```

**Option 2: Working Directory Fix**
```json
{
  "setupCommands": [
    "bash -c 'cd ../../ && docker-compose -f kotlin/rag-with-kotlin/compose.yaml up -d'"
  ]
}
```

**Option 3: Application Configuration**
Update the application's Docker Compose configuration to use relative paths from module directory

### 4. Environment Variable Issues

#### Symptom
```
❌ Missing required environment variable: OPENAI_API_KEY
```

#### Solutions
```bash
# Set for current session
export OPENAI_API_KEY="your-api-key-here"

# For Anthropic examples
export ANTHROPIC_API_KEY="your-anthropic-key-here"

# For Brave Search examples  
export BRAVE_API_KEY="your-brave-key-here"

# Verify environment variables are set
env | grep -E "(OPENAI|ANTHROPIC|BRAVE)_API_KEY"
```

#### Configuration
Ensure `ExampleInfo.json` lists all required environment variables:
```json
{
  "requiredEnv": [
    "OPENAI_API_KEY",
    "BRAVE_API_KEY"
  ]
}
```

### 5. AI Validation Failures

#### Symptom
```
⚠️ AI validation failed: AI validation script failed with exit code: 1
```

#### Diagnosis Steps
1. **Check the actual application output** in the Spring Boot log
2. **Verify the application started successfully** (look for "Started Application")
3. **Check for real errors** in the application logs
4. **Ensure environment variables are set** properly

#### Common Causes
- **Application startup failure**: Real Spring Boot errors masked by AI validation
- **Missing dependencies**: External services not available
- **Configuration issues**: Wrong API keys or configuration values
- **Network problems**: Can't reach external APIs

#### Solutions
```bash
# Run test individually to see full output
cd module-directory
jbang integration-tests/RunModule.java

# Check the detailed Spring Boot log
cat integration-testing/logs/integration-tests/module-spring-boot-*.log

# Test the application manually
./mvnw spring-boot:run
```

### 6. Pattern Matching Issues

#### Symptom
```
❌ Missing: Started.*Application
❌ Missing: Expected output pattern
```

#### Diagnosis
1. **Check actual output** in the displayed application logs
2. **Verify regex patterns** are correct for the application
3. **Look for output variations** due to different environments

#### Solutions

**Update Success Patterns**
```json
{
  "successRegex": [
    "Started.*Application",          // Standard Spring Boot
    "Your specific pattern here"     // Application-specific
  ]
}
```

**Test Regex Patterns**
```bash
# Test regex against actual output
grep -E "Started.*Application" log-file.txt
```

**Switch to AI Validation**
For applications with unpredictable output:
```json
{
  "aiValidation": {
    "enabled": true,
    "validationMode": "primary"
  }
}
```

### 7. Build Failures

#### Symptom
```
[ERROR] Failed to execute goal ... compilation failure
```

#### Solutions
```bash
# Clean build
./mvnw clean

# Update dependencies
./mvnw dependency:resolve

# Check Java version
java -version  # Should be 17+

# Manual build test
./mvnw clean package
```

### 8. JBang Compilation Issues

#### Symptom
```
[jbang] Error compiling script
cannot find symbol
```

#### Common Causes
- **Missing dependencies** in JBang `//DEPS` directives
- **Wrong source paths** in `//SOURCES` directive
- **Java version mismatch**

#### Solutions
```bash
# Update JBang
jbang version
curl -Ls https://sh.jbang.dev | bash -s - app setup

# Clear JBang cache
jbang cache clear

# Test JBang script manually
jbang --verbose integration-tests/RunModule.java
```

## Debugging Workflows

### 1. Test Failure Investigation

```bash
# Step 1: Run individual test
cd failing-module
jbang integration-tests/RunModule.java

# Step 2: Check logs
cat integration-testing/logs/integration-tests/module-spring-boot-*.log

# Step 3: Manual application test
./mvnw spring-boot:run

# Step 4: Check configuration
cat integration-tests/ExampleInfo.json
```

### 2. New Test Creation Debug

```bash
# Step 1: Scaffold test
python3 integration-testing/scripts/scaffold_integration_test.py module-path --complexity simple

# Step 2: Test immediately
cd module-path
jbang integration-tests/RunModule.java

# Step 3: Adjust configuration
# Edit integration-tests/ExampleInfo.json

# Step 4: Re-test
jbang integration-tests/RunModule.java
```

### 3. Full Test Suite Debug

```bash
# Run with detailed output
./integration-testing/scripts/rit-direct.sh --verbose

# Run specific test only
./integration-testing/scripts/rit-direct.sh module-name

# Clean logs and re-run
./integration-testing/scripts/rit-direct.sh --clean-logs
```

## Configuration Debugging

### Validation Mode Selection

| Application Type | Recommended Mode | Reasoning |
|------------------|------------------|-----------|
| Interactive (Scanner) | `fallback` | Allow regex failure, use AI to validate readiness |
| Predictable output | `hybrid` | Use regex for speed, AI for additional validation |
| Variable AI output | `primary` | Skip regex, use only AI validation |

### Timeout Tuning

```json
{
  "timeoutSec": 120  // Adjust based on application complexity
}
```

Guidelines:
- **Simple examples**: 60-120 seconds
- **Interactive apps**: 15-25 seconds (just startup validation)
- **Complex workflows**: 300-600 seconds
- **Docker-dependent**: 180+ seconds (allow for service startup)

### Environment Variable Debugging

```bash
# Check all environment variables
printenv | grep -E "(API_KEY|DATABASE|DOCKER)"

# Test specific variable
echo $OPENAI_API_KEY

# Set temporarily
OPENAI_API_KEY=test-key jbang integration-tests/RunModule.java
```

## Advanced Troubleshooting

### Log Analysis

**Spring Boot Log Location**
```bash
integration-testing/logs/integration-tests/module-spring-boot-timestamp.log
```

**Test Execution Log Location**
```bash
integration-testing/logs/background-runs/test_module_timestamp.log
```

**Log Analysis Commands**
```bash
# Find errors in Spring Boot log
grep -E "(ERROR|WARN|Exception)" spring-boot.log

# Check startup sequence
grep -E "(Starting|Started)" spring-boot.log

# Find specific patterns
grep -E "your-pattern" spring-boot.log
```

### Performance Issues

**Symptoms**
- Tests timeout frequently
- Slow application startup
- Memory issues during testing

**Solutions**
```bash
# Increase timeout for specific test
# Edit ExampleInfo.json: "timeoutSec": 600

# Check system resources
free -h
df -h

# Limit parallel execution
# Currently tests run sequentially to avoid port conflicts
```

### AI Validation Debugging

**Check AI validation configuration**
```json
{
  "aiValidation": {
    "enabled": true,
    "validationMode": "primary|hybrid|fallback",
    "expectedBehavior": "Clear description of what should happen"
  }
}
```

**AI validation cost monitoring**
- Each validation costs ~$0.002 (400 tokens)
- Monitor usage if running frequently
- Consider using regex for predictable outputs

## Prevention Best Practices

### 1. Before Creating New Tests
- Use appropriate configuration template from `integration-testing/learnings/templates/`
- Test locally before committing
- Verify all environment variables are documented
- Check timeout is appropriate for application complexity

### 2. Regular Maintenance
- Run full test suite monthly: `./integration-testing/scripts/rit-direct.sh`
- Update success patterns when applications change
- Review and update environment variable requirements
- Monitor AI validation costs

### 3. Code Review Checklist
- [ ] Configuration follows established templates
- [ ] Timeout is appropriate for application type
- [ ] All environment variables are documented
- [ ] Success patterns match actual application output
- [ ] AI validation mode is appropriate for application type
- [ ] Test passes locally before commit

## Getting Help

1. **Check this guide** for common issues
2. **Review logs** in `integration-testing/logs/`
3. **Test manually** with `./mvnw spring-boot:run`
4. **Check configuration** against templates in `integration-testing/learnings/templates/`
5. **Run individual test** with `jbang integration-tests/RunModule.java`

For issues not covered here, check the learning documents in `integration-testing/learnings/` for detailed technical insights and patterns.