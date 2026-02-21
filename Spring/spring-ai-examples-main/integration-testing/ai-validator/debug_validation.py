#!/usr/bin/env python3
"""
Debug validation script
"""

import sys
import traceback
from pathlib import Path

# Add lib directory to path for imports
sys.path.insert(0, str(Path(__file__).parent / "lib"))

from validate_example import SpringAIExampleValidator, ValidationRequest

def debug_validation():
    """Debug the validation process step by step"""
    
    log_path = "../logs/integration-tests/helloworld-spring-boot-1754071405537.log"
    
    request = ValidationRequest(
        log_path=log_path,
        example_name="helloworld",
        expected_behavior="Accept user input 'tell me a joke' and return a coherent joke response",
        prompt_template="chat_example_validation"
    )
    
    try:
        print("Creating validator...")
        validator = SpringAIExampleValidator()
        
        print("Loading log file...")
        log_content = validator._load_log_file(request.log_path)
        print(f"Log content length: {len(log_content)}")
        print(f"Log preview: {log_content[:200]}...")
        
        print("\nBuilding prompt...")
        prompt_content = validator._build_validation_prompt(request, log_content, "")
        print(f"Prompt length: {len(prompt_content)}")
        print(f"Prompt template preview: {prompt_content[:300]}...")
        
        print("\nExecuting Claude validation...")
        ai_result = validator._execute_claude_validation(prompt_content)
        print(f"AI result keys: {ai_result.keys()}")
        print(f"Success: {ai_result.get('success')}")
        print(f"JSON extraction success: {ai_result.get('json_extraction_success')}")
        print(f"JSON data: {ai_result.get('json_data')}")
        print(f"Response preview: {ai_result.get('response', '')[:200]}...")
        
        print("\nParsing result...")
        result = validator._parse_validation_result(ai_result, request)
        print(f"Final result: {result}")
        
    except Exception as e:
        print(f"Error occurred: {e}")
        print(f"Error type: {type(e)}")
        print(f"Traceback:")
        traceback.print_exc()

if __name__ == "__main__":
    debug_validation()