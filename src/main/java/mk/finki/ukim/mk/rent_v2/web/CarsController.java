package mk.finki.ukim.mk.rent_v2.web;

import mk.finki.ukim.mk.rent_v2.model.Car;
import mk.finki.ukim.mk.rent_v2.model.enumerations.CarType;
import mk.finki.ukim.mk.rent_v2.service.CarReviewService;
import mk.finki.ukim.mk.rent_v2.service.CarService;
import mk.finki.ukim.mk.rent_v2.service.LocationService;
import mk.finki.ukim.mk.rent_v2.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/cars"})
public class CarsController {

    private final CarService carService;
    private final LocationService locationService;
    private final CarReviewService reviewService;


    public CarsController(CarService carService, LocationService locationService, CarReviewService reviewService) {
        this.carService = carService;
        this.locationService = locationService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String showCars(@RequestParam (required = false) String brand,
                           @RequestParam (required = false) CarType carType,
                           @RequestParam (defaultValue = "1") int pageNum,
                           @RequestParam (defaultValue = "10") int pageSize,
                           Model model) {
        Page<Car> page = this.carService.findPage(brand, carType, pageNum - 1, pageSize);
        model.addAttribute("cars", carService.listAll());
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("carTypes", CarType.values());
        model.addAttribute("page", page);
        return "cars";
    }

    @GetMapping("/add")
    public String createForm(Model model) {
        model.addAttribute("carTypes", CarType.values());
        model.addAttribute("cars", carService.listAll());
        model.addAttribute("locations", locationService.findAll());

        return "add-form";
    }

    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        this.carService.deleteById(id);

        return "redirect:/cars";
    }

    @PostMapping("/add")
    public String saveNewCar(@RequestParam String brand,
                             @RequestParam String carModel,
                             @RequestParam Long location,
                             @RequestParam int year,
                             @RequestParam int pricePerDay,
                             @RequestParam int rating,
                             @RequestParam CarType carType) {
        this.carService.save(brand, carModel, year, pricePerDay, true, location, carType, rating);
        return "redirect:/cars";
    }

    @GetMapping("/edit/{id}")
    public String getEditCar(@PathVariable Long id, Model model) {
        if (this.carService.findById(id).isPresent()) {
            Car car = this.carService.findById(id).get();
            model.addAttribute("car", car);
            model.addAttribute("locations", locationService.findAll());
            model.addAttribute("carTypes", CarType.values());
            return "edit-form";
        }
        return "redirect:/cars?error=CarNotFound";
    }

    @PostMapping("/edit/{id}")
    public String editCar(@PathVariable Long id,
                          @RequestParam String brand,
                          @RequestParam String carModel,
                          @RequestParam Long location,
                          @RequestParam int year,
                          @RequestParam int pricePerDay,
                          @RequestParam CarType carType,
                          Model model) {
        // update(Long id, String brand, String model, int year, double pricePerDay, boolean available, Long locationId, CarType carType);
        this.carService.update(id, brand, carModel, year, pricePerDay, true, location, carType);

        return "redirect:/cars";
    }
}

