import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.golang
import jetbrains.buildServer.configs.kotlin.buildFeatures.testsSplit
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.2"

project {

    vcsRoot(HttpsGithubComIyankeBigdata)
    vcsRoot(Eclipse_1)

    buildType(Er)
    buildType(Eclipse)
}

object Eclipse : BuildType({
    name = "eclipse"

    vcs {
        root(Eclipse_1)
    }

    steps {
        maven {
            goals = "clean test"
            pomLocation = "java_eclipse/pom.xml"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
    }

    features {
        testsSplit {
            numberOfParts = 2
        }
        golang {
            testFormat = "json"
        }
    }
})

object Er : BuildType({
    name = "er"

    vcs {
        root(HttpsGithubComIyankeBigdata)
    }

    triggers {
        vcs {
            branchFilter = ""
        }
    }
})

object Eclipse_1 : GitVcsRoot({
    id("Eclipse")
    name = "eclipse"
    url = "git@github.com:iyanke/java_eclipse.git"
    branch = "master"
    branchSpec = "+:*"
    authMethod = uploadedKey {
        userName = "git"
        uploadedKey = "git1"
    }
    param("secure:password", "")
})

object HttpsGithubComIyankeBigdata : GitVcsRoot({
    name = "https://github.com/iyanke/bigdata"
    url = "https://github.com/iyanke/bigdata"
    branch = "refs/heads/master"
    branchSpec = "+:refs/heads/*"
    authMethod = password {
        userName = "iyanke"
        password = "credentialsJSON:7417b252-61f9-4bbc-a5c6-2508febeb65a"
    }
})
