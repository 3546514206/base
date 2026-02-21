# Spring AI 1.0.1 Complete Test Results

## Test Date: 2025-08-06
## Spring AI Version: 1.0.1

### Summary
- **Total Tests**: 24
- **Passed**: 14 (58.3%)
- **Failed**: 7 (29.2%)
- **Timeout**: 3 (12.5%)

### Complete Test Results

| # | Module | Result | Notes |
|---|--------|--------|-------|
| 1 | agentic-patterns/chain-workflow | ✅ PASSED | |
| 2 | agentic-patterns/evaluator-optimizer | ✅ PASSED | |
| 3 | agentic-patterns/orchestrator-workers | ✅ PASSED | |
| 4 | agentic-patterns/parallelization-workflow | ✅ PASSED | |
| 5 | agentic-patterns/routing-workflow | ✅ PASSED | |
| 6 | agents/reflection | ✅ PASSED | |
| 7 | kotlin/kotlin-function-callback | ✅ PASSED | |
| 8 | kotlin/kotlin-hello-world | ❌ FAILED | Exit code 1 (NEW FAILURE) |
| 9 | kotlin/rag-with-kotlin | ❌ FAILED | Docker not running |
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
| 21 | model-context-protocol/web-search/brave-chatbot | ❌ FAILED | Missing ANTHROPIC_API_KEY |
| 22 | model-context-protocol/web-search/brave-starter | ❌ FAILED | Missing ANTHROPIC_API_KEY |
| 23 | models/chat/helloworld | ✅ PASSED | |
| 24 | prompt-engineering/prompt-engineering-patterns | ⏱️ TIMEOUT | Exit code 124 |

## Comparison: 1.0.0 vs 1.0.1

### Summary Comparison
| Version | Passed | Failed | Timeout |
|---------|--------|--------|---------|
| 1.0.0   | 15 (62.5%) | 6 (25%) | 3 (12.5%) |
| 1.0.1   | 14 (58.3%) | 7 (29.2%) | 3 (12.5%) |

### Key Changes
- **NEW REGRESSION**: `kotlin/kotlin-hello-world` - PASSED in 1.0.0, FAILED in 1.0.1
- All other test results remain consistent between versions

### Regression Details
The only regression found is:
- **kotlin/kotlin-hello-world**: Was passing in 1.0.0, now failing in 1.0.1 with exit code 1

### Consistent Failures (Both Versions)
1. `kotlin/rag-with-kotlin` - Docker requirement
2. `model-context-protocol/client-starter/starter-webflux-client`
3. `model-context-protocol/dynamic-tool-update/client`
4. `model-context-protocol/dynamic-tool-update/server`
5. `model-context-protocol/web-search/brave-chatbot` - API key
6. `model-context-protocol/web-search/brave-starter` - API key

### Consistent Timeouts (Both Versions)
1. `misc/openai-streaming-response` - Web application
2. `model-context-protocol/weather/starter-webmvc-server` - Web server
3. `prompt-engineering/prompt-engineering-patterns` - Complex/long-running

## Recommendation
Investigate the `kotlin/kotlin-hello-world` regression before deploying 1.0.1. This is the only test that worked in 1.0.0 but fails in 1.0.1.