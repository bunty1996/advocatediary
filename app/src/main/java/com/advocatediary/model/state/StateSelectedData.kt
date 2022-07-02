package com.advocatediary.model.state

class StateSelectedData
{
    private var id:String?=null
    private var name:String?=null
    private var checkValue:String?=null

    fun getId(): String?
    {
        return id
    }

    fun setId(id:String)
    {
        this.id=id
    }

    fun getName(): String?
    {
        return name
    }

    fun setName(name:String)
    {
        this.name=name
    }


    fun getCheckValue(): String?
    {
        return checkValue
    }

    fun setChekValue(checkValue:String)
    {
        this.checkValue=checkValue
    }
}