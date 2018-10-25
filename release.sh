#!/bin/sh

chmod 600 deploy.pem
chmod 600 secret.gpg
chmod 600 public.gpg
eval "$(ssh-agent -s)"
ssh-add deploy.pem
git config --global user.email "travis@travis-ci.org"
git config --global user.name "Travis CI"
git remote add github git@github.com:pdacostaporto/representations.git
echo "scm.url=scm:git:ssh://git@github.com/$TRAVIS_REPO_SLUG.git
scm.tag=$TRAVIS_TAG" > release.properties
mvn --batch-mode release:perform --settings settings.xml
jq -n \
  --arg body "`sed -n "/## \[$TRAVIS_TAG\]/,/## \[/{/## \[/b;p}" CHANGELOG.md`" \
  --arg name "$TRAVIS_TAG" \
  --arg tag_name "$TRAVIS_TAG" \
  --arg target_commitish "$TRAVIS_COMMIT" \
  '{
    body: $body,
    name: $name,
    tag_name: $tag_name,
    target_commitish: $target_commitish,
    draft: false,
    prerelease: false
  }' > release.json
curl -H "Authorization: token $GITHUB_TOKEN" --data @release.json "https://api.github.com/repos/$TRAVIS_REPO_SLUG/releases"
