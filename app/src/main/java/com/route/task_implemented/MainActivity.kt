package com.route.task_implemented

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.route.task_implemented.api.ApiInterface
import com.route.task_implemented.api.RetrofitInstance
import com.route.task_implemented.databinding.ActivityMainBinding
import com.route.task_implemented.models.Posts
import com.route.task_implemented.viewmodel.PostsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var postsAdapter: PostAdapter
    lateinit var viewModel : PostsViewModel
    var postsList  = ArrayList<Posts>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRV()

        viewModel=ViewModelProvider(this)[PostsViewModel::class.java]
        viewModel.getPostsData()
        viewModel.getPostLiveData().observe(this){
            postsAdapter.setList(it as ArrayList<Posts>)
        }


        postsAdapter.onPostClick = object : PostAdapter.OnItemClickListener{
            override fun onClickListener(post: Posts, position: Int) {
                makeIntent(position)
            }

        }
    }

    private fun prepareRV(){
        postsAdapter=PostAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = postsAdapter
    }


    private fun makeIntent(position:Int){
        val intent = Intent(this@MainActivity,DetailsActivity::class.java)
        intent.putExtra("", viewModel.getPostLiveData().value?.get(position)?.id)
        intent.putExtra("", viewModel.getPostLiveData().value?.get(position)?.userId)
        intent.putExtra("", viewModel.getPostLiveData().value?.get(position)?.title)
        intent.putExtra("", viewModel.getPostLiveData().value?.get(position)?.body)
        startActivity(intent)
    }
}






















//        val api : ApiInterface= RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
//        val call : Call<List<Posts>> = api.getData()
//       call.enqueue(object : Callback<List<Posts>>{
//           override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
//                   Toast.makeText(this@MainActivity, " Calll ", Toast.LENGTH_LONG).show()
//                   if (response.body() != null) {
//                       postsList= (response.body() as ArrayList<Posts>?)!!
//                       postsAdapter.setList(postsList)
//                       Log.d("Call<List<Posts>>","${(response.body() as ArrayList<Posts>).size}")
//
//                   } else {
//                       Toast.makeText(this@MainActivity, "noooooo Calll ", Toast.LENGTH_LONG).show()
//                   }
//               }
//           override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
//                   Toast.makeText(this@MainActivity, "noooooo Calll ", Toast.LENGTH_LONG).show()
//
//           }
//
//       })