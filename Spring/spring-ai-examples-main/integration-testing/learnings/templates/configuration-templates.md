# Integration Test Configuration Templates

## Overview

This document provides standardized `ExampleInfo.json` templates for different types of Spring AI examples. Use these templates to ensure consistency and optimal configuration for new integration tests.

## Template Categories

### 1. Interactive Applications Template
**Use for**: Applications using Scanner.nextLine(), chat interfaces, interactive prompts

```json
{
  "timeoutSec": 20,
  "successRegex": [
    "Started.*Application",
    "Interactive prompt pattern"
  ],
  "requiredEnv": [
    "OPENAI_API_KEY"
  ],
  "aiValidation": {
    "enabled": true,
    "validationMode": "fallback",
    "readmeFile": "../README.md",
    "expectedBehavior": "Interactive [TYPE] should start successfully and display the interaction prompt, indicating the system is ready for user input. The application will wait for interactive input to demonstrate [FUNCTIONALITY].",
    "promptTemplate": "example_validation",
    "successCriteria": {
      "requiresUserInteraction": true,
      "expectedComponents": ["spring_boot_startup", "[APP_TYPE]_ready", "interactive_prompt"],
      "validationNote": "Tests readiness for interactive [FUNCTIONALITY] workflow"
    }
  }
}
```

**Examples**: agents/reflection, sqlite/chatbot, brave-chatbot

### 2. Agentic Workflow Template
**Use for**: Multi-step AI processes, complex reasoning workflows, evaluation systems

```json
{
  "timeoutSec": 300,
  "successRegex": [
    "Started.*Application",
    "EVALUATION:\\\\s+PASS"
  ],
  "requiredEnv": [
    "OPENAI_API_KEY"
  ],
  "aiValidation": {
    "enabled": true,
    "validationMode": "primary",
    "readmeFile": "../README.md",
    "expectedBehavior": "[WORKFLOW_TYPE] should execute successfully with multi-step AI processing and produce evaluation results showing the workflow completed correctly.",
    "promptTemplate": "workflow_validation",
    "successCriteria": {
      "expectedSteps": 3,
      "evaluationRequired": true,
      "expectedComponents": ["spring_boot_startup", "workflow_execution", "evaluation_results"]
    }
  }
}
```

