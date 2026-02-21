#!/usr/bin/env python3
"""
Simple test for the validation system
"""

import sys
from pathlib import Path

# Add lib directory to path for imports
sys.path.insert(0, str(Path(__file__).parent / "lib"))

from claude_code_wrapper import ClaudeCodeWrapper

def test_simple_prompt():
    """Test with a very simple prompt"""
    wrapper = ClaudeCodeWrapper()
    
    simple_prompt = """
Please analyze this Spring Boot log and respond with JSON:

```
Spring AI Hello World!
USER: Tell me a joke
ASSISTANT: Why did the scarecrow win an award? Because he was outstanding in his field!
Hello World demo completed!
```

Did the application work correctly? Respond with JSON format:
{
  "success": true,
  "reasoning": "explanation here"
}
"""
    
    print("Testing simple prompt...")
    result = wrapper.analyze_from_text_with_json(
        prompt_text=simple_prompt,
        timeout=60,
        show_progress=True
    )
    
    print(f"Success: {result.get('success')}")
    print(f"JSON extracted: {result.get('json_extraction_success')}")
    print(f"JSON data: {result.get('json_data')}")
    print(f"Raw response: {result.get('response', '')[:200]}...")
    
    return result

if __name__ == "__main__":
    test_simple_prompt()