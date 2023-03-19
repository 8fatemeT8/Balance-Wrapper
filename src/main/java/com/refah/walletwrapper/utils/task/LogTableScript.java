package com.refah.walletwrapper.utils.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;

@Component
public class LogTableScript implements StartUpTask {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream sqlFileInputStream = classLoader.getResourceAsStream("log-table.sql");
        BufferedReader sqlFileBufferedReader = new BufferedReader(new InputStreamReader(sqlFileInputStream));
        try {
            executeStatements(sqlFileBufferedReader, entityManager);
        } catch (Exception e) {
        }
    }

    @Transactional
    public void executeStatements(BufferedReader br, EntityManager entityManager) throws IOException {
        transactionTemplate.execute(t -> {
            String line;
            while (true) {
                try {
                    if ((line = br.readLine()) == null) break;
                } catch (IOException e) {
                    return null;
                }
                entityManager.createNativeQuery(line).executeUpdate();
            }
            t.flush();
            return null;
        });

    }
}
