package com.advocatediary.model.stateDistricts

class StateCheckData {
  private  lateinit var id:String
    private lateinit var name:String
    private lateinit var checkValue:String

    fun getId():String?
    {
        return id
    }
    fun getName():String?
    {
        return name
    }
    fun getCheckValue():String?
    {
        return checkValue
    }

    fun setId(id:String)
    {
        this.id=id
    }

    fun setName(name:String)
    {
        this.name=name
    }

    fun setCheckValue(checkValue:String)
    {
        this.checkValue=checkValue
    }
}