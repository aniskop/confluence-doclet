#!/bin/bash

source_dir=$1
space_key=$2
root_page_id=$3
basic_auth=$4
host_url=$5

function extract_http_code() {
    http_code=$(echo $1 | grep -oE "HTTP/[1|2]\.[0-9]\s[0-9]{3}" | grep -oE "[0-9]{3}")
    echo $http_code
}

function extract_page_id() {
    echo ""
}

function read_file_as_json_value() {
    contents=""
    buffer=""
    while IFS= read -r line; do
        buffer="$line\\n"
        buffer=$(echo "$buffer" | sed "s/\"/\\\"/g")
        contents="$contents$buffer"
    done < "$1"
    echo $contents
}

function publish_page() {
    page_title=$1
    page_contents="$2"
    parent_id=$3
    payload_template=$(echo "{\"type\":\"page\",\"title\": \"$page_title\",\"space\":{\"key\":\"$space_key\"},\"ancestors\":[{\"id\":\"$parent_id\"}],\"body\": {\"storage\": {\"value\": \"$page_contents\",\"representation\": \"wiki\"}}}")
    http_response=$(curl -is -X POST -H "Content-Type: application/json" --basic -u "$basic_auth" -d "$payload_template" "$host_url/rest/api/content")

    published_http_code=$(extract_http_code "$http_response")
    
    if [ "$published_http_code" == "200" ]; then
        published_page_id=$(extract_page_id "$http_response")
    else
        published_page_id=""
    fi
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
echo "Root page id   : $root_page_id"
echo "Host           : $host_url"
echo

published_page_id=""
published_http_code=""

# Publish package page first and then all its class pages
for package_page in $source_dir/*.wiki
do
    echo "Publishing package page $package_page ..."
    package_name=$(basename $package_page)
    package_name=${package_name%.*}

    page_contents=$(read_file_as_json_value "$package_page")

    publish_page "$package_name" "$page_contents" "$root_page_id"
    echo "HTTP code=$published_http_code, page id=$published_page_id"

    if [ "$published_http_code" == "200" ]; then
        # Remember package page id because published_page_id is overwritten after every call
        package_page_id=$published_page_id
    
        for class_page in $source_dir/$package_name/*.wiki
        do
            echo "  Publishing class page $class_page ..."
            class_name=$(basename $class_page)
            class_name=${class_name%.*}
            publish_page "$class_name" "$(cat $class_page)" "$package_page_id"
        done
    fi
done

