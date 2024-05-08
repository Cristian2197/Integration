package sftp.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sftp.integration.models.GenderSummary;

public interface GenderSummaryRepository extends JpaRepository<GenderSummary, Long> {
}
