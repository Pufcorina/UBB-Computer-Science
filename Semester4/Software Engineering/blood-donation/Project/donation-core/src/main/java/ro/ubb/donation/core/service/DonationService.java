package ro.ubb.donation.core.service;

import ro.ubb.donation.core.model.Donation;
import ro.ubb.donation.core.model.Result;
import ro.ubb.donation.core.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DonationService
{
    Optional<Donation> findDonation(int donationId);

    Optional<Donation> findDonationByUser( User user);

    List<Donation> findAll();

    List<Donation> findAllByStatus(String status);

    Donation updateDonation(int donationId, String status, String rejectionReason, boolean cancerPast5Years,
                            boolean recentTattos, boolean pregnantOrMenstruating, boolean surgeryPast6Months,
                            int pulse, int bloodPresure, float weight, String donationBeneficiary );

    Donation updateDonationStatus(int donationId, String status, String rejectionReason,String apointmentDate);

    Donation createDonation(User user, String status, boolean cancerPast5Years,
                            boolean recentTattos, boolean pregnantOrMenstruating, boolean surgeryPast6Months,
                            int pulse, int bloodPresure, float weight, String donationBeneficiary );

    void deleteDonation(int donationId);

    List<Donation> findDonationByUsername(String username);

    Donation updateDonationResult(int donationId, Result result);
}
