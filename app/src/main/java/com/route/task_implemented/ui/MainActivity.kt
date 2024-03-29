package com.route.task_implemented.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.route.task_implemented.Constants
import com.route.task_implemented.adapter.PostAdapter
import com.route.task_implemented.databinding.ActivityMainBinding
import com.route.task_implemented.models.Posts
import com.route.task_implemented.viewmodel.PostsViewModel

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
        postsAdapter= PostAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = postsAdapter
    }


    private fun makeIntent(position:Int){
        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
        intent.putExtra(Constants.ID_KEY, viewModel.getPostLiveData().value?.get(position)?.id)
        intent.putExtra(Constants.USERID_KEY, viewModel.getPostLiveData().value?.get(position)?.userId)
        Log.d("makeIntent","${viewModel.getPostLiveData().value?.get(position)?.userId}")
        intent.putExtra(Constants.TITLE_KEY, viewModel.getPostLiveData().value?.get(position)?.title)
        intent.putExtra(Constants.BODY_KEY, viewModel.getPostLiveData().value?.get(position)?.body)
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