**Examples**: agentic-patterns/*, orchestrator-workers, routing-workflow

### 3. MCP Integration Template
**Use for**: Model Context Protocol examples, client-server communication

```json
{
  "timeoutSec": 120,
  "successRegex": [
    "Started.*Application",
    "MCP.*[Ii]nitialized",
    "Connection established|Tools available"
  ],
  "requiredEnv": [
    "OPENAI_API_KEY"
  ],
  "aiValidation": {
    "enabled": true,
    "validationMode": "hybrid",
    "readmeFile": "../README.md",
    "expectedBehavior": "MCP [CLIENT/SERVER] should start successfully, establish protocol connection, and demonstrate [FUNCTIONALITY] integration through Model Context Protocol.",
    "promptTemplate": "client_server_validation",
    "successCriteria": {
      "expectedComponents": ["spring_boot_startup", "mcp_initialization", "[PROTOCOL]_connection", "tool_availability"],
      "validationNote": "Tests MCP integration for [FUNCTIONALITY]"
    }
  }
}
```

**Examples**: model-context-protocol/*, filesystem, brave, weather servers

### 4. Simple Example Template
**Use for**: Basic demonstrations, hello world examples, simple function callbacks

```json
{
  "timeoutSec": 120,
  "successRegex": [
    "Started.*Application",
    "[Specific output pattern]"
  ],
  "requiredEnv": [
    "OPENAI_API_KEY"
  ],
  "aiValidation": {
    "enabled": true,
    "validationMode": "hybrid",
    "readmeFile": "../README.md",
    "expectedBehavior": "Simple [EXAMPLE_TYPE] should start successfully and demonstrate [FUNCTIONALITY] with clear output showing the integration working correctly.",
    "promptTemplate": "example_validation",
    "successCriteria": {
      "expectedComponents": ["spring_boot_startup", "[FUNCTIONALITY]_demonstration"],
      "validationNote": "Tests basic [FUNCTIONALITY] integration"
    }
  }
}
```

**Examples**: models/chat/helloworld, kotlin/kotlin-hello-world, misc/function-callback

### 5. Docker-Dependent Template
**Use for**: Applications requiring Docker Compose, external services

```json
{
  "timeoutSec": 180,
  "setupCommands": [
    "docker-compose up -d",
    "sleep 10"
  ],
  "cleanupCommands": [
    "docker-compose down"
  ],
  "successRegex": [
    "Started.*Application",
    "Connected to.*database|Service ready"
  ],
  "requiredEnv": [
    "OPENAI_API_KEY"
  ],
  "expectedFailurePatterns": [
    "Connection refused",
    "Service unavailable"
  ],
  "aiValidation": {
    "enabled": true,
    "validationMode": "hybrid",
    "readmeFile": "../README.md",
    "expectedBehavior": "[APPLICATION_TYPE] should start successfully, connect to external services via Docker, and demonstrate [FUNCTIONALITY] integration.",
    "promptTemplate": "example_validation",
    "successCriteria": {
      "requiresExternalServices": true,
      "expectedComponents": ["spring_boot_startup", "service_connection", "[FUNCTIONALITY]_demonstration"],
      "validationNote": "Tests [FUNCTIONALITY] with external service dependencies"
    }
  }
}
```

**Examples**: kotlin/rag-with-kotlin, brave-docker-agents-gateway

## Configuration Guidelines

### Timeout Standards
| Application Type | Timeout | Reasoning |
|------------------|---------|-----------|
| Simple Examples | 120s | Basic Spring Boot startup + demonstration |
| Interactive Apps | 15-25s | Quick startup validation, no full workflow |
| MCP Integration | 120s | Protocol handshake + tool discovery |
| Agentic Workflows | 300-600s | Complex multi-step AI processing |
| Docker-Dependent | 180s | Service startup + application initialization |

### AI Validation Modes
| Mode | Use Case | Pattern Check | AI Check |
|------|----------|---------------|----------|
| **primary** | Unpredictable AI outputs | ❌ Skip | ✅ Only |
| **hybrid** | Mix of predictable/variable | ✅ Required | ✅ Additional |
| **fallback** | Interactive applications | ✅ Primary | ✅ If regex fails |

### Success Pattern Guidelines
1. **Always include**: `"Started.*Application"` for Spring Boot validation
2. **Application-specific**: Key output indicating functionality works
3. **Interactive apps**: Include readiness indicator (`"Let's chat!"`, `"Starting interactive"`)
4. **MCP apps**: Include protocol initialization (`"MCP.*[Ii]nitialized"`)
5. **Evaluation apps**: Include completion indicator (`"EVALUATION:\\\\s+PASS"`)

### Environment Variable Standards
- **Primary**: `OPENAI_API_KEY` (most common)
- **Anthropic**: `ANTHROPIC_API_KEY` (for Claude-based examples)
- **Service-specific**: `BRAVE_API_KEY`, `DATABASE_URL`, etc.
- **Multiple providers**: Use array: `["OPENAI_API_KEY", "ANTHROPIC_API_KEY"]`

### AI Validation Best Practices

#### expectedBehavior Guidelines
- **Be specific**: Describe what the application should actually do
- **Include context**: Mention key technologies (MCP, Docker, specific AI features)
- **Set expectations**: Clarify what constitutes success
- **Note limitations**: For interactive apps, clarify testing scope

#### successCriteria Standards
```json
"successCriteria": {
  "requiresUserInteraction": true,        // For interactive apps
  "requiresExternalServices": true,       // For Docker/external deps
  "expectedSteps": 3,                     // For multi-step workflows
  "evaluationRequired": true,             // For evaluation frameworks
  "expectedComponents": [                 // Always include
    "spring_boot_startup",               // Standard component
    "app_specific_component",            // Application-specific
    "functionality_demonstration"        // What it demonstrates
  ],
  "validationNote": "Tests [specific purpose]"  // Brief test description
}
```

## Template Selection Guide

1. **Identify Application Type**:
   - Does it require user input? → Interactive Template
   - Is it a multi-step AI workflow? → Agentic Template  
   - Does it use MCP? → MCP Template
   - Does it need Docker? → Docker-Dependent Template
   - Otherwise → Simple Template

2. **Customize Template**:
   - Replace `[PLACEHOLDERS]` with specific values
   - Adjust timeout based on complexity
   - Add application-specific success patterns
   - Configure appropriate validation mode

3. **Validate Configuration**:
   - Test locally before committing
   - Verify all required environment variables
   - Check timeout is sufficient but not excessive
   - Ensure success patterns match actual output

## Maintenance Notes

- **Update templates** when new patterns emerge across multiple examples
- **Version templates** when making breaking changes to schema
- **Document exceptions** when examples require non-standard configuration
- **Regular review** of existing configurations against current templates