package spicinemas.api.db;

import org.jooq.DSLContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import spicinemas.SpiCinemasApplication;
import spicinemas.api.model.Movie;
import spicinemas.api.type.MovieListingType;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpiCinemasApplication.class)
@ActiveProfiles("test")
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepo;
    @Autowired
    DSLContext dslContext;

    @Test
    public void shouldInsertUserInDb(){
        String movieName = "Infinity War 1";
        Movie expectedMovie = new Movie(movieName, "RDX, Dolby Atmos, SUB", MovieListingType.NOW_SHOWING);
        movieRepo.addMovie(expectedMovie);
        Movie actualMovie = movieRepo.getMovie(movieName);
        assertThat(actualMovie.getName(), is(expectedMovie.getName()));
        assertThat(actualMovie.getExperiences(), is(expectedMovie.getExperiences()));
        assertThat(actualMovie.getListingType(), is(expectedMovie.getListingType()));
    }

    @Test
    public void shouldReturnAllMoviesWithListingTypeNowShowing(){

        Movie movie1 = new Movie("Infinity War", "RDX, Dolby Atmos, SUB", MovieListingType.NOW_SHOWING);
        Movie movie2 = new Movie("Iron Man", "RDX, Dolby Atmos, SUB", MovieListingType.UPCOMING);
        movieRepo.addMovie(movie1);
        movieRepo.addMovie(movie2);
        List<Movie> movies = movieRepo.getNowShowingMovies();
        Assert.assertEquals("Total now showing movies should be 1",11, movies.size());
    }

    @Test
    public void shouldReturnAMovieByName(){
        Movie movie1 = new Movie("Infinity War", "RDX, Dolby Atmos, SUB", MovieListingType.NOW_SHOWING);
        Movie movie2 = new Movie("Iron Man", "RDX, Dolby Atmos, SUB", MovieListingType.UPCOMING);
        movieRepo.addMovie(movie1);
        movieRepo.addMovie(movie2);
        Movie movie = movieRepo.getMovie("Iron Man");
        Assert.assertEquals("Get movie should return Iron Man","Iron Man", movie.getName());
    }




}