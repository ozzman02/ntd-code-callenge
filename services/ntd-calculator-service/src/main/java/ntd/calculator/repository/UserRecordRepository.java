package ntd.calculator.repository;

import ntd.calculator.domain.UserRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {

    @Query(value = "SELECT * FROM records WHERE user_id = ?1 order by created_date desc",
            countQuery = "SELECT COUNT(*) FROM records WHERE user_id = 1?", nativeQuery = true)
    Page<UserRecord> findUserRecordsByUserId(Long userId, Pageable pageable);

}
