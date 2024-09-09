INSERT INTO records (
    operation_id,
    user_id,
    amount,
    user_balance,
    operation_value,
    operation_response,
    created_date
) VALUES(1, 3, 1, 4999, '1+1', 2, now());

INSERT INTO records (
    operation_id,
    user_id,
    amount,
    user_balance,
    operation_value,
    operation_response,
    created_date
) VALUES(2, 3, 5, 4994, '10+10+10+10+10+10+10', 70, now());

INSERT INTO records (
    operation_id,
    user_id,
    amount,
    user_balance,
    operation_value,
    operation_response,
    created_date
) VALUES(3, 3, 10, 4984, '10+10+10+10+10+10*10', 600, now());

INSERT INTO records (
    operation_id,
    user_id,
    amount,
    user_balance,
    operation_value,
    operation_response,
    created_date
) VALUES(4, 3, 15, 4969, '1500/0', null, now());