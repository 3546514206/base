# AI Validator for Spring AI Examples

This directory contains the AI-powered validation system for Spring AI integration tests. It uses Claude Code to intelligently analyze application logs and determine if example applications successfully demonstrated their intended functionality.

## Components

- `lib/` - Core libraries (claude_code_wrapper.py, animated_progress.py)
- `templates/` - Validation prompt templates for different example types
- `validate_example.py` - Main validation script (to be implemented)
- `requirements.txt` - Python dependencies

## Usage

The AI validator is called from Java integration tests to validate that example applications:
1. Ran without exceptions
2. Demonstrated their intended functionality
3. Worked correctly for multi-component examples

## Status

🚧 **Under Development** - Part of Phase 1 implementation from plans/ai-validation-plan.md