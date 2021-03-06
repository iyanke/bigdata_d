package patches.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a vcsRoot with id = 'Eclipse_2'
in the root project, and delete the patch script.
*/
create(DslContext.projectId, GitVcsRoot({
    id("Eclipse_2")
    name = "eclipse"
    url = "git@github.com:iyanke/java_eclipse.git"
    branch = "master"
    branchSpec = "+:*"
    authMethod = uploadedKey {
        userName = "git"
        uploadedKey = "git1"
    }
    param("secure:password", "")
}))

