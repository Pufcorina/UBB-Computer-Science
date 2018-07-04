package ro.ubb.web.converter;

import java.util.Collection;
import java.util.Set;

public interface Converter<Model, Dto> {

    Dto convertModelToDto(Model dto);

    Set<Dto> convertModelsToDtos(Collection<Model> model);

}
