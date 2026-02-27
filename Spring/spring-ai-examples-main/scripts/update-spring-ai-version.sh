#!/bin/bash

# Update Spring AI version across all pom.xml files
# Usage: ./scripts/update-spring-ai-version.sh [VERSION]
# Example: ./scripts/update-spring-ai-version.sh 1.0.1
# Example: ./scripts/update-spring-ai-version.sh 1.1.0-SNAPSHOT

set -e

# Get the version parameter or use default
VERSION="${1:-1.1.0-SNAPSHOT}"

# Validate version format
if [[ ! "$VERSION" =~ ^[0-9]+\.[0-9]+\.[0-9]+(-SNAPSHOT)?$ ]]; then
    echo "Error: Invalid version format. Expected: X.Y.Z or X.Y.Z-SNAPSHOT"
    echo "Example: 1.0.1 or 1.1.0-SNAPSHOT"
    exit 1
fi

echo "Updating Spring AI version to: $VERSION"

# Find script directory and project root
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"

# Change to project root
cd "${PROJECT_ROOT}"

# Create backup directory with timestamp
BACKUP_DIR="${PROJECT_ROOT}/.version-backups/$(date +%Y%m%d_%H%M%S)"
mkdir -p "${BACKUP_DIR}"

# Counter for updated files
UPDATED_COUNT=0
TOTAL_COUNT=0

# Find and process all pom.xml files
echo "Searching for pom.xml files..."
while IFS= read -r pom_file; do
    # Skip backup directories
    if [[ "$pom_file" == *".version-backups"* ]]; then
        continue
    fi
    
    TOTAL_COUNT=$((TOTAL_COUNT + 1))
    updated_file=false
    
    # Check if this pom file contains spring-ai.version property
    if grep -q "<spring-ai.version>" "$pom_file"; then
        # Create backup
        relative_path="${pom_file#./}"
        backup_file="${BACKUP_DIR}/${relative_path}"
        mkdir -p "$(dirname "$backup_file")"
        cp "$pom_file" "$backup_file"
        
        # Update the version property
        sed -i "s|<spring-ai.version>.*</spring-ai.version>|<spring-ai.version>${VERSION}</spring-ai.version>|g" "$pom_file"
        updated_file=true
    fi
    
    # Also check for direct spring-ai-bom version in dependencyManagement
    if grep -q "spring-ai-bom" "$pom_file"; then
        if ! $updated_file; then
            # Create backup if not already done
            relative_path="${pom_file#./}"
            backup_file="${BACKUP_DIR}/${relative_path}"
            mkdir -p "$(dirname "$backup_file")"
            cp "$pom_file" "$backup_file"
        fi
        
        # Update the BOM version using sed with pattern for multiline
        sed -i '/<artifactId>spring-ai-bom<\/artifactId>/{n;s|<version>.*</version>|<version>'"${VERSION}"'</version>|}' "$pom_file"
        
        updated_file=true
    fi
    
    if $updated_file; then
        echo "  ✓ Updated: ${pom_file#./}"
        UPDATED_COUNT=$((UPDATED_COUNT + 1))
    fi
done < <(find . -name "pom.xml" -type f)

echo ""
echo "Summary:"
echo "  Total pom.xml files found: $TOTAL_COUNT"
echo "  Files updated: $UPDATED_COUNT"
echo "  Backup location: $BACKUP_DIR"
echo ""

# Verify the update
echo "Verification - checking a sample file:"
sample_file="./misc/openai-streaming-response/pom.xml"
if [ -f "$sample_file" ]; then
    grep "<spring-ai.version>" "$sample_file" || echo "Sample file not found or doesn't contain spring-ai.version"
fi

echo ""
echo "✅ Spring AI version update complete!"
echo ""
echo "To restore from backup, run:"
echo "  ./scripts/restore-spring-ai-version.sh $BACKUP_DIR"