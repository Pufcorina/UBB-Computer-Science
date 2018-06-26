package ro.ubb.web.converter;

import java.util.Collection;
import java.util.Set;

public interface Converter<Model, Dto> {

    public Dto convertModelToDto(Model model);

    public Set<Dto> convertModelsToDtos(Collection<Model> models);
}
