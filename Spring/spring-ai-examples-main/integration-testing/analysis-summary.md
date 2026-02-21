# Integration Test Analysis Summary

## 1. Brave Examples with API Keys

Both examples **PASS** with both Spring AI 1.0.0 and 1.0.1 when API keys are provided:
- `model-context-protocol/web-search/brave-chatbot` ✅ (with ANTHROPIC_API_KEY and BRAVE_API_KEY)
- `model-context-protocol/web-search/brave-starter` ✅ (with ANTHROPIC_API_KEY and BRAVE_API_KEY)

## 2. Kotlin Hello World "Regression"

**FALSE NEGATIVE** - The example actually works in both versions!
- Spring AI 1.0.0: ✅ Passes (app works, AI validation passes)
- Spring AI 1.0.1: ❌ False failure (app works perfectly, AI validation tool fails)

The application successfully:
- Starts Spring Boot
- Connects to OpenAI
- Generates a joke
- Shuts down cleanly

The failure is in the AI validation script itself, not the example.

## 3. MCP Client/Server Examples

### PASSING MCP Examples:
- `model-context-protocol/brave` ✅
- `model-context-protocol/client-starter/starter-default-client` ✅
- `model-context-protocol/filesystem` ✅
- `model-context-protocol/sqlite/chatbot` ✅
- `model-context-protocol/sqlite/simple` ✅

### FAILING MCP Examples:
- `model-context-protocol/client-starter/starter-webflux-client` ❌
- `model-context-protocol/dynamic-tool-update/client` ❌
- `model-context-protocol/dynamic-tool-update/server` ❌

The dynamic-tool-update examples likely fail because they need to run as a pair (client + server).

## 4. Timeout Examples

### Examples that timeout (run indefinitely as web servers):
1. `misc/openai-streaming-response` - REST API endpoint
2. `model-context-protocol/weather/starter-webmvc-server` - Web server
3. `prompt-engineering/prompt-engineering-patterns` - Takes >3 minutes

### Failing MCP Client/Server Examples:
1. `model-context-protocol/client-starter/starter-webflux-client`
2. `model-context-protocol/dynamic-tool-update/client`
3. `model-context-protocol/dynamic-tool-update/server`

## 5. Paired Execution Approach

For the `dynamic-tool-update` examples that need paired execution:

### Current Approach Issues:
- Testing them individually causes connection failures
- Server needs to be running before client connects

### Proposed Solution:
1. Start the server in background with a startup delay
2. Wait for server to be ready (port check or health endpoint)
3. Run the client test
4. Kill the server process

### Alternative Approaches:
- Create a composite test that starts both
- Use docker-compose to orchestrate them
- Skip these from automated tests and mark as "manual verification required"

## Updated Test Results Summary

### With API Keys and Corrections:
| Version | Actually Passing | Actually Failing | Timeout |
|---------|-----------------|------------------|---------|
| 1.0.0   | 17 (70.8%) | 4 (16.7%) | 3 (12.5%) |
| 1.0.1   | 17 (70.8%) | 4 (16.7%) | 3 (12.5%) |

### Actually Failing (both versions):
1. `kotlin/rag-with-kotlin` - Requires Docker
2. `model-context-protocol/client-starter/starter-webflux-client` - Connection issue
3. `model-context-protocol/dynamic-tool-update/client` - Needs server
4. `model-context-protocol/dynamic-tool-update/server` - Needs client

### Conclusion:
**No real regressions between 1.0.0 and 1.0.1!** The kotlin-hello-world "failure" is a testing framework issue, not an actual regression.