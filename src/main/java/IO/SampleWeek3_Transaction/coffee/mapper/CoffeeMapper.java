package IO.SampleWeek3_Transaction.coffee.mapper;

import IO.SampleWeek3_Transaction.coffee.dto.CoffeePatchDto;
import IO.SampleWeek3_Transaction.coffee.dto.CoffeePostDto;
import IO.SampleWeek3_Transaction.coffee.dto.CoffeeResponseDto;
import IO.SampleWeek3_Transaction.coffee.entity.Coffee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
    List<CoffeeResponseDto> coffeesToCoffeeResponseDtos(List<Coffee> coffees);
}
