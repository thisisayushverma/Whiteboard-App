package com.example.whiteboardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.whiteboardapp.databinding.ActivityContentBinding
import com.google.android.material.navigation.NavigationView


class ContentActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var cavbinding:ActivityContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cavbinding= ActivityContentBinding.inflate(layoutInflater)
        setContentView(cavbinding.root)
        setSupportActionBar(cavbinding.topAppBar)
        var toggle = ActionBarDrawerToggle(this,cavbinding.drawerlayout,cavbinding.topAppBar,R.string.app_name,R.string.app_name)
        cavbinding.drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

        cavbinding.navMenu.setNavigationItemSelectedListener(this)

        changefrag(HomeFragment())

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        cavbinding.drawerlayout.closeDrawer(GravityCompat.START)
        if(item.itemId==R.id.home) {
            changefrag(HomeFragment())
            supportActionBar?.title="Whiteboard"

        }
        else if(item.itemId==R.id.changepass){
            supportActionBar?.title="Profile"
            changefrag(ChangePassFrag())
        }
        else if(item.itemId==R.id.logout) Toast.makeText(this,"Log out",Toast.LENGTH_LONG).show()
        return true
    }

    fun changefrag(frag:Fragment){
        val fragment=supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragcontainer,frag).commit()
    }
}