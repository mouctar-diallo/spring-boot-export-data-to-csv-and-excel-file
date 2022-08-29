package com.moucdev.app.helpers;

import com.moucdev.app.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Slf4j
@Service
public class CsvExportHelper {

    private static final Logger logger = LoggerFactory.getLogger(CsvExportHelper.class);

    public void exportUserDataTocsv(List<User> users, Writer writer) throws IOException {

        try {
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (User user: users) {
                csvPrinter.printRecord(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
            }
        } catch (IOException ex) {
            log.error("Error to export Data csv " + ex.getMessage());
        }
    }

}
