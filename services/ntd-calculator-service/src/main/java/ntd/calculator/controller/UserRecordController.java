package ntd.calculator.controller;

import lombok.RequiredArgsConstructor;
import ntd.calculator.dto.UserRecordDto;
import ntd.calculator.service.UserRecordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ntd.calculator.constants.ApplicationConstants.PAGE_SIZE;
import static ntd.calculator.constants.ApplicationConstants.USER_RECORDS_URL;

@RestController
@RequestMapping(USER_RECORDS_URL)
@RequiredArgsConstructor
public class UserRecordController {

    private final UserRecordService userRecordService;

    @GetMapping("/{id}/page/{page}")
    public ResponseEntity<Page<UserRecordDto>> getUserRecords(@PathVariable Long id, @PathVariable Integer page) {
        return new ResponseEntity<>(userRecordService.findAllUserRecordsByUserId(id,
                PageRequest.of(page, PAGE_SIZE)), HttpStatus.OK);
    }
}
