package com.mobdeve.s11.santos.andreali.everhourprototype

import android.content.Context
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "workspace_db"

        // Workspace table
        const val WORKSPACE_TABLE = "workspaces"
        const val WORKSPACE_ID = "workspace_id"
        const val WORKSPACE_NAME = "workspace_name"
        const val WORKSPACE_HOURS = "workspace_hours"
        const val WORKSPACE_PROJECTS = "workspace_projects"

        // Members table
        const val MEMBERS_TABLE = "members"
        const val MEMBER_ID = "member_id"
        const val MEMBER_NAME = "member_name"
        const val MEMBER_ROLE = "member_role"

        private const val CREATE_TABLE_WORKSPACES = ("CREATE TABLE $WORKSPACE_TABLE (" +
                "$WORKSPACE_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$WORKSPACE_NAME TEXT, " +
                "$WORKSPACE_HOURS INTEGER, " +
                "$WORKSPACE_PROJECTS INTEGER)")

        private const val CREATE_TABLE_MEMBERS = ("CREATE TABLE $MEMBERS_TABLE (" +
                "$WORKSPACE_ID INTEGER, " +
                "$MEMBER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$MEMBER_NAME TEXT, " +
                "$MEMBER_ROLE TEXT, " +
                "FOREIGN KEY ($WORKSPACE_ID) REFERENCES $WORKSPACE_TABLE($WORKSPACE_ID))")
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_WORKSPACES)
        db.execSQL(CREATE_TABLE_MEMBERS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $WORKSPACE_TABLE")
        db.execSQL("DROP TABLE IF EXISTS $MEMBERS_TABLE")
        onCreate(db)
    }

    fun addMember(workspaceId: Int, name: String, role: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(WORKSPACE_ID, workspaceId)
            put(MEMBER_NAME, name)
            put(MEMBER_ROLE, role)
        }
        db.insert(MEMBERS_TABLE, null, values)
    }

    fun getMembers(workspaceId: Int): List<Member> {
        val db = readableDatabase
        val members = mutableListOf<Member>()
        val cursor = db.query(
            MEMBERS_TABLE,
            null,
            "$WORKSPACE_ID = ?",
            arrayOf(workspaceId.toString()),
            null,
            null,
            null
        )
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(MEMBER_ID))
                val name = cursor.getString(cursor.getColumnIndex(MEMBER_NAME))
                val role = cursor.getString(cursor.getColumnIndex(MEMBER_ROLE))
                members.add(Member(id, workspaceId, name, role))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return members
    }
}
