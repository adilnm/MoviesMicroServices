package io.javabrains.movieinfoservice.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.movieinfoservice.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	@GetMapping("/{movieId}")
	public Movie getMovieinfo(@PathVariable("movieId") String movieId) {
		return new Movie(movieId, "Test name");
	}   
}
