#!/bin/bash
NEW_PROJECT_NAME=$1
NEW_NAMESPACE=$(echo $NEW_PROJECT_NAME | sed "s/-//g")      #removes -
NEW_DATABASE_NAME=$(echo $NEW_PROJECT_NAME | sed "s/-/_/g") #changes - for _

 replace() {
  grep -rl --exclude-dir={build,out,.idea,.git} $1 | xargs sed -i "s/$1/$2/g"
}

 # Docker, CI, gradle, ...
replace kotlin-spring-sample $NEW_PROJECT_NAME
# Namespaces
replace kotlinspringsample $NEW_NAMESPACE
# Database
replace kotlin_spring_sample $NEW_DATABASE_NAME
# Folders
git mv src/main/kotlin/br/com/creditas/kotlinspringsample src/main/kotlin/br/com/creditas/$NEW_NAMESPACE
git mv src/test/kotlin/br/com/creditas/kotlinspringsample src/test/kotlin/br/com/creditas/$NEW_NAMESPACE

 #Remove this file
echo "Project renamed! Removing this script..."
git rm -f rename-project.sh
#rm rename-project.sh
echo "Done!"
