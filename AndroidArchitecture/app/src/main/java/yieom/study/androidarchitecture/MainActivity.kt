package yieom.study.androidarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import yieom.study.androidarchitecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.tvMsg.text = "Hello World!!!!!"
        binding.btnFragment.setOnClickListener {
            replaceFragment()
        }
    }

    private fun replaceFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,MainFragment()).commit()
    }
}