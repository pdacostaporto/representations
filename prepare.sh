#!/bin/sh

git config --global user.email "representations@kerri.uy"
git config --global user.name "Travis CI"
git checkout -B master
eval "$(ssh-agent -s)"
chmod 600 deploy.pem
ssh-add deploy.pem
sed -i -e "s/\[Unreleased\]/\[$RELEASE_VERSION\]/" CHANGELOG.md
sed -i -e "s/\.\.\.HEAD$/\.\.\.$RELEASE_VERSION/" CHANGELOG.md
sed -n "/## \[$RELEASE_VERSION\]/,/## \[/{/## \[/b;p}" CHANGELOG.md
mvn --batch-mode -Dtag=$RELEASE_VERSION release:prepare -DreleaseVersion=$RELEASE_VERSION -DdevelopmentVersion=$DEVELOPMENT_VERSION-SNAPSHOT
