#!/bin/bash

apt-get update

echo "Installing JDK"
apt-get install -y openjdk-8-jdk

echo "Installing Maven"
apt-get install -y maven

echo "Installing GPG"
apt-get install -y gpg

echo "<settings>
  <servers>
    <server>
      <id>ossrh</id>
      <username>your-ossrh-jira-username</username>
      <password>your-ossrh-jira-password</password>
    </server>
  </servers>
  <profiles>
    <profile>
      <id>ossrh</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <properties>
        <gpg.passphrase>your-key-passphrase</gpg.passphrase>
      </properties>
    </profile>
  </profiles>
</settings>" > /home/vagrant/.m2/settings.xml
chown vagrant:vagrant /home/vagrant/.m2/settings.xml
chmod 600 /home/vagrant/.m2/settings.xml
