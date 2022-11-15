package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_login.*

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            it.isChecked=true

            when(it.itemId){
                R.id.nav_home -> replaceFragment(HomeFragment(),it.title.toString())
                R.id.nav_message -> Toast.makeText(applicationContext, "Clicked Message", Toast.LENGTH_SHORT).show()
                R.id.nav_setting -> replaceFragment(SettingFragment(),it.title.toString())
                R.id.nav_login -> replaceFragment(LoginFragment(),it.title.toString())
                R.id.nav_share -> Toast.makeText(applicationContext, "Clicked Share", Toast.LENGTH_SHORT).show()

            }
            true
        }

    }


    private fun replaceFragment(fragment: Fragment, title:String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}