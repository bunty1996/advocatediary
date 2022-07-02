package com.advocatediary.utils

import android.util.Log
import com.advocatediary.handler.*
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
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

class WebServices() {


    val UTF8 = Charset.forName("UTF-8")

// for live data
/*public static final String base_url = "https://westhave.com/acd/api/";
    public static final String FLAG_URL = "http://westhave.com/acd/public/flags/";*/


    private lateinit var api: API

    // for Local data
    fun create() {
        //      mInstance =
        retrofit = Retrofit.Builder()
            //.baseUrl("https://test2.ebookbazaar.com/api/")
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        api = retrofit.create(API::class.java)
        //return retrofit.create(API::class.java)
    }

    companion object Factory1 {

        private lateinit var mInstance: WebServices

        private lateinit var retrofit: Retrofit

        val base_url = "https://iamwhiz.com/acd/api/"
        val FLAG_URL = "http://iamwhiz.com/acd/public/flags/"
        internal var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        fun getInstance(): WebServices? {
            mInstance = WebServices()
            return mInstance
        }
    }

    fun apiCreate() {
        api = retrofit.create(API::class.java)
    }


    fun loginCallMethod(phone: String,
        password: String,
        deviceType: String,
        deviceToken: String,
        loginHandler: LoginHandler
    ) {


        /*  api = Retrofit.Builder()
              .baseUrl(base_url)
              .addConverterFactory(GsonConverterFactory.create())
              .client(okHttpClient)
              .build().create(API::class.java)*/

        apiCreate()
        api.getCurrentWeatherData(phone, password, deviceType, deviceToken)?.enqueue(object : Callback<LoginExample> {
            override fun onResponse(call: Call<LoginExample>, response: Response<LoginExample>) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!
                    loginHandler.onSuccess(weatherResponse)
                    //  Log.e("responseee==", weatherResponse.message)

                }
            }

            override fun onFailure(call: Call<LoginExample>, t: Throwable) {
                //   weatherData!!.text = t.message
                Log.e("error", t.message)
                loginHandler.onError(t.message.toString())
            }
        })
    }

    // For get state and district
    fun getStateListMethod(handler: StateHandler) {
        apiCreate()
        api.callGetStateCodeAPI().enqueue(object : StateExample(), Callback<StateExample> {

            override fun onResponse(call: Call<StateExample>, response: Response<StateExample>) {
                handler.onSuccess(response.body())
            }

            override fun onFailure(call: Call<StateExample>, t: Throwable) {
                handler.onError(t.message.toString())
            }
        })
    }

    fun checkUserExists(phone: String, handler: UserExistsHandler) {
        apiCreate()

        api.userExixtsAPI(phone).enqueue(object : Callback<UserExistsExample> {
            override fun onFailure(call: Call<UserExistsExample>, t: Throwable) {
                handler.onError(t.message.toString()!!)
            }

            override fun onResponse(call: Call<UserExistsExample>, response: Response<UserExistsExample>) {
                handler.onSuccess(response.body()!!)
            }
        })
    }

    // for home screen
    fun getHomeSectionMethod(userId: String, homeHandler: HomeHandler) {
        apiCreate()
        api.getHomeSectionData(userId).enqueue(object : Callback<HomeExample> {

            override fun onResponse(call: Call<HomeExample>, response: Response<HomeExample>) {
                homeHandler.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<HomeExample>, t: Throwable) {
                homeHandler.onError(t.message.toString())
            }
        })
    }

    // For get all my cases
    fun getMyCasesMethod(userId: String, page: Int, search: String, casesHandler: MyCasesHandler) {

        try {
            // for remove all previous request
            okHttpClient.dispatcher().cancelAll()
        } catch (e: Exception) {

        }
        apiCreate()
        api.getMyCasesData(userId, page, search).enqueue(object : Callback<MyCasesExample> {

            override fun onResponse(call: Call<MyCasesExample>, response: Response<MyCasesExample>) {
                casesHandler.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<MyCasesExample>, t: Throwable) {
                casesHandler.onError(t.message.toString())
            }
        })
    }


    // for get all My Clients
    fun getAllMyClientsMethod(userId: String, page: Int, search: String, handler: MyClientHandler) {
        try {
            // for remove all previous request
            okHttpClient.dispatcher().cancelAll()
        } catch (e: Exception) {

        }

        apiCreate()

        api.getAllMyClients(userId, page, search).enqueue(object : Callback<MyClientExample> {

            override fun onResponse(call: Call<MyClientExample>, response: Response<MyClientExample>) {
                handler.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<MyClientExample>, t: Throwable) {
                handler.onError(t.message.toString())
            }
        })
    }

    // for change password
    fun changePasswordMethod(userId: String, oldPassword: String, newPassword: String, handler: ChangePasswordHandler) {
        apiCreate()
        api.changePasswordAPI(userId, oldPassword, newPassword).enqueue(object : Callback<ChangePasswordExample> {
            override fun onResponse(call: Call<ChangePasswordExample>, response: Response<ChangePasswordExample>) {
                handler.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<ChangePasswordExample>, t: Throwable) {
                handler.onError(t.message.toString())
            }
        })
    }


    // for add Client
    fun addClientMethod(
        userId: String,
        fullName: String,
        pahone: String,
        email: String,
        address: String, gender: String,
        handler: AddClientHandler
    ) {
        apiCreate()
        api.addClientAPI(userId, fullName, email, pahone, gender, address, fullName)
            .enqueue(object : Callback<AddClientExample> {

                override fun onResponse(call: Call<AddClientExample>, response: Response<AddClientExample>) {
                    handler.onSuccess(response.body()!!)
                }

                override fun onFailure(call: Call<AddClientExample>, t: Throwable) {
                    handler.onError(t.message.toString())
                }
            })
    }


    // for Regitser
    fun registerMethod(
        firstName: String,
        last_name: String,
        full_name: String,
        phone: String,
        email: String

        , password: String, address: String,

        office_address: String, device_type: String, device_token: String, state_id: String
        , district_id: String, registerHandler: RegisterHandler
    ) {
        apiCreate()
        api.registerAPI(
            firstName, last_name, full_name, phone, email, password, address, office_address,
            device_type, device_token, state_id, district_id
        ).enqueue(object : Callback<RegisterExample> {

            override fun onResponse(call: Call<RegisterExample>, response: Response<RegisterExample>) {
                registerHandler.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<RegisterExample>, t: Throwable) {
                registerHandler.onError(t.message.toString())
            }
        })
    }


    // for reset password
    fun resetPassword(phone: String, password: String, resetPasswordHandler: ResetPasswordHandler) {
        apiCreate()
        api.resetPassword(phone, password).enqueue(object : Callback<ResetPasswordExample> {
            override fun onResponse(call: Call<ResetPasswordExample>, response: Response<ResetPasswordExample>) {
                resetPasswordHandler.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<ResetPasswordExample>, t: Throwable) {
                resetPasswordHandler.onError(t.message.toString())
            }

        })
    }


    fun getStateDistrictMethod(stateHandler: StateDistrictHandler) {
        apiCreate()
        api.getStateDistrictAPI().enqueue(object : Callback<StateDistrictExample> {
            override fun onFailure(call: Call<StateDistrictExample>, t: Throwable) {
                stateHandler.onError(t.message!!)
            }

            override fun onResponse(call: Call<StateDistrictExample>, response: Response<StateDistrictExample>) {
                stateHandler.onSuccess(response.body()!!)
            }
        })
    }


    fun getAllJudgesMethod(handler: GetJudgesHandler) {
        apiCreate()
        api.getAllJudgesAPI().enqueue(object : Callback<GetJudgeExample> {
            override fun onFailure(call: Call<GetJudgeExample>, t: Throwable) {
                handler.onError(t.message.toString())
            }

            override fun onResponse(call: Call<GetJudgeExample>, response: Response<GetJudgeExample>) {
                handler.onSuccess(response.body()!!)
            }

        })
    }

    // For get all case types
    fun getAllCaseTypesMethod(handler: CaseTypesHandler) {
        apiCreate()
        api.getAllCasesTypes().enqueue(object : Callback<CaseTypeExample> {
            override fun onFailure(call: Call<CaseTypeExample>, t: Throwable) {
                handler.onError(t.message!!)
            }

            override fun onResponse(call: Call<CaseTypeExample>, response: Response<CaseTypeExample>) {
                handler.onSuccess(response.body()!!)
            }
        })
    }


    // for get all Purposes
    fun getAllPurposesMthod(handler: CasePurposesHandler) {
        apiCreate()
        api.getAllCasePurposes().enqueue(object : Callback<CasePurposesExample> {
            override fun onFailure(call: Call<CasePurposesExample>, t: Throwable) {
                handler.onError(t.message.toString())
            }

            override fun onResponse(call: Call<CasePurposesExample>, response: Response<CasePurposesExample>) {
                handler.onSuccess(response.body()!!)
            }

        })
    }

    // for add case
    fun addCaseMethod(jsonObject: JsonObject, handler: AddCaseHandler) {
        apiCreate()
        api.addCaseAPI(jsonObject).enqueue(object : Callback<AddCaseExample> {

            override fun onResponse(call: Call<AddCaseExample>, response: Response<AddCaseExample>) {
                handler.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<AddCaseExample>, t: Throwable) {
                handler.onError(t.message.toString())
            }
        })
    }


    fun updateCasseAPICall(
        tv_next_date_update: String,
        tv_Status_selection: String,
        et_payment_update: String,
        et_notes: String,
        checkValue: String,
        previousCaseDate: String, CaseId: String, userId: String,
        handler: CaseUpdateHandler
    ) {
        apiCreate()

        var jsonObject = JsonObject()
        jsonObject.addProperty("important_date_status", checkValue)
        jsonObject.addProperty("previous_case_date", previousCaseDate)

        jsonObject.addProperty("court_id", "")
        jsonObject.addProperty("place", "")
        jsonObject.addProperty("section", "")
        jsonObject.addProperty("date_of_fileing", "")
        jsonObject.addProperty("judge_id", "")
        jsonObject.addProperty("opposite_party", "")
        jsonObject.addProperty("opposite_lawyer", "")
        jsonObject.addProperty("case_type_id", "")

        jsonObject.addProperty("case_id", CaseId)

        jsonObject.addProperty("user_id", userId)

        jsonObject.addProperty("case_next_date", tv_next_date_update)
        jsonObject.addProperty("notes", et_notes)

        jsonObject.addProperty("purpose_of_hearing", tv_Status_selection)

        jsonObject.addProperty("payment", et_payment_update)


        api.updateCase(jsonObject).enqueue(object : Callback<CaseUpdateExample> {
            override fun onFailure(call: Call<CaseUpdateExample>, t: Throwable) {
                handler.onError(t.message.toString())

            }

            override fun onResponse(call: Call<CaseUpdateExample>, response: Response<CaseUpdateExample>) {
                handler.onSuccess(response.body()!!)
            }

        })
    }

    // for get client details
    fun getClientDetailsMethod(userId: String, clientId: String, handler: ClientDetailHandler) {
        apiCreate()
        api.clientDetailAPI(userId, clientId).enqueue(object : Callback<ClientDetailExample> {
            override fun onFailure(call: Call<ClientDetailExample>, t: Throwable) {
                handler.onError(t.message.toString())
            }

            override fun onResponse(call: Call<ClientDetailExample>, response: Response<ClientDetailExample>) {
                handler.onSuccess(response.body()!!)
            }
        })
    }


    // For get PArticular clientCases
    fun getAllCassForParticularClient(
        userId: String,
        clientId: String,
        page: Int,
        search: String,
        handler: ParticularClientHandler
    ) {
        apiCreate()
        api.getAllCasesClient(userId, clientId, page, search).enqueue(object : Callback<ParticularClientExample> {
            override fun onResponse(call: Call<ParticularClientExample>, response: Response<ParticularClientExample>) {
                handler.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<ParticularClientExample>, t: Throwable) {
                handler.onError(t.message.toString())
            }

        })
    }

    // for case detail
    fun getCaseDetailMethod(user_id: String, case_id: String, page: Int, handler: CaseDetailHandler) {
        apiCreate()
        api.caseDetailAPI(user_id, case_id, page).enqueue(object : Callback<CaseDetailExample> {

            override fun onResponse(call: Call<CaseDetailExample>, response: Response<CaseDetailExample>) {
                handler.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<CaseDetailExample>, t: Throwable) {
                handler.onError(t.message!!)
            }

        })
    }


    // for get All My Clients
    fun getAllMyClientMethod(advocateId: String, search: String, page: Int, handler: AllClientHandler) {

        try {
            // for remove all previous request
            okHttpClient.dispatcher().cancelAll()
        } catch (e: Exception) {

        }

        apiCreate()
        api.getAllMyClientsAPI(advocateId, search, page).enqueue(object : Callback<AllClientExample> {
            override fun onFailure(call: Call<AllClientExample>, t: Throwable) {
                handler.onError(t.message!!)
            }

            override fun onResponse(call: Call<AllClientExample>, response: Response<AllClientExample>) {
                handler.onSuccess(response.body()!!)
            }

        })
    }


    // for update client
    fun editClientMEthod(
        clientId: String, userId: String, et_name_client: String, et_phone_client: String,
        et_email_client: String, et_address_client: String, gender: String, handler: EditClientHandler
    ) {
        apiCreate()
        api.updateClientAPI(
            clientId,
            userId,
            et_name_client,
            et_email_client,
            et_phone_client,
            gender,
            et_address_client,
            et_name_client,
            ""
        )
            .enqueue(object : Callback<EditClientExample> {

                override fun onResponse(call: Call<EditClientExample>, response: Response<EditClientExample>) {
                    handler.onSuccess(response.body()!!)
                }

                override fun onFailure(call: Call<EditClientExample>, t: Throwable) {
                    handler.onError(t.message!!)
                }
            })
    }


    // for delete clients
    fun deleteClientMethod(client_id: String, user_id: String, handler: DeleteClientHandler) {
        apiCreate()
        api.deleteClientAPI(client_id, user_id).enqueue(object : Callback<DeleteClientsExample> {

            override fun onResponse(call: Call<DeleteClientsExample>, response: Response<DeleteClientsExample>) {
                handler.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<DeleteClientsExample>, t: Throwable) {
                handler.onError(t.message!!)
            }


        })
    }


    // For Delete Case
    fun deleteCaseMethod(caseId: String, user_id: String, handler: DeleteCaseHandler) {
        apiCreate()
        api.deleteCaseAPI(caseId, user_id).enqueue(object : Callback<DeleteCaseExample> {

            override fun onResponse(call: Call<DeleteCaseExample>, response: Response<DeleteCaseExample>) {
                handler.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<DeleteCaseExample>, t: Throwable) {
                handler.onError(t.message!!)
            }
        })
    }


    // for add case
    fun editCaseMethod(jsonObject: JsonObject, handler: CaseUpdateHandler) {
        apiCreate()
        api.editCase(jsonObject).enqueue(object : Callback<CaseUpdateExample> {

            override fun onResponse(call: Call<CaseUpdateExample>, response: Response<CaseUpdateExample>) {
                handler.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<CaseUpdateExample>, t: Throwable) {
                handler.onError(t.message.toString())
            }
        })
    }


    // for help Screens
    fun helpMethod(user_id: String, query: String, handler: HelpHandler) {
        apiCreate()
        api.helpAPI(user_id, query).enqueue(object : Callback<HelpExample> {

            override fun onResponse(call: Call<HelpExample>, response: Response<HelpExample>) {
                handler.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<HelpExample>, t: Throwable) {
                handler.onError(t.message!!)
            }
        })
    }
}