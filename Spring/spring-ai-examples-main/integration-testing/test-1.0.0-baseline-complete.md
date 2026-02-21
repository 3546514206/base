# Spring AI 1.0.0 Baseline Test Results

## Test Date: 2025-08-06
## Spring AI Version: 1.0.0

### Summary
- **Total Tests**: 24
- **Passed**: 15 (62.5%)
- **Failed**: 6 (25%)
- **Timeout**: 3 (12.5%)

### Confirmed Test Results

| # | Module | Result | Notes |
|---|--------|--------|-------|
| 1 | agentic-patterns/chain-workflow | ✅ PASSED | |
| 2 | agentic-patterns/evaluator-optimizer | ✅ PASSED | |
| 3 | agentic-patterns/orchestrator-workers | ✅ PASSED | |
| 4 | agentic-patterns/parallelization-workflow | ✅ PASSED | |
| 5 | agentic-patterns/routing-workflow | ✅ PASSED | |
| 6 | agents/reflection | ✅ PASSED | |
| 7 | kotlin/kotlin-function-callback | ✅ PASSED | |
| 8 | kotlin/kotlin-hello-world | ✅ PASSED | |
| 9 | kotlin/rag-with-kotlin | ❌ FAILED | Docker not running (after path fix) |
| 10 | misc/openai-streaming-response | ⏱️ TIMEOUT | Web app runs indefinitely |
| 11 | misc/spring-ai-java-function-callback | ✅ PASSED | |
| 12 | model-context-protocol/brave | ✅ PASSED | |
| 13 | model-context-protocol/client-starter/starter-default-client | ✅ PASSED | |
| 14 | model-context-protocol/client-starter/starter-webflux-client | ❌ FAILED | Exit code 2 |
| 15 | model-context-protocol/dynamic-tool-update/client | ❌ FAILED | Exit code 1 |
| 16 | model-context-protocol/dynamic-tool-update/server | ❌ FAILED | Exit code 1 |
| 17 | model-context-protocol/filesystem | ✅ PASSED | |
| 18 | model-context-protocol/sqlite/chatbot | ✅ PASSED | Interactive Scanner app |
| 19 | model-context-protocol/sqlite/simple | ✅ PASSED | |
| 20 | model-context-protocol/weather/starter-webmvc-server | ⏱️ TIMEOUT | Web server |
| 21 | model-context-protocol/web-search/brave-chatbot | ❌ FAILED | Missing API key or config |
| 22 | model-context-protocol/web-search/brave-starter | ❌ FAILED | Exit code 1 |
| 23 | models/chat/helloworld | ✅ PASSED | |
| 24 | prompt-engineering/prompt-engineering-patterns | ⏱️ TIMEOUT | Exit code 124 (2 min timeout) |

### Known Issues
1. **Docker requirement**: kotlin/rag-with-kotlin requires Docker running
2. **Web applications**: Some apps (openai-streaming-response, weather/starter-webmvc-server) run indefinitely as servers
3. **MCP client/server**: dynamic-tool-update examples may need both client and server running together
4. **API Keys**: Some examples require specific API keys (ANTHROPIC_API_KEY for brave examples)

### Next Steps
Need to complete testing for modules 17-19, 22-24 to get full baseline.