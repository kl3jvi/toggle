package com.toggle.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.toggle.data.model.LoginResponseItem
import java.lang.reflect.Type


object LoginParser {
    fun parseLoginResponse(response: String): List<LoginResponseItem>? {
//        [[{"Flag":"Wrong Password "}],{"fieldCount":0,"affectedRows":0,"insertId":0,"serverStatus":34,"warningCount":1,"message":"","protocol41":true,"changedRows":0}]
//        [[{"flag":"success","userid":290804,"userfname":"amit","userlname":"rajput","email":"amit123456@gmail.com","TuserID":359}]]
        val jsonParsable = response.replace("[[", "[").replace("}],{", "},{").replace("]]", "]")

        val type: Type = Types.newParameterizedType(List::class.java, LoginResponseItem::class.java)
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<LoginResponseItem>> = moshi.adapter(type)
        return adapter.fromJson(jsonParsable)
    }
}



