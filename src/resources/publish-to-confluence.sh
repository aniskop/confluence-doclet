#!/bin/bash

source_dir=$1
space_key=$2
parent_page_id=$3
basic_auth=$4
host_url=$5

function publish_page() {
    page_title=$1
    page_contents=$2
    parent_id=$3
    payload_template=$(echo "{\"type\":\"page\",\"title\": \"$page_title\",\"space\":{\"key\":\"$space_key\"},\"ancestors\":[{\"id\":\"$parent_id\"}],\"body\": {\"storage\": {\"value\": \"$page_contents\",\"representation\": \"wiki\"}}")

    echo "curl -X POST --basic -u $basic_auth -d "$payload_template" $host_url/rest/api/2/content"
}

# TODO: VALIDATE ALL PARAMS

# Files and directory structure
# source_dir
#   package_dir1
#     class_page1.wiki
#     class_page2.wiki
#     ...
#   package_dir2
#     ...
#   package_page1.wiki
#   package_page2.wiki
#   package_page3.wiki

echo "Source dir     : $source_dir"
echo "Space key      : $space_key"
echo "Parent page id : $parent_page_id"
echo "Host           : $host_url"
echo

# Publish package page first and then all its class pages
echo "== Publishing package pages =="
for package_page in $source_dir/*.wiki
do
    echo "$package_page --> $(basename $package_page)"
    publish_page "$(basename $package_page)" "$(cat $package_page)" "$parent_page_id"
done

echo
echo "== Publishing class pages =="
for package_dir in $source_dir/*/
do
    echo "$package_dir --> $(basename $package_dir)"
    #if [ -d ]; then
    #fi
done
