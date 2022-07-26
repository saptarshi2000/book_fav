package com.wipro.BookAppFavorite.Controller;

import com.wipro.BookAppFavorite.Model.AddToFavouriteResponse;
import com.wipro.BookAppFavorite.Model.Book;
import com.wipro.BookAppFavorite.Model.DeleteFavouriteResponse;
import com.wipro.BookAppFavorite.Model.GetAllFavouritesResponse;
import com.wipro.BookAppFavorite.Service.FavouriteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favourite")
@CrossOrigin
public class FavouriteController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @PostMapping("/add_to_favourite")
    public ResponseEntity<AddToFavouriteResponse> addToFavourite(@RequestBody Book book, @RequestHeader("Authorization") String token){
        AddToFavouriteResponse addToFavouriteResponse =
                favouriteService.addToFavourites(book,token);
        return new ResponseEntity<>(addToFavouriteResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get_all_favourites")
    public ResponseEntity<GetAllFavouritesResponse> getAllFavourite(@RequestHeader("Authorization") String token){
        GetAllFavouritesResponse response = favouriteService.getAllFavourites(token);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete_book/{book_id}")
    public ResponseEntity<DeleteFavouriteResponse> deleteFavourite(@PathVariable String book_id,@RequestHeader("Authorization") String token){
        DeleteFavouriteResponse response = favouriteService.deleteFavourite(book_id,token);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}