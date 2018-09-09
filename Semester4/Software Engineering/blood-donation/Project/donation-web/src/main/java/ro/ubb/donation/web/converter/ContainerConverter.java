package ro.ubb.donation.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.donation.core.model.Container;
import ro.ubb.donation.web.dto.ContainerDto;

import java.util.Date;

@Component
public class ContainerConverter extends AbstractConverter<Container, ContainerDto> implements Converter<Container, ContainerDto> {
    @Override
    public Container convertDtoToModel(ContainerDto containerDto) {
        return Container.builder()
                .componentType( containerDto.getComponentType() )
                .rh( containerDto.getRh() )
                .bloodGroup( containerDto.getBloodGroup() )
                .expirationDate( new Date(  containerDto.getExpirationDate() ) )
                .build();
    }

    @Override
    public ContainerDto convertModelToDto(Container container) {
        return ContainerDto.builder()
                .bloodGroup( container.getBloodGroup() )
                .centerId( container.getCenterId().getId() )
                .componentType( container.getComponentType() )
                .expirationDate( container.getExpirationDate().toString() )
                .rh( container.getRh() )
                .build();
    }
}
