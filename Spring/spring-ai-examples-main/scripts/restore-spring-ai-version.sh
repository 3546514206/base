#!/bin/bash

# Restore Spring AI version from backup
# Usage: ./scripts/restore-spring-ai-version.sh [BACKUP_DIR]

set -e

# Get the backup directory parameter
BACKUP_DIR="${1}"

if [ -z "$BACKUP_DIR" ]; then
    echo "Error: Backup directory required"
    echo "Usage: ./scripts/restore-spring-ai-version.sh BACKUP_DIR"
    echo ""
    echo "Available backups:"
    ls -la .version-backups/ 2>/dev/null || echo "  No backups found"
    exit 1
fi

if [ ! -d "$BACKUP_DIR" ]; then
    echo "Error: Backup directory not found: $BACKUP_DIR"
    exit 1
fi

echo "Restoring Spring AI versions from: $BACKUP_DIR"

# Find script directory and project root
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"

# Change to project root
cd "${PROJECT_ROOT}"

# Counter for restored files
RESTORED_COUNT=0

# Restore all backed up pom.xml files
echo "Restoring pom.xml files..."
while IFS= read -r backup_file; do
    # Calculate the original path
    relative_path="${backup_file#${BACKUP_DIR}/}"
    original_file="./${relative_path}"
    
    if [ -f "$original_file" ]; then
        cp "$backup_file" "$original_file"
        echo "  ✓ Restored: $relative_path"
        RESTORED_COUNT=$((RESTORED_COUNT + 1))
    else
        echo "  ⚠ Original file not found, skipping: $relative_path"
    fi
done < <(find "$BACKUP_DIR" -name "pom.xml" -type f)

echo ""
echo "Summary:"
echo "  Files restored: $RESTORED_COUNT"
echo ""

# Verify the restore
echo "Verification - checking a sample file:"
sample_file="./misc/openai-streaming-response/pom.xml"
if [ -f "$sample_file" ]; then
    grep "<spring-ai.version>" "$sample_file" || echo "Sample file not found or doesn't contain spring-ai.version"
fi

echo ""
echo "✅ Spring AI version restore complete!"