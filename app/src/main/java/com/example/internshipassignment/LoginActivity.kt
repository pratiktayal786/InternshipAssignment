package com.example.internshipassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient
    lateinit var display_tv: TextView
    lateinit var siteurl: String
    lateinit var username: String
    lateinit var password: String
    lateinit var display_pd: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        siteurl = intent.getStringExtra("siteurl")!!
        username = intent.getStringExtra("username")!!
        password = intent.getStringExtra("password")!!

        display_pd = findViewById(R.id.pb_display)
        display_tv = findViewById(R.id.tv_display)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)
            apiClient.getApiService(siteurl).login(LoginRequest(username = username, password = password))
                .enqueue(object : Callback<LoginResponse>{
                    override fun onResponse(p0: Call<LoginResponse>, p1: Response<LoginResponse>) {
                        val response = p1.body();
                        if(response != null)
                        {
                            sessionManager.saveAutnToken(response.token)
                            //Toast.makeText(this@LoginActivity, response.token, Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(p0: Call<LoginResponse>, p1: Throwable) {
                        Log.wtf("Error", "We are not able to get the token")
                    }
                })
            fetchPost()

    }
    private  fun fetchPost()
    {
        apiClient.getApiService(siteurl).fetchPost(token =  "Bearer ${sessionManager.fetchAuthToken()}")
            .enqueue(object : Callback<PostResponse>{
                override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                    val res = response.body()
                    display_pd.visibility = View.GONE
                    display_tv.visibility = View.VISIBLE
                    if (res != null) {
                        display_tv.setText("spacelimit --->> " + res.spacelimit + "\n")
                        display_tv.append("articlelimit --->> " + res.articlelimit + "\n")
                        display_tv.append("productlimit --->> " + res.productlimit + "\n")
                        display_tv.append("featured_product_limit --->> " + res.featured_product_limit + "\n")
                        display_tv.append("gallerylimit --->> " + res.gallerylimit + "\n")
                        display_tv.append("article_catlimit --->> " + res.article_catlimit + "\n")
                        display_tv.append("catlimit --->> " + res.catlimit + "\n")
                        display_tv.append("sold_by_label --->> " + res.sold_by_label + "\n")
                        display_tv.append("customerlimit --->> " + res.customerlimit + "\n")
                        display_tv.append("stafflimit --->> " + res.stafflimit + "\n")
                        display_tv.append("manage_commission --->> " + res.manage_commission + "\n")
                        display_tv.append("wp_admin_view --->> " + res.wp_admin_view + "\n")
                        display_tv.append("manage_groups --->> " + res.manage_groups + "\n")
                        display_tv.append("manage_managers --->> " + res.manage_managers + "\n")
                        display_tv.append("capability_controller --->> " + res.capability_controller + "\n")
                        display_tv.append("membership --->> " + res.membership + "\n")
                    }
                }

                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}