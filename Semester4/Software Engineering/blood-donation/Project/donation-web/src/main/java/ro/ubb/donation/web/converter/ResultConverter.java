package ro.ubb.donation.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.donation.core.model.Result;
import ro.ubb.donation.web.dto.ResultDto;

@Component
public class ResultConverter extends AbstractConverter<Result, ResultDto> implements Converter<Result, ResultDto>{
    @Override
    public Result convertDtoToModel(ResultDto resultDto) {
        return Result.builder()
                .illnessDiscovered( resultDto.isIllnessDiscovered() )
                .illnessInfo( resultDto.getIllnessInfo() )
                .build();
    }

    @Override
    public ResultDto convertModelToDto(Result result) {
        return ResultDto.builder()
                .appointmentDate( result.getDonation().getAppointment_date().toString() )
                .illnessInfo( result.getIllnessInfo() )
                .illnessDiscovered( result.isIllnessDiscovered())
                .result_pdf(result.getResultPdf())
                .build();
    }
}
