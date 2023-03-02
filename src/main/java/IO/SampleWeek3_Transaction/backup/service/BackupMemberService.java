package IO.SampleWeek3_Transaction.backup.service;

import IO.SampleWeek3_Transaction.backup.entity.BackupMember;
import IO.SampleWeek3_Transaction.backup.repository.BackupMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BackupMemberService {
    private final BackupMemberRepository repository;

    @Transactional
    public void createBackupMember(BackupMember backupMember) {
        repository.save(backupMember);

        throw new RuntimeException("multi datasource rollback test");
    }
}
