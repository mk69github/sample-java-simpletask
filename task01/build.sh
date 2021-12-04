#!/bin/bash
base_dir='./'
jar='simple-tasks.jar'
manifest='META-INF/MANIFEST.MF'

echo "$(date '+%Y-%m-%d %H:%M:%S') [INFO] Start build. output="${jar}

files=$(find ${base_dir} -type f -name '*.java')
for file in ${files[@]}; do
    javac -cp ${base_dir} ${file}
done

jar cvfm ${jar} ${manifest} ${base_dir}

echo "$(date '+%Y-%m-%d %H:%M:%S') [INFO] Finish build. output="${jar}
