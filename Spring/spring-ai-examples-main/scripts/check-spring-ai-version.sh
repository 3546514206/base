#!/bin/bash

# Check Spring AI versions across all pom.xml files
# Usage: ./scripts/check-spring-ai-version.sh

set -e

echo "Checking Spring AI versions in all pom.xml files..."
echo ""

# Find script directory and project root
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"

# Change to project root
cd "${PROJECT_ROOT}"

# Track versions found
declare -A VERSION_COUNT
TOTAL_WITH_VERSION=0
TOTAL_WITHOUT_VERSION=0

# Find and check all pom.xml files (excluding backups)
echo "Files with spring-ai.version property:"
echo "----------------------------------------"
while IFS= read -r pom_file; do
    if grep -q "<spring-ai.version>" "$pom_file"; then
        version=$(grep "<spring-ai.version>" "$pom_file" | sed 's/.*<spring-ai.version>\(.*\)<\/spring-ai.version>.*/\1/')
        relative_path="${pom_file#./}"
        echo "  $version - $relative_path"
        
        # Count this version
        if [ -n "$version" ]; then
            VERSION_COUNT["$version"]=$((${VERSION_COUNT["$version"]:-0} + 1))
            TOTAL_WITH_VERSION=$((TOTAL_WITH_VERSION + 1))
        fi
    else
        TOTAL_WITHOUT_VERSION=$((TOTAL_WITHOUT_VERSION + 1))
    fi
done < <(find . -name "pom.xml" -type f -not -path "./.version-backups/*" | sort)

echo ""
echo "Version Summary:"
echo "----------------"
for version in "${!VERSION_COUNT[@]}"; do
    echo "  $version: ${VERSION_COUNT[$version]} files"
done

echo ""
echo "Statistics:"
echo "-----------"
echo "  Total pom.xml files: $((TOTAL_WITH_VERSION + TOTAL_WITHOUT_VERSION))"
echo "  Files with spring-ai.version: $TOTAL_WITH_VERSION"
echo "  Files without spring-ai.version: $TOTAL_WITHOUT_VERSION"

# Check for version inconsistencies
if [ ${#VERSION_COUNT[@]} -gt 1 ]; then
    echo ""
    echo "⚠️  WARNING: Multiple versions detected! Consider running:"
    echo "    ./scripts/update-spring-ai-version.sh <desired-version>"
else
    echo ""
    echo "✅ All files use the same Spring AI version"
fi