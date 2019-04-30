package capstonedesign.globalrounge.model.permission

open class BaseServer {
    internal var ip = "219.250.232.170"
    internal val port = 5050

    companion object{
        const val LOGIN = 100
        const val LOGIN_OK = 101
        const val LOGIN_ALREADY = 102
        const val LOGIN_NO_DATA = 103

        const val STATE_REQ = 200
        const val STATE_DEL = 201
        const val STATE_CREATE = 202

        const val LOGOUT = 500
    }
    /**
     * 이스터에그를 위한 함수
     */
    fun setIp(ip:String){
        this.ip=ip
    }

}