package com.example.testtask.content_screen.profile_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.sign_screen.MainActivity
import com.factor.bouncy.BouncyRecyclerView

class ProfileFragment : Fragment() {

    private var itemsRecyclerView: BouncyRecyclerView? = null

    private var profileItemsAdapter: ProfileAdapter? = ProfileAdapter(emptyList())

    private val profileViewModel: ProfileViewModel by lazy {
        ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        itemsRecyclerView = view.findViewById(R.id.profile_recycler_view)

        itemsRecyclerView?.layoutManager = LinearLayoutManager(context)
        itemsRecyclerView?.adapter = profileItemsAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.itemsLiveData.observe(
            viewLifecycleOwner,
            Observer {
                profileItemsAdapter = ProfileAdapter(it)
                itemsRecyclerView?.adapter = profileItemsAdapter
            }
        )
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }

    inner class ProfileListItemWithArrowHolder(view: View) :  ProfileItemList(view) {
        private val itemIcon: ImageView = itemView.findViewById(R.id.item_icon)
        private val itemTitle: TextView = itemView.findViewById(R.id.item_title)

        override fun bind(item: ProfileListItem) {
            itemIcon.setImageResource(item.icon)
            itemTitle.text = item.title
        }
    }

    inner class ProfileListItemWithoutArrowHolder(view: View) :  ProfileItemList(view), OnClickListener {
        private val itemIcon: ImageView = itemView.findViewById(R.id.item_icon)
        private val itemTitle: TextView = itemView.findViewById(R.id.item_title)
        private val itemDescription: TextView = itemView.findViewById(R.id.item_description)

        private lateinit var item: ProfileListItem

        init {
            itemView.setOnClickListener(this)
        }

        override fun bind(item: ProfileListItem) {
            this.item = item
            itemIcon.setImageResource(item.icon)
            itemTitle.text = item.title
            itemDescription.text = item.description
        }

        override fun onClick(p0: View?) {
            if (item.title == "Log out") {
                val intent = MainActivity.newIntent(requireContext())
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }

    abstract inner class ProfileItemList(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: ProfileListItem)
    }

    inner class ProfileAdapter(var items: List<ProfileListItem>) : RecyclerView.Adapter<ProfileItemList>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileItemList {
           return when(viewType) {
                0 -> ProfileListItemWithArrowHolder(layoutInflater.inflate(R.layout.profile_list_item_with_arrow,
                    parent, false))
               else -> ProfileListItemWithoutArrowHolder(layoutInflater.inflate(R.layout.profile_list_item_without_arrow,
                   parent, false))
            }
        }

        override fun onBindViewHolder(holder: ProfileItemList, position: Int) {
            val item = items[position]
            holder.bind(item)
        }

        override fun getItemCount() = items.size

        override fun getItemViewType(position: Int) = if (items[position].withArrow) 0 else 1

    }
}