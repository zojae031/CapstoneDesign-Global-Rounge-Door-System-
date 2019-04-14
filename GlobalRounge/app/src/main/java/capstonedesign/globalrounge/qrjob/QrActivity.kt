package capstonedesign.globalrounge.qrjob

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import capstonedesign.globalrounge.mainjob.User
import capstonedesign.globalrounge.mainjob.view.MainActivity.Companion.REQUEST_CODE
import capstonedesign.globalrounge.R
import capstonedesign.globalrounge.Student
import capstonedesign.globalrounge.databinding.ActivityQrBinding

/**
 * 미완성 더미 액티비티
 */
class QrActivity : AppCompatActivity() {

    //데이터바인딩 변수
    private lateinit var binding: ActivityQrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qr)
        val student = intent.getSerializableExtra(EXTRA_USER) as Student
        binding.textView.text = "id : ${student.studentID} \n name : ${student.name}"
        binding.logout.setOnClickListener {
            setResult(REQUEST_CODE)
            finish()
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    companion object {
        private const val EXTRA_USER = "EXTRA_USER"
        fun getIntent(context: Context?, student: Student) = Intent(context, QrActivity::class.java).apply {
            putExtra(EXTRA_USER, student)
        }

    }
}