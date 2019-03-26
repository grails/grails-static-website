DATE=`date +'%b %d, %Y'`
echo "  -" >> main/src/main/resources/releases.yml
echo "    version: $1" >> main/src/main/resources/releases.yml
echo "    publicationDate: $DATE" >> main/src/main/resources/releases.yml
