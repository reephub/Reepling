package com.reepling.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.reepling.R;
import com.reepling.model.Movie;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by michael on 25/01/2016.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder>
        implements Filterable{

    private static final String TAG = MoviesAdapter.class.getSimpleName();

    private Context context;
    private List<Movie> moviesList;
    private List<Movie> moviesListFiltered;
    private MovieAdapterListener listener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.multi_pane_movie_item_title);
            genre = view.findViewById(R.id.multi_pane_movie_item_genre);
            year = view.findViewById(R.id.multi_pane_movie_item_year);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onMovieSelected(moviesListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public MoviesAdapter(Context context, List<Movie> moviesList, MovieAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.moviesList = moviesList;
        this.moviesListFiltered = moviesList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.multi_pane_movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesListFiltered.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return moviesListFiltered.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    moviesListFiltered = moviesList;
                } else {
                    List<Movie> filteredList = new ArrayList<>();
                    for (Movie row : moviesList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) || row.getGenre().contains(charSequence) || row.getYear().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    moviesListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = moviesListFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                moviesListFiltered = (ArrayList<Movie>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


        public interface MovieAdapterListener {
            void onMovieSelected(Movie movie);
        }
}