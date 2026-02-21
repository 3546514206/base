# GitHub Actions Setup Documentation

## Overview
This document describes the GitHub Actions CI/CD setup for Spring AI Examples integration testing.

## Workflow Location
- **File**: `.github/workflows/integration-tests-manual.yml`
- **Type**: Manual trigger (workflow_dispatch)
- **Purpose**: Test Spring AI examples with configurable versions

## Setup Process

### 1. Prerequisites
- GitHub repository access with Actions enabled
- Ability to add repository secrets

### 2. Required Secrets
Configure these in Settings → Secrets and variables → Actions:
- **OPENAI_API_KEY**: Required for OpenAI API calls in examples
- **ANTHROPIC_API_KEY**: Required for AI validation using Claude

### 3. Workflow Components

#### Environment Setup
1. **JDK 17**: Via `actions/setup-java@v4` with Temurin distribution
2. **JBang**: Via `jbangdev/setup-jbang@v0.119.0` 
3. **Python 3.11**: Via `actions/setup-python@v5`
4. **Claude Code CLI**: Via npm global install
5. **Maven Cache**: Automatic via setup-java
6. **JBang Cache**: Via `actions/cache@v4`

#### Version Management
- Uses `scripts/update-spring-ai-version.sh` to update Spring AI version
- Supports both release (e.g., 1.0.1) and SNAPSHOT versions
- Updates 17 pom.xml files with spring-ai.version property

#### Test Execution
- Runs 3 representative examples by default:
  - models/chat/helloworld
  - kotlin/kotlin-hello-world
  - misc/spring-ai-java-function-callback
- Continues running all tests even if one fails
- Collects results and fails at the end if any test failed

## CI-Specific Issues & Limitations

### 1. AI Validation
- **Issue**: Claude Code CLI requires npm installation
- **Solution**: Installing via `npm install -g @anthropic-ai/claude-code`
- **Limitation**: Not using official GitHub Action (yet)
- **TODO**: Optimize with `anthropics/claude-code-action@beta`

### 2. Exit Codes
- **Issue**: Script must properly propagate failures
- **Solution**: Added exit code handling in run-integration-tests.sh
- **Note**: Tests run sequentially to avoid port conflicts

### 3. Dependencies
- **JBang**: Must be installed for integration tests
- **Claude CLI**: Required for AI validation
- **Python**: Installed but not currently used (ready for future tools)

### 4. Performance
- **Initial run**: ~5-10 minutes (downloads dependencies)
- **Cached runs**: ~3-5 minutes (uses cached dependencies)
- **Artifact storage**: Logs preserved for 7 days

## Version Management Integration

### Success Points
- ✅ Dynamic version switching works correctly
- ✅ Both 1.0.1 and 1.1.0-SNAPSHOT versions supported
- ✅ Version update script integrated seamlessly
- ✅ Backup/restore capability available (locally)

### How It Works
1. User specifies version in workflow dispatch input
2. Workflow runs update-spring-ai-version.sh
3. All 17 relevant pom.xml files updated
4. Tests run with specified version
5. Version shown in logs and summaries

## Usage

### Running the Workflow
1. Go to Actions tab in GitHub
2. Select "Integration Tests (Manual)"
3. Click "Run workflow"
4. Configure:
   - **Spring AI Version**: e.g., 1.0.1 or 1.1.0-SNAPSHOT
   - **Test filter**: Optional, leave empty for default 3 tests
5. Click green "Run workflow" button

### Monitoring
- Real-time logs available in Actions tab
- Job summary shows test results
- Artifacts contain detailed logs
- Exit code indicates overall success/failure

## Troubleshooting

### Test Failures
1. Check if ANTHROPIC_API_KEY is configured
2. Verify OPENAI_API_KEY is valid
3. Review logs in uploaded artifacts
4. Check for version compatibility issues

### Claude CLI Issues
- Verify npm install succeeded
- Check PATH includes npm global bin
- May need to use `--dangerously-skip-permissions` flag

### Port Conflicts
- Tests run sequentially to avoid port 8080 conflicts
- If issues persist, check for hanging processes

## Future Improvements
- Migrate to `anthropics/claude-code-action@beta`
- Add parallel test execution with dynamic ports
- Implement cost tracking
- Add more examples to default test set
- Create separate workflows for different triggers