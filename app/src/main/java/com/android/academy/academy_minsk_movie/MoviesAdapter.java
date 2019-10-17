package com.android.academy.academy_minsk_movie;

import android.graphics.drawable.Drawable;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.academy.academy_minsk_movie.data.DataStorage;
import com.android.academy.academy_minsk_movie.data.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

//    private final OnItemClickListener itemClickListener;

    public static final int ADVERTISING_POSITION = 3;
    private static final int ITEM_VIEW_TYPE_MOVIES = 0;
    static final int ITEM_VIEW_TYPE_ADVERTISING = 1;

    /**
     * A listener that is attached to all ViewHolders to handle image loading events and clicks.
     */
    private interface ViewHolderListener {

        void onLoadCompleted(ImageView view, int adapterPosition);

        void onItemClicked(View view, int adapterPosition);

        void onAdvertisingClick();
    }

    private final RequestManager requestManager;
    private final ViewHolderListener viewHolderListener;


//    public interface OnItemClickListener {
//        /**
//         * MoviesFragment должен реализовывать этот интерфейс, чтобы адаптер мог
//         * сообщать фрагменту о кликах
//         */
//        void onItemClick(int position);
//
//        void onAdvertisingClick();
//    }

    private List<Movie> listOfItems;

    MoviesAdapter(Fragment fragment) {

        // В конструктор адаптера передаём интерфейс для прослушки кликов
        this.requestManager = Glide.with(fragment);
        this.viewHolderListener = new ViewHolderListenerImpl(fragment);

        // Инициализируем лист с фильмами
        listOfItems = DataStorage.getInstance().getMovieList();
    }

    @Override
    public int getItemViewType(int position) {

        // Проверяем была ли выбрана реклама из списка фильмов
        if (position != ADVERTISING_POSITION) {
            return ITEM_VIEW_TYPE_MOVIES;
        }
        return ITEM_VIEW_TYPE_ADVERTISING;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        // Проверяем для какого типа view нужно создать viewHolder
        if (viewType == ITEM_VIEW_TYPE_ADVERTISING) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movies_recycler_view_advertising, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movies_recycler_view_item, parent, false);
        }
        return new ViewHolder(view, requestManager, viewHolderListener, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Если тип view не является рекламой, то биндим туда значения из viewHolder
        if (holder.getItemViewType() != ITEM_VIEW_TYPE_ADVERTISING) {
            holder.onBind();
        }
    }

    @Override
    public int getItemCount() {

        // Проверяем размер нашего списка фильмов
        return listOfItems.size();
    }

    interface OnFragmentInteractionListener {
        // Слушатель для переключения Navigation icon на AppBar
        void onFragmentInteraction();
    }
    private static class ViewHolderListenerImpl implements ViewHolderListener {

        private OnFragmentInteractionListener fragmentInteractionListener;



        private Fragment fragment;
        private AtomicBoolean enterTransitionStarted;

        ViewHolderListenerImpl(Fragment fragment) {
            fragmentInteractionListener = (OnFragmentInteractionListener) fragment.getContext();
            this.fragment = fragment;
            this.enterTransitionStarted = new AtomicBoolean();
        }

        @Override
        public void onLoadCompleted(ImageView view, int position) {
            // Call startPostponedEnterTransition only when the 'selected' image loading is completed.
            if (MainActivity.currentPosition != position) {
                return;
            }
            if (enterTransitionStarted.getAndSet(true)) {
                return;
            }
            fragment.startPostponedEnterTransition();
        }

        /**
         * Handles a view click by setting the current position to the given {@code position} and
         * starting a {@link  GalleryDetailsAdapter} which displays the image at the position.
         *
         * @param view     the clicked {@link ImageView} (the shared element view will be re-mapped at the
         *                 GridFragment's SharedElementCallback)
         * @param position the selected view position
         */
        @Override
        public void onItemClicked(View view, int position) {
            fragmentInteractionListener.onFragmentInteraction();
            // Update the position.
            MainActivity.currentPosition = position;

            // Exclude the clicked card from the exit transition (e.g. the card will disappear immediately
            // instead of fading out with the rest to prevent an overlapping animation of fade and move).
            ((TransitionSet) fragment.getExitTransition()).excludeTarget(view, true);

            ImageView transitioningView = view.findViewById(R.id.movies_item_iv_poster);
            fragment.getFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true) // Optimize for shared element transition
                    .addSharedElement(transitioningView, transitioningView.getTransitionName())
                    .replace(R.id.container, new GalleryDetailsFragment(), GalleryDetailsFragment.class
                            .getSimpleName())
                    .addToBackStack(null)
                    .commit();
        }

        @Override
        public void onAdvertisingClick() {
//            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ADVERTISING_URL)));
        }
    }


    final class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RequestManager requestManager;
        private ViewHolderListener viewHolderListener;
        private ImageView poster;
        private TextView title;
        private TextView overview;

        private int viewType;

        private ViewHolder(@NonNull View itemView,
                           RequestManager requestManager,
                           ViewHolderListener viewHolderListener,
                           int viewType) {
            super(itemView);
            this.viewType = viewType;
            // Проверяем, если тип view не является рекламой, то инициализируем вьюхи
            if (viewType != ITEM_VIEW_TYPE_ADVERTISING) {
                this.requestManager = requestManager;
                this.viewHolderListener = viewHolderListener;

                poster = itemView.findViewById(R.id.movies_item_iv_poster);
                title = itemView.findViewById(R.id.movies_item_tv_title);
                overview = itemView.findViewById(R.id.movies_item_tv_overview_text);
                itemView.findViewById(R.id.movies_item_card_view).setOnClickListener(this);
            }


            // Ставим слушатель на элемент из списка recyclerView
//            setListeners(itemView, viewType);
        }

        /**
         * Binds this view holder to the given adapter position.
         * <p>
         * The binding will load the image into the image view, as well as set its transition name for
         * later.
         */
        void onBind() {
            int position = getAdapterPosition();
            Movie movie = listOfItems.get(position);

            title.setText(movie.getTitle());
            overview.setText(movie.getOverview());

            setImage(movie, position);

            // Set the string value of the image resource as the unique transition name for the view.
            poster.setTransitionName(String.valueOf(movie.getPosterRes()));
        }

        void setImage(Movie movie, int position) {
            // Load the image with Glide to prevent OOM error when the image drawables are very large.
            requestManager
                    .load(movie.getPosterRes())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                    Target<Drawable> target, boolean isFirstResource) {
                            viewHolderListener.onLoadCompleted(poster, position);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable>
                                target, DataSource dataSource, boolean isFirstResource) {
                            viewHolderListener.onLoadCompleted(poster, position);
                            return false;
                        }
                    })
                    .into(poster);
        }

        @Override
        public void onClick(View view) {

            // Проверяем тип view...
            if (viewType != ITEM_VIEW_TYPE_ADVERTISING) {

                // Если это филь, то переходим на просмотр деталей фильма...
                viewHolderListener.onItemClicked(view, getAdapterPosition());
            } else {

                // Если это реклама, то открываем YouTube
                viewHolderListener.onAdvertisingClick();
            }


        }

//        private void setListeners(View itemView, int viewType) {
//            itemView.setOnClickListener(view -> {
//
//                // Получаем значение позиции выбранного элемента
//                int position = getAdapterPosition();
//
//                // Проверяем слушатель и позицию элемента на валидность
//                if (itemClickListener != null && position != RecyclerView.NO_POSITION) {
//
//                    // Проверяем тип view...
//                    if (viewType != ITEM_VIEW_TYPE_ADVERTISING) {
//
//                        // Если это филь, то переходим на просмотр деталей фильма...
//                        itemClickListener.onItemClick(getAdapterPosition());
//                    } else {
//
//                        // Если это реклама, то открываем YouTube
//                        itemClickListener.onAdvertisingClick();
//                    }
//                }
//            });
//        }


    }
}

