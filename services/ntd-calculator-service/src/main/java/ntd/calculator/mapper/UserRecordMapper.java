package ntd.calculator.mapper;

import ntd.calculator.domain.UserRecord;
import ntd.calculator.dto.UserRecordDto;

public class UserRecordMapper {

    public static UserRecordDto mapToUserRecordDto(UserRecord userRecord) {
        return UserRecordDto.builder()
                .id(userRecord.getId())
                .operationId(userRecord.getOperation().getId())
                .operationType(userRecord.getOperation().getOperationType())
                .userId(userRecord.getUser().getId())
                .username(userRecord.getUser().getUsername())
                .amount(userRecord.getAmount())
                .userBalance(userRecord.getUserBalance())
                .operationValue(userRecord.getOperationValue())
                .createdDate(userRecord.getCreatedDate())
                .operationResponse(userRecord.getOperationResponse())
                .build();
    }

}
