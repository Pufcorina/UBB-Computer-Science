package ro.ubb.donation.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.donation.core.model.Donation;
import ro.ubb.donation.web.dto.DonationDto;

@Component
public class DonationConverter implements Converter<Donation, DonationDto> {
    @Override
    public Donation convertDtoToModel(DonationDto donationDto) {
        return Donation.builder()
                .donation_id(donationDto.getDonation_id())
                .status(donationDto.getStatus())
                .donationBeneficiary(donationDto.getDonationBeneficiary())
                .weight(donationDto.getWeight())
                .bloodPressure(donationDto.getBloodPressure())
                .pulse(donationDto.getPulse())
                .surgeryPast6Months(donationDto.isSurgeryPast6Months())
                .pregnantOrMenstruating(donationDto.isPregnantOrMenstruating())
                .recentTattoos(donationDto.isRecentTattoos())
                .cancerPast5Years(donationDto.isCancerPast5Years())
                .rejectionReason(donationDto.getRejectionReason())
                .build();
    }

    @Override
    public DonationDto convertModelToDto(Donation donation) {
        return DonationDto.builder()
                .donation_id(donation.getDonation_id())
                .status(donation.getStatus())
                .donationBeneficiary(donation.getDonationBeneficiary())
                .weight(donation.getWeight())
                .bloodPressure(donation.getBloodPressure())
                .pulse(donation.getPulse())
                .surgeryPast6Months(donation.isSurgeryPast6Months())
                .pregnantOrMenstruating(donation.isPregnantOrMenstruating())
                .recentTattoos(donation.isRecentTattoos())
                .cancerPast5Years(donation.isCancerPast5Years())
                .rejectionReason(donation.getRejectionReason())
                .build();
    }
}
