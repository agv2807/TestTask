package com.example.testtask.content_screen.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.content_screen.profile_screen.ProfileViewModel
import com.factor.bouncy.BouncyRecyclerView

class HomeFragment : Fragment() {

    private var categoriesRecyclerView: BouncyRecyclerView? = null

    private var categoriesAdapter: CategoriesAdapter? = CategoriesAdapter(emptyList())

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        categoriesRecyclerView = view.findViewById(R.id.categories_recycler_view)

        categoriesRecyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoriesRecyclerView?.adapter = categoriesAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.categoriesLiveData.observe(
            viewLifecycleOwner,
            Observer {
                categoriesAdapter = CategoriesAdapter(it)
                categoriesRecyclerView?.adapter = categoriesAdapter
            }
        )
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    private inner class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val categoryIcon: ImageView = itemView.findViewById(R.id.category_icon)
        private val categoryTitle: TextView = itemView.findViewById(R.id.category_title)

        fun bind(category: Category) {
            categoryIcon.setImageResource(category.icon)
            categoryTitle.text = category.title
        }
    }

    private inner class CategoriesAdapter(var categories: List<Category>) : RecyclerView.Adapter<CategoriesViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
            val view = layoutInflater.inflate(R.layout.categories_item, parent, false)
            return CategoriesViewHolder(view)
        }

        override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
            val category = categories[position]
            holder.bind(category)
        }

        override fun getItemCount() = categories.size

    }
}