package capstonedesign.globalrounge.model.permission

import android.annotation.SuppressLint
import android.util.Log
import capstonedesign.globalrounge.dto.STUDENT
import capstonedesign.globalrounge.dto.User
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 세종대학교 인증정보를 우회접근하여 받아오는 class
 * @see capstonedesign.globalrounge.mainjob.MainPresenter.requestSejongPermission
 */
object SejongConnection {
    private val sejongPermission: Permission
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    /**
     * POST 를 보내기위한 Retrofit Interface
     */
    private interface Permission {

        @POST(url)
        fun getResult(@Query("rUserid") rUserid: String, @Query("rPW") rPW: String, @Query("pro") pro: Int): Observable<ResponseBody>

        companion object {
            const val url = "https://udream.sejong.ac.kr/main/loginPro.aspx/"
        }
    }

    /**
     * retrofit 정보를 초기화하는 생성자
     */
    init {
        sejongPermission = with(Retrofit.Builder()) {
            baseUrl(Permission.url)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            build()
        }.create(Permission::class.java)
    }

    /**
     * 사용자 정보를 요청하는 구간
     * @param user : 입력된 사용자 정보
     * @param callback : 응답값에 따른 콜백
     * @see capstonedesign.globalrounge.mainjob.MainPresenter.requestSejongPermission
     *
     */
    @SuppressLint("CheckResult")
    fun requestUserInformation(user: User): Observable<ResponseBody> =
        sejongPermission.getResult(user.id, user.pw, STUDENT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun addDisposable(disposable: Disposable){
        SejongConnection.compositeDisposable.add(disposable)
    }
    fun clearDisposable(){
        SejongConnection.compositeDisposable.clear()
    }

}