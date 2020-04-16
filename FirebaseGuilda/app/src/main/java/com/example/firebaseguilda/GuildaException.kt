package com.example.firebaseguilda

import java.lang.Exception

class GuildaException(msg: String): Exception() {

    private val mMsg = msg

    override fun printStackTrace() {
        print(mMsg)
    }

}