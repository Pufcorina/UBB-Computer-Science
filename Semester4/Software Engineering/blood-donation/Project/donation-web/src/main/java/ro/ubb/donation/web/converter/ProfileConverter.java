package ro.ubb.donation.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.donation.core.model.Profile;
import ro.ubb.donation.web.dto.ProfileDto;

@Component
public class ProfileConverter implements Converter<Profile, ProfileDto> {
    @Override
    public Profile convertDtoToModel(ProfileDto profileDto) {
        return Profile.builder()
                .firstName(profileDto.getFirstName())
                .lastName(profileDto.getFirstName())
                .birthDate(profileDto.getDateOfBirth())
                .gender(profileDto.getGender())
                .bloodType(profileDto.getBloodGroup())
                .gender(profileDto.getGender())
                .cnp(profileDto.getCnp())
                .phone(profileDto.getPhone())
                .allergies(profileDto.getAllergies())
                .diseases(profileDto.getDiseases())
                .chronicIllness(profileDto.getChronicIllness())
                .rh(profileDto.getRh())
                .email(profileDto.getEmail())
                .build();
    }

    @Override
    public ProfileDto convertModelToDto(Profile profile) {
        return ProfileDto.builder()
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .dateOfBirth(profile.getBirthDate())
                .gender(profile.getGender())
                .bloodGroup(profile.getBloodType())
                .gender(profile.getGender())
                .cnp(profile.getCnp())
                .phone(profile.getPhone())
                .allergies(profile.getAllergies())
                .diseases(profile.getDiseases())
                .chronicIllness(profile.getChronicIllness())
                .rh(profile.getRh())
                .email(profile.getEmail())
                .build();
    }
}
