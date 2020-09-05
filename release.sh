DATE=`date +'%b %d, %Y'`
echo "    -" >> conf/releases.yml
echo "      version: $1" >> conf/releases.yml
echo "      publicationDate: $DATE" >> conf/releases.yml
