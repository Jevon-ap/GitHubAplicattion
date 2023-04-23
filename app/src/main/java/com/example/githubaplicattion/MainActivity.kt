package com.example.githubaplicattion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentManager.BackStackEntry
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubaplicattion.Data.Model.User
import com.example.githubaplicattion.ROOM.FavoriteActivity
import com.example.githubaplicattion.ROOM.FavoriteRepository
import com.example.githubaplicattion.UI.Detail.DetailUserActivity
import com.example.githubaplicattion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var favoriteRepository: FavoriteRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter= UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object:UserAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: User) {

                Intent(this@MainActivity,DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME,data.login)
                    //showLoading(true)
                    it.putExtra(DetailUserActivity.EXTRA_ID,data.id)
                    it.putExtra(DetailUserActivity.EXTRA_URL,data.avatar_url)
                    startActivity(it)
                }
            }

        })
        viewModel= ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

        binding.et.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    searchUser()
                }

                override fun afterTextChanged(s: Editable?) {}
            })

        viewModel.getSearchUser().observe(this ,{
            if (it!=null){
                adapter.setList(it)
                showLoading(false)
            }
        })

    }
    private fun searchUser() {
        val query = binding.et.text.toString()
        if (query.isEmpty()) return
        showLoading(true)
        viewModel.setSearchUser(query)
    }

    private fun showLoading(state:Boolean){
        if (state){
            binding.progress.visibility = View.VISIBLE
            binding.recyclerView.visibility=View.GONE
            Handler().postDelayed({
                binding.progress.visibility = View.GONE
                binding.recyclerView.visibility=View.VISIBLE
            }, 3000)

        }else{
            binding.progress.visibility = View.GONE
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.Favorite -> {
                val i = Intent(this, FavoriteActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return true
        }
    }
}