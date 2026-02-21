# Orchestrator-Workers Workflow Pattern

This project demonstrates the Orchestrator-Workers workflow pattern for building effective LLM-based systems, as described in [Anthropic's research on building effective agents](https://www.anthropic.com/research/building-effective-agents).

![Orchestration Workflow](https://www.anthropic.com/_next/image?url=https%3A%2F%2Fwww-cdn.anthropic.com%2Fimages%2F4zrzovbb%2Fwebsite%2F8985fc683fae4780fb34eab1365ab78c7e51bc8e-2401x1000.png&w=3840&q=75)

## Overview

The Orchestrator-Workers pattern is a flexible approach for handling complex tasks that require dynamic task decomposition and specialized processing. It consists of three main components:

- **Orchestrator**: A central LLM that analyzes tasks and determines required subtasks
- **Workers**: Specialized LLMs that execute specific subtasks
- **Synthesizer**: Component that combines worker outputs into a final result

## When to Use

This pattern is particularly effective for:

- Complex tasks where subtasks can't be predicted upfront
- Tasks requiring different approaches or perspectives
- Situations needing adaptive problem-solving
- Tasks benefiting from specialized processing


## Implementation

The implementation uses Spring AI's ChatClient for LLM interactions and consists of:

```java
public class OrchestratorWorkers {
    public WorkerResponse process(String taskDescription) {
        // 1. Orchestrator analyzes task and determines subtasks
        OrchestratorResponse orchestratorResponse = // ...

        // 2. Workers process subtasks in parallel
        List<String> workerResponses = // ...

        // 3. Results are combined into final response
        return new WorkerResponse(/*...*/);
    }
}
```

### Usage Example

```java
ChatClient chatClient = // ... initialize chat client
OrchestratorWorkers agent = new OrchestratorWorkers(chatClient);

// Process a task
WorkerResponse response = agent.process(
    "Generate both technical and user-friendly documentation for a REST API endpoint"
);

// Access results
System.out.println("Analysis: " + response.analysis());
System.out.println("Worker Outputs: " + response.workerResponses());
```

## Customization

The pattern can be customized through:

1. **Custom Prompts**: Provide specialized prompts for orchestrator and workers
```java
agent = new OrchestratorWorkers(
    chatClient,
    customOrchestratorPrompt,
    customWorkerPrompt
);
```

2. **Default Templates**: Modify the default prompts for common use cases
   - `DEFAULT_ORCHESTRATOR_PROMPT`: Template for task analysis
   - `DEFAULT_WORKER_PROMPT`: Template for worker processing

## Dependencies

- Spring AI
- Spring Boot
- Java 17 or later

## References

- [Building Effective Agents (Anthropic Research)](https://www.anthropic.com/research/building-effective-agents)
