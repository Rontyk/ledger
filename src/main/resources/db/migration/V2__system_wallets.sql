INSERT INTO _wallet (created_at, updated_at, money, wallet_type, system_wallet_code, allow_negative, currency)
VALUES
    (now(), now(), 0, 'SYSTEM', 'EXTERNAL', TRUE,  'KZT'),
    (now(), now(), 0, 'SYSTEM', 'FEES',     FALSE, 'KZT');
