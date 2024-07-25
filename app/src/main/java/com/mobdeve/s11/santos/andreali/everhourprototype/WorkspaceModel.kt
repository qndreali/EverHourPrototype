package com.mobdeve.s11.santos.andreali.everhourprototype

data class WorkspaceModel (
    var workspaceName: String? = null,
    var workspaceHrs: Int? = null,
    var workspaceUsers: ArrayList<UserModel>? = null,
    var workspaceProjects: ArrayList<ProjectModel>? = null
) {
}