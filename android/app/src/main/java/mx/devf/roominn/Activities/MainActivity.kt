package mx.devf.roominn.Activities

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import mx.devf.roominn.API.RoomInnService
import mx.devf.roominn.Components.ServiceGenerator
import mx.devf.roominn.R
import mx.devf.roominn.Fragments.RoomiesFragment
import mx.devf.roominn.Fragments.ServicesFragment
import mx.devf.roominn.HouseFragment
import mx.devf.roominn.Interfaces.IMainNavigate
import mx.devf.roominn.Settings

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, IMainNavigate  {

    override var service : RoomInnService? = null
    var token : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //fab.setOnClickListener { view ->
        //    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //            .setAction("Action", null).show()
        //}

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        nav_view.setCheckedItem(R.id.nav_housedetails)
        navigate(HouseFragment.newIntances(), "House Details")
        setupService()

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        if(item.isChecked == false) {
            val tag = item.title.toString()
            when (item.itemId) {
            // Handles
                R.id.nav_housedetails -> {
                    navigate(HouseFragment.newIntances(), tag)
                }
                R.id.nav_roomies -> {
                    navigate(RoomiesFragment.newIntances(), tag)
                }
                R.id.nav_services -> {
                    navigate(ServicesFragment.newIntances(), tag)
                }
                R.id.nav_logout ->  logout()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setupService() {
        token = Settings.getToken(this)
        service = ServiceGenerator.createService(RoomInnService::class.java, token)
    }

    override fun navigate(fragment : Fragment)
    {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit()
    }

    override fun navigate(fragment : Fragment, tag : String)
    {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_right_enter, R.anim.slide_left_exit)
                .replace(R.id.content, fragment, tag)
                .commit()
    }

    override fun logout(){

        Settings.saveToken(this, "")
        val intent = Intent(this, LoginActivity::class.java)
        //intent.putExtra(Constants.INTENT_KEY_USERNAME, username)
        startActivity(intent)
        finish()
    }


}
