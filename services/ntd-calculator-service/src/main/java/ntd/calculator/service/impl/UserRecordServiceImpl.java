package ntd.calculator.service.impl;

import lombok.RequiredArgsConstructor;
import ntd.calculator.domain.UserRecord;
import ntd.calculator.dto.UserRecordDto;
import ntd.calculator.mapper.UserRecordMapper;
import ntd.calculator.repository.UserRecordRepository;
import ntd.calculator.service.UserRecordService;
import ntd.calculator.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRecordServiceImpl implements UserRecordService {

    private final UserRecordRepository userRecordRepository;

    private final UserService userService;

    @Override
    public UserRecord save(UserRecord userRecord) {
        return userRecordRepository.save(userRecord);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserRecordDto> findByUser(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRecordRepository.findByUser(userService
                        .findByUsername(authentication.getName()), pageable)
                .map(UserRecordMapper::mapToUserRecordDto);
    }

}
