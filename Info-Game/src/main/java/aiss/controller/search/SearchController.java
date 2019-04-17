package aiss.controller.search;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resources.SpotifyResource;
import aiss.model.spotify.Playlists;



/**
 * Servlet implementation class SearchController
 */
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = Logger.getLogger(SearchController.class.getName());
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String query = request.getParameter("searchQuery");
		RequestDispatcher rd = null;
		

				log.log(Level.FINE, "Searching for Spotify playlists that contain " + query);
				SpotifyResource spotify = new SpotifyResource("BQALI4HTtjUPoPsl_4luGSG4-Dzn1an7ZXWCuOpu7stl2wflfWwEQD44PJIDbjgUphbTBZEiuRnWJ4jm8yoPffXKZ1aORvcBncECk-ILYusn7MVe2c9oyB9aMQf8Ft8WsYO3JxT8JlgG8IhFf_bXYrQDQ8z-Yx2-GlOQg3e7Wd1Shtjv_GfNzc_FdCwMHny31KkdL_FFvSyjourgXE2h-1UBRSA7hzNnQvmiIuOrTQ");
				Playlists spotifyResults = spotify.searchPlaylist(query);
				
				if (spotifyResults!=null ){
					rd = request.getRequestDispatcher("/success.jsp");
					request.setAttribute("playlists", spotifyResults.getItems());
				} else {
					log.log(Level.SEVERE, "OMDb object: " + spotifyResults);
					rd = request.getRequestDispatcher("/error.jsp");
				}
				rd.forward(request, response);
			}
		
//		// Search for movies in OMDb
//		log.log(Level.FINE, "Searching for OMDb movies that contain " + query);
//		OMDbResource omdb = new OMDbResource();
//		MovieSearch omdbResults = omdb.getMovies(query);
//
//		log.log(Level.FINE, "Searching for flickr photos that contain " + query);
//		FlickrResource flickr = new FlickrResource();
//		PhotoSearch flickrResults = flickr.getFlickrPhotos(query);
//		
//		if (omdbResults!=null && flickrResults!=null ){
//			rd = request.getRequestDispatcher("/success.jsp");
//			request.setAttribute("movies", omdbResults.getSearch());
//			request.setAttribute("photos", flickrResults.getPhotos());	
//		} else {
//			log.log(Level.SEVERE, "OMDb object: " + omdbResults);
//			rd = request.getRequestDispatcher("/error.jsp");
//		}
//		rd.forward(request, response);
//	}
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
