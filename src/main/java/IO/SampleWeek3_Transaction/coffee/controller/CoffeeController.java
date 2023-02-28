package IO.SampleWeek3_Transaction.coffee.controller;

import IO.SampleWeek3_Transaction.coffee.dto.CoffeePatchDto;
import IO.SampleWeek3_Transaction.coffee.dto.CoffeePostDto;
import IO.SampleWeek3_Transaction.coffee.entity.Coffee;
import IO.SampleWeek3_Transaction.coffee.mapper.CoffeeMapper;
import IO.SampleWeek3_Transaction.coffee.service.CoffeeService;
import IO.SampleWeek3_Transaction.dto.MultiResponseDto;
import IO.SampleWeek3_Transaction.dto.SingleResponseDto;
import IO.SampleWeek3_Transaction.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/coffees")
@Validated
public class CoffeeController {
    private final static String COFFEE_DEFAULT_URL = "/coffees";
    private CoffeeService coffeeService;
    private CoffeeMapper mapper;

    public CoffeeController(CoffeeService coffeeService, CoffeeMapper coffeeMapper) {
        this.coffeeService = coffeeService;
        this.mapper = coffeeMapper;
    }

    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto) {
        Coffee coffee = coffeeService.createCoffee(mapper.coffeePostDtoToCoffee(coffeePostDto));
        URI location = UriCreator.createUri(COFFEE_DEFAULT_URL, coffee.getCoffeeId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto) {
        coffeePatchDto.setCoffeeId(coffeeId);
        Coffee coffee = coffeeService.updateCoffee(mapper.coffeePatchDtoToCoffee(coffeePatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.coffeeToCoffeeResponseDto(coffee)),
                HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        Coffee coffee = coffeeService.findCoffee(coffeeId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.coffeeToCoffeeResponseDto(coffee)),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Coffee> pageCoffees = coffeeService.findCoffees(page - 1, size);
        List<Coffee> coffees = pageCoffees.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.coffeesToCoffeeResponseDtos(coffees),
                        pageCoffees),
                HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        coffeeService.deleteCoffee(coffeeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
