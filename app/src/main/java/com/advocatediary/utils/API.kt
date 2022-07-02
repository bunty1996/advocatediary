package com.advocatediary.utils

import com.advocatediary.model.addCases.AddCaseExample
import com.advocatediary.model.addClient.AddClientExample
import com.advocatediary.model.allClients.AllClientExample
import com.advocatediary.model.caseDetail.CaseDetailExample
import com.advocatediary.model.casePurposes.CasePurposesExample
import com.advocatediary.model.caseType.CaseTypeExample
import com.advocatediary.model.caseUpdate.CaseUpdateExample
import com.advocatediary.model.changePassword.ChangePasswordExample
import com.advocatediary.model.clientDeatail.ClientDetailExample
import com.advocatediary.model.deleteCase.DeleteCaseExample
import com.advocatediary.model.deleteClients.DeleteClientsExample
import com.advocatediary.model.editClient.EditClientExample
import com.advocatediary.model.getJudges.GetJudgeExample
import com.advocatediary.model.help.HelpExample
import com.advocatediary.model.home.HomeExample
import com.advocatediary.model.login.LoginExample
import com.advocatediary.model.myCases.MyCasesExample
import com.advocatediary.model.myClient.MyClientExample
import com.advocatediary.model.particularClient.ParticularClientExample
import com.advocatediary.model.register.RegisterExample
import com.advocatediary.model.resetPassword.ResetPasswordExample
import com.advocatediary.model.state.StateExample
import com.advocatediary.model.stateDistricts.StateDistrictExample
import com.advocatediary.model.userExists.UserExistsExample
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface API {
    @FormUrlEncoded
    @POST("login")
    fun getCurrentWeatherData(
        @Field("phone") phone: String, @Field("password") password: String, @Field("device_type") device_type: String, @Field(
            "device_token"
        ) device_token: String
    ): Call<LoginExample>


    @GET("getStatesAndDistricts")
    fun callGetStateCodeAPI(): Call<StateExample>


    @FormUrlEncoded
    @POST("checkUserExistByPhone")
    fun userExixtsAPI(@Field("phone") phone: String): Call<UserExistsExample>

    @FormUrlEncoded
    @POST("case-schedule")
    fun getHomeSectionData(@Field("user_id") userId: String): Call<HomeExample>


    @FormUrlEncoded
    @POST("getCasesByAdvocateId")
    fun getMyCasesData(
        @Field("user_id") user_id: String, @Field("page") page: Int
        , @Field("search") search: String
    ): Call<MyCasesExample>


    @FormUrlEncoded
    @POST("my-clients")
    fun getAllMyClients(
        @Field("advocate_id") advocate_id: String, @Field("page") page: Int
        , @Field("search") search: String
    ): Call<MyClientExample>


    @FormUrlEncoded
    @POST("change_password")
    fun changePasswordAPI(
        @Field("user_id") user_id: String,
        @Field("old_password") old_password: String,
        @Field("new_password") new_password: String
    ): Call<ChangePasswordExample>


    @FormUrlEncoded
    @POST("add-client")
    fun addClientAPI(
        @Field("user_id") user_id: String,
        @Field("first_name") first_name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("gender") gender: String,

        @Field("address") address: String,
        @Field("full_name") full_name: String
    ): Call<AddClientExample>


    @FormUrlEncoded
    @POST("reg")
    fun registerAPI(
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,

        @Field("full_name") full_name: String,
        @Field("phone") phone: String,

        @Field("email") email: String,
        @Field("password") password: String,

        @Field("address") address: String,
        @Field("office_address") office_address: String,

        @Field("device_type") device_type: String,
        @Field("device_token") device_token: String,
        @Field("state_id") state_id: String,
        @Field("district_id") district_id: String
    ): Call<RegisterExample>


    @FormUrlEncoded
    @POST("resetPassword")
    fun resetPassword(@Field("phone") phone: String, @Field("password") password: String): Call<ResetPasswordExample>


    @GET("getStatesDistrictsCourts")
    fun getStateDistrictAPI(): Call<StateDistrictExample>

    @GET("judges")
    fun getAllJudgesAPI(): Call<GetJudgeExample>


    @GET("casetype-list")
    fun getAllCasesTypes(): Call<CaseTypeExample>


    @GET("case-status")
    fun getAllCasePurposes(): Call<CasePurposesExample>


    @POST("add-case")
    fun addCaseAPI(@Body jsonObject: JsonObject): Call<AddCaseExample>

    // for update case
    @POST("update-case")
    fun updateCase(@Body jsonObject: JsonObject): Call<CaseUpdateExample>


    @FormUrlEncoded
    @POST("client-detail")
    fun clientDetailAPI(@Field("advocate_id") advocate_id: String, @Field("client_id") client_id: String): Call<ClientDetailExample>


    @FormUrlEncoded
    @POST("perticular-client")
    fun getAllCasesClient(@Field("user_id") user_id:String,@Field("client_id") client_id:String,
                         @Field("page") page:Int, @Field("search") search:String):Call<ParticularClientExample>






    @FormUrlEncoded
    @POST("case-detail")
    fun caseDetailAPI(@Field("user_id") user_id:String,@Field("case_id") case_id:String,@Field("page") page:Int):Call<CaseDetailExample>


    @FormUrlEncoded
    @POST("my-clients")
    fun getAllMyClientsAPI(@Field("advocate_id") advocate_id:String,
                           @Field("search") search:String,
                           @Field("page") page:Int):Call<AllClientExample>



    @FormUrlEncoded
    @POST("update-client")
    fun updateClientAPI(@Field("client_id") client_id:String,
                        @Field("user_id") user_id:String,
                        @Field("first_name") first_name:String,
                        @Field("email") email:String,
                        @Field("phone") phone:String,
                        @Field("gender") gender:String,
                        @Field("address") address:String,
                        @Field("full_name") full_name:String,
                        @Field("image") image:String
                        ):Call<EditClientExample>




    @FormUrlEncoded
    @POST("delete-clients")
    fun deleteClientAPI(@Field("client_id") client_id:String,@Field("user_id") user_id:String):Call<DeleteClientsExample>




    @FormUrlEncoded
    @POST("delete-cases")
    fun deleteCaseAPI(@Field("case_id") case_id:String,@Field("user_id") user_id:String):Call<DeleteCaseExample>



    // for update case
    @POST("update-case")
    fun editCase(@Body jsonObject: JsonObject): Call<CaseUpdateExample>


    @FormUrlEncoded
    @POST("help")
    fun helpAPI(@Field("user_id") user_id:String,@Field("message") message:String):Call<HelpExample>
}

