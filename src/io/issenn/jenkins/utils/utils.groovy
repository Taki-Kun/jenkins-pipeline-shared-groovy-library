#!/usr/bin/groovy

package io.issenn.jenkins.utils

import com.cloudbees.groovy.cps.NonCPS

@NonCPS
def installHomebrew() {
    sh """
    /usr/bin/ruby -e "\$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
    """
}

@NonCPS
def installMetarunnerOnMac(String metarunner){
    sh(script: "brew install ${metarunner}")
}

@NonCPS
def installMetarunner(String metarunner){
    sh """ 
  git clone https://github.com/${metarunner}/${metarunner}.git ${JENKINS_HOME}/.${metarunner}
  cd ${JENKINS_HOME}/.${metarunner}
  src/configure --without-ssl && make -C src
  """
}

@NonCPS
def installVersion(String metarunner, String version) {
    sh "${metarunner} install ${version}"
}

@NonCPS
def deleteVersion(String metarunner, String version) {
    File directory = new File("${JENKINS_HOME}/.${metarunner}/versions/${version}")

    directory.deleteDir()
}

@NonCPS
def purgeAllVersions(String metarunner) {
    File directory = new File("${JENKINS_HOME}/.${metarunner}/versions/")

    directory.listFiles().each{
        it.deleteDir()
    }
}
