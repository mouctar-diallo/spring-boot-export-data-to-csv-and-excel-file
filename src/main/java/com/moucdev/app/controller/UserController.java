package com.moucdev.app.controller;

import com.moucdev.app.entities.User;
import com.moucdev.app.helpers.CsvExportHelper;
import com.moucdev.app.helpers.ExportDataInExcelFile;
import com.moucdev.app.service.user.UserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final CsvExportHelper csvExportHelper;
    private final ExportDataInExcelFile exportDataInExcelFile;

    public UserController (UserService userService, CsvExportHelper csvExportHelper,ExportDataInExcelFile exportDataInExcelFile) {
        this.userService = userService;
        this.csvExportHelper = csvExportHelper;
        this.exportDataInExcelFile = exportDataInExcelFile;
    }

    @GetMapping(value = "users/export-csv")
    public void userReportInPDF(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.addHeader("Content-Disposition","attachment; filename=\"user.csv\"");
        List<User> users = userService.findAll();

        csvExportHelper.exportUserDataTocsv(users, response.getWriter());

    }

    @GetMapping("users/export-excel")
    public ResponseEntity<InputStreamResource> downloadExcelFile() throws IOException {

        List<User> users = userService.findAll();
        ByteArrayInputStream inputStream = exportDataInExcelFile.exportExcelData(users);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=users.xlsx");

        return ResponseEntity.ok().headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(inputStream));
    }
}
