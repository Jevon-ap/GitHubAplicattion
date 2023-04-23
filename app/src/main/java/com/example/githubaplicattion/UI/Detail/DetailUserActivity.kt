package com.example.githubaplicattion.UI.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.githubaplicattion.ROOM.FavoriteRepository
import com.example.githubaplicattion.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var favoriteRepository: FavoriteRepository

    companion object{
        const val EXTRA_USERNAME="extra_username"
        const val EXTRA_ID="extra_id"
        const val EXTRA_URL="extra_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username =intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID,0)
        val avatar =intent.getStringExtra(EXTRA_URL)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME,username)
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        viewModel.setUserDetail(username.toString())


        viewModel.getUserDetail().observe(this, { userDetail ->
            userDetail?.let {
                showLoading(true)
                binding.tvName.text = it.name
                binding.tvUsername.text = it.login
                binding.tvLocation.text=it.location
                binding.tvFollowers.text = "${it.followers} Followers"
                binding.tvFollowing.text = "${it.following} Following"
                Glide.with(this@DetailUserActivity)
                    .load(it.avatarUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(binding.imageView2)
            }
        })

        val sectionPagerAdapter = SectionPagerAdapter(this,supportFragmentManager,bundle)

        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

 //       fungsi dibawah input ke database bikin error bikin error , kenapa?

//        favoriteRepository = FavoriteRepository(application)
//
//        var ischecked = false
//        val count = favoriteRepository.checkUser(id)
//        if (count != null){
//            if (count > 0){
//                binding.btnFav.isChecked = true
//                ischecked=true
//            }else{
//                binding.btnFav.isChecked=false
//                ischecked = false
//            }
//        }
//        binding.btnFav.setOnClickListener {
//            ischecked = !ischecked
//            if (ischecked){
//                if (username != null) {
//                    if (avatar != null) {
//                        favoriteRepository.insertFavorite(
//                            username,
//                            id
//                            ,avatar)
//                    }
//                }
//            }else{
//                favoriteRepository.deleteFavorite(id)
//            }
//            binding.btnFav.isChecked = ischecked
//
//        }


    }
    private fun showLoading(state:Boolean){
        if (state){
            binding.progress.visibility = View.VISIBLE
            binding.imageView2.visibility=View.GONE
            Handler().postDelayed({
                binding.progress.visibility = View.GONE
                binding.imageView2.visibility=View.VISIBLE
            }, 2000)

        }else{
            binding.progress.visibility = View.GONE
        }
    }


}