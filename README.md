# Flickrphotos
----------------------

Flickrphotos is a simple REST application to demonstrate the use of geolocation, with Flickr and Google maps.

Add flickr API KEY, secrect key and GMAP KEY on aplication.properties

```properties
apiKey = flicker_api_key
secret = secret_flickr_key
GMAP_API_KEY = AIza...... 
```

*Run*

 -  mvn spring-boot:run


*REST Endpoint*

 [http://localhost:8080/geolocation](http://localhost:8080/geolocation)
 
 [http://localhost:8080/geolocation/{id}?page=1](http://localhost:8080/geolocation/{id}?page=1)  id= flickr_id_photo

 *Libraries used*
 
 Flickr4Java
 
 Java Client for Google Maps Services
