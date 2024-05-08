package sftp.integration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sftp.integration.models.GenderAgeSummary;
import sftp.integration.repository.GenderAgeSummaryRepository;

@Service
public class GenderAgeSummaryServices {
    private final GenderAgeSummaryRepository gasRepo;

    @Autowired
    public GenderAgeSummaryServices(GenderAgeSummaryRepository genderageSummaryRepo) {
        this.gasRepo = genderageSummaryRepo;
    }

    public void save(GenderAgeSummary gs){
        gasRepo.save(gs);
    }
}
