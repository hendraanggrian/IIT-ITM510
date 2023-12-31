////////////////////////////////////////////////////////////////////////
// Course: ITM-510-05                                                 //
// Assignment: Movie Library 2.0                                      //
// Date: 29/10/2023                                                   //
// Student: Hendra Wijaya (A20529195)                                 //
//                                                                    //
// A recursive CLI app that manages movie library using MVC design    //
// pattern.                                                           //
////////////////////////////////////////////////////////////////////////

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class MovieControllerTest {
  @Test
  public void crudOperations() {
    MovieController controller = new MovieController();

    controller.createMovie("Barbie", "Greta Grewig", 2023, "Comedy", 114);
    assertEquals(0, controller.getCurrentMovieIndex());

    assertThrows(
        IllegalArgumentException.class,
        () -> controller.updateMovie(1, "Barbie & Ken", "Greta Grewig", 2023, "Comedy", 114)
    );
    controller.updateMovie(0, "Barbie & Ken", "Greta Grewig", 2023, "Comedy", 114);
    assertEquals("Barbie & Ken", controller.getCurrentMovie().getTitle());

    assertThrows(IllegalArgumentException.class, () -> controller.deleteMovie(1));
    controller.deleteMovie(0);
    assertTrue(controller.getMovieList().isEmpty());
  }

  @Test
  public void rotatingIndex() {
    List<Movie> movieList = new ArrayList<>();
    movieList.add(null);
    movieList.add(null);

    MovieController controller = new MovieController(movieList);
    assertEquals(1, controller.getCurrentMovieIndex());
    controller.navigateNext();
    assertEquals(0, controller.getCurrentMovieIndex());
    controller.navigatePrevious();
    assertEquals(1, controller.getCurrentMovieIndex());
  }
}
