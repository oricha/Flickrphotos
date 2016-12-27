package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.GeoData;
import com.flickr4java.flickr.photos.geo.GeoInterface;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@RestController
public class GeolocationController {

	private static final Logger LOG = LoggerFactory.getLogger(GeolocationController.class);
	@Value("${secret}")
	private String SHAREDSECRET;

	@Value("${apiKey}")
	private String APIKEY;

	@Value("${GMAP_API_KEY}")
	private String GMAP_KEY;

	@Value("${geo.photoid}")
	private String photoId;
	/**
	 *  The id parameter is the id_photo, If it is null, a default photo is used
	 * 
	 */
	@RequestMapping("/geolocation/{id}")
	@Produces("application/json")
	public List<GeocodingResult> geolocation(@PathVariable String id)
			throws FlickrException {
		List<GeocodingResult> result = new ArrayList<>();
		
		photoId = (id == null) ? photoId: id;  //Mock id_photo, is optional
		
		Flickr flickr = new Flickr(APIKEY, SHAREDSECRET, new REST());
		GeoInterface geo = flickr.getPhotosInterface().getGeoInterface();
		GeoData locData;
		try {
			locData = geo.getLocation(photoId);
		} catch (FlickrException e) {
			LOG.error("Photo not found");
			throw new FlickrException(e.getErrorCode(), e.getErrorMessage());
		}

		GeoApiContext context = new GeoApiContext().setApiKey(GMAP_KEY);
		GeocodingResult[] results;
		try {
			results = GeocodingApi.newRequest(context).latlng(new LatLng(locData.getLatitude(), locData.getLongitude()))
					.await();
			for (GeocodingResult item : results) {
				result.add(item);
			}
		} catch (Exception e) {
			LOG.error("Error locating photo", e.getMessage());
		}
		return result;
	}

}
