package ro.ubb.lab7.web.converter;

import ro.ubb.lab7.core.domain.BaseEntity;
import ro.ubb.lab7.web.dto.BaseDto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseConverter<Model, Dto>
        implements Converter<Model, Dto>{

    public Set<Dto> convertModelsToDtos(Collection<Model> models){
        return models.stream()
                .map(m -> convertModelToDto(m))
                .collect(Collectors.toSet());
    }
}
