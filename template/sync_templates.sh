#!/usr/bin/env bash
find . -mindepth 1 -maxdepth 1 -type d | while read line; do
cp -R "$line" /Applications/Android\ Studio.app/Contents/plugins/android/lib/templates/other
done
