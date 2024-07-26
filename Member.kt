package com.mobdeve.s11.santos.andreali.everhourprototype

import java.io.Serializable

class Member(
    var workspaceId: Int,
    var memberId: Int,
    var name: String,
    var role: String
) : Serializable {

    companion object {
        private const val DEFAULT_ID = -1
    }

    // Constructor for a new member without an ID (will use default ID)
    constructor(workspaceId: Int, name: String, role: String) : this(workspaceId, DEFAULT_ID, name, role)

    // Default constructor
    constructor() : this(DEFAULT_ID, DEFAULT_ID, "Unnamed", "Unknown")
}
