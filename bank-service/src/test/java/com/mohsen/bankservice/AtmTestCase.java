package com.mohsen.bankservice;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public interface AtmTestCase {
    static final  String TEST_CASE_CARD_1 = "testcase/card-dto-1.json";
    static final  String TEST_CASE_REQ_TRANSACTION_DTO_1 = "testcase/req-transaction-dto-1.json";

    static String readJsonCase(String filename) throws IOException {
        return StreamUtils.copyToString(new ClassPathResource(filename).getInputStream(), Charset.defaultCharset());
    }
}
