package ro.ubb.donation.web.converter;

import ro.ubb.donation.core.model.Role;

import ro.ubb.donation.web.dto.RoleDto;


public class RoleConverter extends AbstractConverter<Role, RoleDto> implements Converter<Role, RoleDto> {

    @Override
    public Role convertDtoToModel(RoleDto roleDto) {
        return null;
    }

    @Override
    public RoleDto convertModelToDto(Role role) {
        return null;
    }
}
