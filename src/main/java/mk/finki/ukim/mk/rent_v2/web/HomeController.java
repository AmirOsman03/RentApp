package mk.finki.ukim.mk.rent_v2.web;

import mk.finki.ukim.mk.rent_v2.model.Car;
import mk.finki.ukim.mk.rent_v2.model.CarReview;
import mk.finki.ukim.mk.rent_v2.model.enumerations.CarType;
import mk.finki.ukim.mk.rent_v2.service.CarReviewService;
import mk.finki.ukim.mk.rent_v2.service.CarService;
import mk.finki.ukim.mk.rent_v2.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/", "/home"})
public class HomeController {
    private final CarService carService;
    private final LocationService locationService;
    private final CarReviewService reviewService;

    public HomeController(CarService carService, LocationService locationService, CarReviewService reviewService) {
        this.carService = carService;
        this.locationService = locationService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        return "home";
    }

    @PostMapping
    public String showLocation(@RequestParam String city, Model model) {
        model.addAttribute("cars", carService.searchCarsByLocation_Name(city));
        model.addAttribute("locations", locationService.findAll()); // If you want to display available locations
        model.addAttribute("searchCity", city); // To keep the search query visible
        return "home";
    }

    @GetMapping("/view/{id}")
    public String viewCar(@PathVariable Long id, Model model) {
        Car car = this.carService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car id:" + id));
        model.addAttribute("car", car);
        // Use the appropriate method for fetching reviews
        model.addAttribute("reviews", reviewService.getReviewsByCarId(id));  // Make sure this method exists in your service
        return "review";  // Return the view that should display the car details
    }

    @PostMapping("/view/rating/{id}")
    public String addReview(@PathVariable Long id,
                            @RequestParam int rating,
                            @RequestParam long userId) {
        reviewService.saveRating(id, rating, userId);

        return "redirect:/view/" + id;
    }
}
