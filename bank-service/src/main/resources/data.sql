INSERT INTO test.account (created_by,created_date,last_mod_date,modified_by,account_no,balance,version)
VALUES ('anonymousUser','2022-11-11 00:33:36','2022-11-11 15:02:01','200','100',1905.00,22);

INSERT INTO test.card (card_no,finger_print,first_name,last_name,pin,preferred_authentication_method,fk_account_id,failed_authenticate_count)
VALUES ('200','$2a$11$MhF4hVUyNS03mgdABCnUdOhsP7Hun.iLXWLbgNYX.tlSEEudCCDoe','mohsen','ahmadi','$2a$11$RCBKmXNiwSKlapxNrKEtEu2tfsSgPmCUmbsxY1PmnhyiXmtwU8IEy',0,1,0);
