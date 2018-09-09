package ro.ubb.donation.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.donation.core.model.Address;
import ro.ubb.donation.web.dto.AddressDto;

@Component
public class AddressConverter implements Converter<Address, AddressDto> {
    @Override
    public Address convertDtoToModel(AddressDto addressDto) {
        return Address.builder()
                .currentCountry(addressDto.getCurrentCountry())
                .currentCity(addressDto.getCurrentCity())
                .country(addressDto.getCountry())
                .city(addressDto.getCity())
                .currentHomeAddress(addressDto.getCurrentHomeAddress())
                .homeAddress(addressDto.getHomeAddress())
                .build();
    }

    @Override
    public AddressDto convertModelToDto(Address address) {
        return AddressDto.builder()
                .city(address.getCity())
                .country(address.getCountry())
                .homeAddress(address.getHomeAddress())
                .currentHomeAddress(address.getCurrentHomeAddress())
                .currentCity(address.getCurrentCity())
                .currentCountry(address.getCurrentCountry())
                .build();
    }
}
