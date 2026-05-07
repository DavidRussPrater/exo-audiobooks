#!/bin/bash

# Usage: ./rename.sh "com.new.package" "NewAppName"

NEW_PACKAGE=$1
NEW_APP_NAME=$2
OLD_PACKAGE="com.example.myapplication"
OLD_APP_NAME="SkeletonAndroidProject" # Match this to your rootProject.name in settings.gradle

if [ -z "$NEW_PACKAGE" ] || [ -z "$NEW_APP_NAME" ]; then
    echo "Usage: ./rename.sh <new_package> <new_app_name>"
    exit 1
fi

# 1. Replace Package Name in files
echo "Replacing package name in files..."
grep -rl "$OLD_PACKAGE" . --exclude="rename.sh" --exclude-dir=".git" --exclude-dir=".gradle" --exclude-dir="build" | xargs sed -i '' "s/$OLD_PACKAGE/$NEW_PACKAGE/g"

# 2. Replace Project Name in settings.gradle and strings.xml
echo "Replacing app name..."
sed -i '' "s/$OLD_APP_NAME/$NEW_APP_NAME/g" settings.gradle.kts
# Optionally replace app_name in strings.xml if you have one
# sed -i '' "s/My Application/$NEW_APP_NAME/g" app/src/main/res/values/strings.xml

# 3. Rename Directory Structure
echo "Renaming directory structure..."
IFS='.' read -ra OLD_PARTS <<< "$OLD_PACKAGE"
IFS='.' read -ra NEW_PARTS <<< "$NEW_PACKAGE"

OLD_PATH=$(echo "$OLD_PACKAGE" | tr '.' '/')
NEW_PATH=$(echo "$NEW_PACKAGE" | tr '.' '/')

# Function to move folders for src/main, src/test, and src/androidTest
rename_dirs() {
    local base_path=$1
    mkdir -p "$base_path/$(dirname $NEW_PATH)"
    mv "$base_path/$OLD_PATH" "$base_path/$NEW_PATH"
    # Clean up old empty directories if they exist
    find "$base_path/${OLD_PARTS[0]}" -type d -empty -delete 2>/dev/null
}

rename_dirs "app/src/main/java"
rename_dirs "app/src/test/java"
rename_dirs "app/src/androidTest/java"

echo "Done! Please Sync Project with Gradle Files in Android Studio."