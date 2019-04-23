#!/usr/bin/env groovy

import io.issenn.jenkins.utils.utils

def call(String version='2.5.1', String method=null, Closure body={}) {
    String metarunner = 'rbenv'
    def utils = new utils()

    print "Setting up Ruby version ${version}!"

    def command = "command -v ${metarunner}"

    try {
        sh(returnStdout: true, script: command)
    } catch(Exception ex) {
        installRbenv(metarunner)
        installRbenv("ruby-build")
        installRbenv("readline")
        installRbenv("rbenv-gemset")
        installRbenv("rbenv-vars")
    }

    if (!fileExists("$HOME/.${metarunner}/versions/${version}/")) {
        utils.installVersion(metarunner, version,
            "--disable-install-doc --with-readline-dir=\$(brew --prefix readline)")
    }

    sh "rbenv version"

    /*
    withEnv(["PATH=$HOME/.${metarunner}/shims:$PATH"]) {
        sh "${metarunner} rehash && ${metarunner} local ${version}"
        body()
    }

     */
/*
    if (method == 'clean') {
        print "Removing Ruby ${version}!!!"
        withEnv(["PATH=$HOME/.${metarunner}/bin/:$PATH"]) {
            utils.deleteVersion(metarunner, version)
        }
    }

 */
}

def installRbenv(String metarunner) {
    println("Installing ${metarunner}")
    new utils().installMetarunnerOnMac(metarunner)
}

