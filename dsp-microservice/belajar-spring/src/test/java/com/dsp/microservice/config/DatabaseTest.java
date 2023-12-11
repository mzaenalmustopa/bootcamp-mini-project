package com.dsp.microservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DatabaseTest {

    @Test
    void testSingleton() {
        Database database1 = Database.getInstance();
        Database database2 = Database.getInstance();
        //Database database3 = new Database();

        assertNotNull(database1);
        assertNotNull(database2);

        assertSame(database1, database2);
    }
}