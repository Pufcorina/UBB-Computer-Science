package converter;

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
