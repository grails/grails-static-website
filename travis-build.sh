#!/bin/bash
set -e

git config --global user.name "$GIT_NAME"
git config --global user.email "$GIT_EMAIL"
git config --global credential.helper "store --file=~/.git-credentials"
echo "https://$GH_TOKEN:@github.com" > ~/.git-credentials

./gradlew main:runShadow

./gradlew guides:runShadow

if [[ $TRAVIS_BRANCH == 'master' && $TRAVIS_PULL_REQUEST == 'false' ]]; then

# Publish Main Site
	git clone https://${GH_TOKEN}@github.com/$TRAVIS_REPO_SLUG.git -b gh-pages gh-pages --single-branch > /dev/null
	cd gh-pages
	cp -r ../main/build/site/. ./
	git add *
	git commit -a -m "Updating main grails site for Travis build: https://travis-ci.org/$TRAVIS_REPO_SLUG/builds/$TRAVIS_BUILD_ID"
	git push origin HEAD
	cd ..
	rm -rf gh-pages

# Publish Guides site
    git clone https://${GH_TOKEN}@github.com/grails/grails-guides.git -b gh-pages gh-pages --single-branch > /dev/null
	cd gh-pages
	cp -r ../guides/build/site/. ./
	git add *
	git commit -a -m "Updating guides site for Travis build: https://travis-ci.org/$TRAVIS_REPO_SLUG/builds/$TRAVIS_BUILD_ID"
	git push origin HEAD
	cd ..
	rm -rf gh-pages

    if [[ -n $TRAVIS_TAG ]]; then
        ./gradlew grails-navigation-core:bintrayUpload
        ./gradlew grails-navigation:bintrayUpload
    fi

fi
