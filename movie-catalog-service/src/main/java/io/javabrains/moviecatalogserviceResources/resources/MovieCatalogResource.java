package io.javabrains.moviecatalogserviceResources.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.javabrains.moviecatalogserviceResources.models.CatalogItem;
import io.javabrains.moviecatalogserviceResources.models.Movie;
import io.javabrains.moviecatalogserviceResources.models.Rating;
import io.javabrains.moviecatalogserviceResources.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/"+userId, UserRating.class);
		return ratings.getUserRating().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);


			return new CatalogItem(movie.getName(), "Desc", rating.getRating());
		}).collect(Collectors.toList());
	}

}
