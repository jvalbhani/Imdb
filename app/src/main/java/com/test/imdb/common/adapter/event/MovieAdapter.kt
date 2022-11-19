package com.test.imdb.common.adapter.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.imdb.R
import com.test.imdb.common.datamodel.Movie

class MovieAdapter(private val items: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null
    override fun getItemCount() = items.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return MovieViewHolder(
            view,
            onItemClickListener
        )
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_movie_tile
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

    }

    class MovieViewHolder(view: View, private val listener: OnItemClickListener?) :
        RecyclerView.ViewHolder(view) {
        fun bind(event: Movie) {
            with(itemView) {
                setOnClickListener {
                    listener?.onClick(event)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(movie: Movie)
    }
}
