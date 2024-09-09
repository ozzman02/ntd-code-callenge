package ntd.calculator.service.impl;

import lombok.RequiredArgsConstructor;
import ntd.calculator.domain.UserRecord;
import ntd.calculator.repository.UserRecordRepository;
import ntd.calculator.service.UserRecordService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRecordServiceImpl implements UserRecordService {

    private final UserRecordRepository userRecordRepository;

    @Override
    public UserRecord save(UserRecord userRecord) {
        return userRecordRepository.save(userRecord);
    }

}
