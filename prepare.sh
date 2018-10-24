#!/bin/sh

gpg --import keyring.gpg
git config --global user.email "representations@kerri.uy"
git config --global user.name "Travis CI"
git remote set-url origin git@github.com:pdacostaporto/representations.git
git fetch
git checkout -B master
git branch --set-upstream-to origin/master
eval "$(ssh-agent -s)"
chmod 600 deploy.pem
ssh-add deploy.pem
sed -i -e "s/\[Unreleased\]/\[$RELEASE_VERSION\]/" CHANGELOG.md
sed -i -e "s/\.\.\.HEAD$/\.\.\.$RELEASE_VERSION/" CHANGELOG.md
git add CHANGELOG.md
git commit -m "Changelog updated to version $RELEASE_VERSION"
mvn --batch-mode -Dtag=$RELEASE_VERSION release:prepare -DreleaseVersion=$RELEASE_VERSION -DdevelopmentVersion=$DEVELOPMENT_VERSION-SNAPSHOT --settings settings.xml
sed -i -e "/^\[$RELEASE_VERSION\]: https:.*/i \[Unreleased\]: https:\/\/github.com\/pdacostaporto\/representations\/compare\/$RELEASE_VERSION\.\.\.HEAD" CHANGELOG.md
git add CHANGELOG.md
git commit -m "Changelog updated for new development version"
git push
