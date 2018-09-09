package ro.ubb.donation.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.donation.core.model.Result;
import ro.ubb.donation.core.repository.ResultRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultRepository resultRepository;

    @Override
    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public Optional<Result> findResult(int id) {
        return resultRepository.findById(id);
    }

    @Override
    public Optional<Result> findResultByCnp(String cnp) {
        List<Result> results = resultRepository.findAll();
        results = results.stream().filter(result -> result.getDonation().getUser().getProfile().getCnp().equals(cnp)).collect(Collectors.toList());
        if (results.isEmpty())
            return Optional.empty();
        else
            return Optional.of(results.get(0));
    }

    @Override
    @Transactional
    public Result updateResult(int resultId, boolean illnessDiscovered, String illnessInfo, String resultPdf) {
        Optional<Result> result = resultRepository.findById(resultId);
        if (result.isPresent()) {
            Result r = result.get();
            r.setIllnessDiscovered(illnessDiscovered);
            r.setIllnessInfo(illnessInfo);
            r.setResultPdf(resultPdf);
        }
        return result.orElse(null);
    }

    @Override
    public Result createResult(boolean illnessDiscovered, String illnessInfo, String resultPdf) {
        Result result = Result.builder()
                .illnessDiscovered(illnessDiscovered)
                .illnessInfo(illnessInfo)
                .resultPdf(resultPdf)
                .build();
        this.resultRepository.save(result);
        return result;
    }

    @Override
    @Transactional
    public Result deleteResult(int resultId) {
        Optional<Result> resultOptional = this.resultRepository.findById(resultId);
        if (resultOptional.isPresent()){
            this.resultRepository.deleteById(resultId);
            return resultOptional.get();
        }
        return null;
    }
}
