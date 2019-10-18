package com.android.academy.academy_minsk_movie.data;

import com.android.academy.academy_minsk_movie.ui.movies_list.MoviesAdapter;
import com.android.academy.academy_minsk_movie.R;

import java.util.ArrayList;
import java.util.List;

public class DataStorage {

    private static DataStorage instance;
    private List<Movie> movieList;

    private DataStorage() {
        movieList = createMovieList();
    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    private List<Movie> createMovieList() {
        List<Movie> list = new ArrayList<>();
        list.add(new Movie(
                "Jurassic World - Fallen Kingdom",
                "Three years after the demise of Jurassic World, a volcanic eruption threatens the remaining dinosaurs on the isla Nublar, so Claire Dearing, the former park manager, recruits Owen Grady to help prevent the extinction of the dinosaurs once again",
                R.drawable.jurassic_world_fallen_kingdom,
                R.drawable.jurassic_world_fallen_kingdom_big,
                "June 22, 2018",
                "https://youtu.be/vn9mMeWcgoM"
        ));
        list.add(new Movie(
                "The Meg",
                "A deep sea submersible pilot revisits his past fears in the Mariana Trench, and accidentally unleashes the seventy foot ancestor of the Great White Shark believed to be extinct",
                R.drawable.the_meg,
                R.drawable.the_meg_big,
                "August 10, 2018",
                "https://youtu.be/GGYXExfKhmo"
        ));
        list.add(new Movie(
                "The First Purge",
                "To push the crime rate below one percent for the rest of the year, the New Founding Fathers of America test a sociological theory that vents aggression for one night in one isolated community. But when the violence of oppressors meets the rage of the others, the contagion will explode from the trial-city borders and spread across the nation",
                R.drawable.the_first_purge,
                R.drawable.the_first_purge_big,
                "July 4, 2018",
                "https://youtu.be/UL29y0ah92w"
        ));
        list.add(new Movie(
                "Deadpool 2",
                "Wisecracking mercenary Deadpool battles the evil and powerful Cable and other bad guys to save a boy's life",
                R.drawable.deadpool_2,
                R.drawable.deadpool_2_big,
                "May 18, 2018",
                "https://youtu.be/20bpjtCbCz0"
        ));
        list.add(new Movie(
                "Black Panther",
                "King T'Challa returns home from America to the reclusive, technologically advanced African nation of Wakanda to serve as his country's new leader. However, T'Challa soon finds that he is challenged for the throne by factions within his own country as well as without. Using powers reserved to Wakandan kings, T'Challa assumes the Black Panther mantel to join with girlfriend Nakia, the queen-mother, his princess-kid sister, members of the Dora Milaje (the Wakandan 'special forces') and an American secret agent, to prevent Wakanda from being dragged into a world war",
                R.drawable.black_panther,
                R.drawable.black_panther_big,
                "February 16, 2018",
                "https://youtu.be/xjDjIWPwcPU"
        ));
        list.add(new Movie(
                "Ocean's Eight",
                "Debbie Ocean, a criminal mastermind, gathers a crew of female thieves to pull off the heist of the century at New York's annual Met Gala",
                R.drawable.ocean_eight,
                R.drawable.ocean_eight_big,
                "June 8, 2018",
                "https://youtu.be/MFWF9dU5Zc0"
        ));
        list.add(new Movie(
                "Interstellar",
                "Interstellar chronicles the adventures of a group of explorers who make use of a newly discovered wormhole to surpass the limitations on human space travel and conquer the vast distances involved in an interstellar voyage",
                R.drawable.interstellar,
                R.drawable.interstellar_big,
                "November 7, 2014",
                "https://youtu.be/zSWdZVtXT7E"
        ));
        list.add(new Movie(
                "Thor - Ragnarok",
                "Thor is on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the prophecy of destruction to his homeworld and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela",
                R.drawable.thor_ragnarok,
                R.drawable.thor_ragnarok_big,
                "November 3, 2017",
                "https://youtu.be/ue80QwXMRHg"
        ));
        list.add(new Movie(
                "Guardians of the Galaxy",
                "Light years from Earth, 26 years after being abducted, Peter Quill finds himself the prime target of a manhunt after discovering an orb wanted by Ronan the Accuser",
                R.drawable.guardians_of_the_galaxy,
                R.drawable.guardians_of_the_galaxy_big,
                "August 1, 2014",
                "https://youtu.be/d96cjJhvlMA"
        ));
        list.add(new Movie(
                "Avengers: Endgame",
                "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
                R.drawable.avengers_endgame,
                R.drawable.avengers_endgame_big,
                "April 22, 2019",
                "https://www.youtube.com/watch?v=6ZfuNTqbHE8"
        ));
        list.add(MoviesAdapter.ADVERTISING_POSITION, list.get(MoviesAdapter.ADVERTISING_POSITION - 1));
        return list;
    }
}
