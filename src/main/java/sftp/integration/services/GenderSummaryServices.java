package sftp.integration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sftp.integration.models.GenderSummary;
import sftp.integration.repository.GenderSummaryRepository;

@Service
public class GenderSummaryServices {
    private final GenderSummaryRepository gsRepo;

    @Autowired
    public GenderSummaryServices(GenderSummaryRepository genderSummaryRepo) {
        this.gsRepo = genderSummaryRepo;
    }

    public void save(GenderSummary gs){
        gsRepo.save(gs);
    }
}
