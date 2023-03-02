package IO.SampleWeek3_Transaction.backup.repository;

import IO.SampleWeek3_Transaction.backup.entity.BackupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackupMemberRepository extends JpaRepository<BackupMember, Long> {
}
