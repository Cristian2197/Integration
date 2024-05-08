package sftp.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sftp.integration.models.GenderAgeSummary;

public interface GenderAgeSummaryRepository extends JpaRepository<GenderAgeSummary, Long>{
    
}
