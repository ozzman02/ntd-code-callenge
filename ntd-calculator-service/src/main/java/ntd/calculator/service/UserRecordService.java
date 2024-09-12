package ntd.calculator.service;

import ntd.calculator.domain.UserRecord;
import ntd.calculator.dto.UserRecordDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRecordService {

    UserRecord save(UserRecord userRecord);

    Page<UserRecordDto> findAllUserRecordsByUserId(Long userId, Pageable pageable);

}
