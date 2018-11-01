#!/bin/sh

chmod 600 deploy.pem
chmod 600 secret.gpg
chmod 600 public.gpg
eval "$(ssh-agent -s)"
ssh-add deploy.pem
git config --global user.email "travis@travis-ci.org"
git config --global user.name "Travis CI"
git remote add github git@github.com:pdacostaporto/representations.git
git checkout -B master
sed -i -e "s/\[Unreleased\]/\[$RELEASE_VERSION\]/" CHANGELOG.md
sed -i -e "s/\.\.\.HEAD$/\.\.\.$RELEASE_VERSION/" CHANGELOG.md
git add CHANGELOG.md
git commit -m "Changelog updated to version $RELEASE_VERSION"
gpg --list-secret-keys
mvn --batch-mode -Dtag=$RELEASE_VERSION release:prepare -DreleaseVersion=$RELEASE_VERSION -DdevelopmentVersion=$DEVELOPMENT_VERSION-SNAPSHOT --settings settings.xml
sed -i -e "/^\[$RELEASE_VERSION\]: https:.*/i \[Unreleased\]: https:\/\/github.com\/pdacostaporto\/representations\/compare\/$RELEASE_VERSION\.\.\.HEAD" CHANGELOG.md
git add CHANGELOG.md
git commit -m "Changelog updated for new development version"
git push github master
