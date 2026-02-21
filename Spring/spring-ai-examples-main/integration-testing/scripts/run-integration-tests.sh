#!/bin/bash

# Direct RIT - Run Integration Tests directly via JBang
# Complete bypass of Python script to avoid hanging issues
#
# Usage:
#   ./run-integration-tests.sh                  # Run all tests
#   ./run-integration-tests.sh --clean-logs     # Clean all logs and run all tests
#   ./run-integration-tests.sh helloworld       # Run only tests matching "helloworld"
#   ./run-integration-tests.sh --clean-logs helloworld  # Clean logs and run specific test

# Don't exit on command failure (let tests fail individually)
set -uo pipefail

# Find script directory and project root
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "${SCRIPT_DIR}/../.." && pwd)"

# Change to project root to ensure consistent behavior
cd "${PROJECT_ROOT}"

# Parse arguments
CLEAN_LOGS=false
TEST_FILTER=""

for arg in "$@"; do
    case $arg in
        --clean-logs)
            CLEAN_LOGS=true
            shift
            ;;
        *)
            TEST_FILTER="$arg"
            ;;
    esac
done

LOGS_BASE_DIR="integration-testing/logs"
LOGS_DIR="${LOGS_BASE_DIR}/background-runs"
TIMESTAMP=$(date +"%Y%m%d_%H%M%S")
LOG_FILE="${PROJECT_ROOT}/${LOGS_DIR}/run-integration-tests_${TIMESTAMP}.log"

mkdir -p "${LOGS_DIR}"

# Clean all logs if requested
if [ "$CLEAN_LOGS" = true ]; then
    echo "🧹 Cleaning all logs..."
    rm -rf "${LOGS_BASE_DIR}"/*
    mkdir -p "${LOGS_DIR}"
    mkdir -p "${LOGS_BASE_DIR}/integration-tests"
fi

# Create the log file first
touch "${LOG_FILE}"

echo "🚀 Starting Spring AI Examples Integration Tests (Direct Mode)"
if [ -n "$TEST_FILTER" ]; then
    echo "🔍 Filter: $TEST_FILTER"
fi
echo "📝 Log file: ${LOG_FILE}"
echo "==============================================="

# Clean up old logs from previous runs (but not the current log file)
echo "🧹 Cleaning up old run logs..." | tee -a "${LOG_FILE}"
find "${LOGS_DIR}" -name "*.log" -not -name "$(basename "${LOG_FILE}")" -delete 2>/dev/null || true

# Port cleanup - kill any processes using port 8080 to prevent conflicts
echo "🔧 Cleaning up port 8080..." | tee -a "${LOG_FILE}"
if lsof -ti:8080 >/dev/null 2>&1; then
    echo "  Found processes using port 8080, terminating..." | tee -a "${LOG_FILE}"
    lsof -ti:8080 | xargs kill -9 2>/dev/null || true
    sleep 2  # Give processes time to cleanup
    echo "  Port 8080 cleanup completed" | tee -a "${LOG_FILE}"
else
    echo "  Port 8080 is free" | tee -a "${LOG_FILE}"
fi

# Find all JBang integration test scripts
if [ -n "$TEST_FILTER" ]; then
    # Filter scripts by the provided pattern
    jbang_scripts=($(find . -name "Run*.java" -path "*/integration-tests/*" | grep -i "$TEST_FILTER" | sort))
else
    # Find all scripts
    jbang_scripts=($(find . -name "Run*.java" -path "*/integration-tests/*" | sort))
fi

echo "Found ${#jbang_scripts[@]} JBang integration test scripts" | tee -a "${LOG_FILE}"
echo "" | tee -a "${LOG_FILE}"

passed=0
failed=0
failed_tests=()

for script in "${jbang_scripts[@]}"; do
    test_dir=$(dirname "${script}")
    module_dir=$(dirname "${test_dir}")
    module_name=$(basename "${module_dir}")
    parent_name=$(basename $(dirname "${module_dir}"))
    full_name="${parent_name}/${module_name}"
    
    # Create individual test log file
    individual_log="${LOGS_DIR}/test_${parent_name}_${module_name}_${TIMESTAMP}.log"
    
    echo "🔄 Testing ${full_name}..." | tee -a "${LOG_FILE}"
    echo "   Script: ${script}" | tee -a "${LOG_FILE}"
    echo "   Individual log: ${individual_log}" | tee -a "${LOG_FILE}"
    
    # Run JBang script with both main log and individual log
    if (cd "${module_dir}" && timeout 300s jbang "integration-tests/$(basename "${script}")") 2>&1 | tee "${individual_log}" >> "${LOG_FILE}"; then
        echo "✅ ${full_name} - PASSED" | tee -a "${LOG_FILE}"
        echo "   ✅ Individual log: ${individual_log}" | tee -a "${LOG_FILE}"
        ((passed++))
    else
        exit_code=$?
        if [ ${exit_code} -eq 124 ]; then
            echo "⏰ ${full_name} - TIMEOUT" | tee -a "${LOG_FILE}"
        else
            echo "❌ ${full_name} - FAILED (exit code: ${exit_code})" | tee -a "${LOG_FILE}"
        fi
        echo "   ❌ Individual log: ${individual_log}" | tee -a "${LOG_FILE}"
        ((failed++))
        failed_tests+=("${full_name}")
    fi
    
    # Clean up any hanging processes on port 8080 after each test
    if lsof -ti:8080 >/dev/null 2>&1; then
        echo "🧹 Cleaning up hanging processes on port 8080..." | tee -a "${LOG_FILE}"
        lsof -ti:8080 | xargs kill -9 2>/dev/null || true
        sleep 1
    fi
    
    echo "" | tee -a "${LOG_FILE}"
done

echo "===============================================" | tee -a "${LOG_FILE}"
echo "📊 Results:" | tee -a "${LOG_FILE}"
echo "  Total: $((passed + failed))" | tee -a "${LOG_FILE}"
echo "  Passed: ${passed}" | tee -a "${LOG_FILE}"
echo "  Failed: ${failed}" | tee -a "${LOG_FILE}"

if [ ${failed} -gt 0 ]; then
    echo "" | tee -a "${LOG_FILE}"
    echo "💥 Failed tests:" | tee -a "${LOG_FILE}"
    for test in "${failed_tests[@]}"; do
        echo "  - ${test}" | tee -a "${LOG_FILE}"
    done
fi

echo "" | tee -a "${LOG_FILE}"
echo "📝 Full log: ${LOG_FILE}"

# Exit with proper code
if [ ${failed} -gt 0 ]; then
    echo "❌ Testing completed with failures!"
    exit 1
else
    echo "✅ Testing completed!"
    exit 0
fi