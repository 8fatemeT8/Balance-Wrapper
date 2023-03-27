package com.refah.walletwrapper.utils.report;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.refah.walletwrapper.model.entity.User;
import com.refah.walletwrapper.utils.exception.ResponseException;
import com.refah.walletwrapper.utils.report.dtos.UserReportDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class ReportGenerator {

    public void getCsv(HttpServletResponse response, ArrayList<User> data, String companyName) {
        try {
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            PrintWriter writer = response.getWriter();
            response.setContentType("text/csv; charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + companyName + "_company.csv");
            List<UserReportDto> finalData = getUserDto(data);
            StatefulBeanToCsv<UserReportDto> bean = new StatefulBeanToCsvBuilder<UserReportDto>(writer)
                    .withSeparator(',')
                    .withOrderedResults(false)
                    .build();
            bean.write(finalData);
            writer.close();
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            throw new ResponseException(HttpStatus.BAD_REQUEST.value(), "در فرایند تهیه فایل خطایی رخ داده است");
        }
    }

    private List<UserReportDto> getUserDto(ArrayList<User> data) {
        List<UserReportDto> result = new ArrayList<>();
        data.forEach(it ->
                result.add(new UserReportDto(it.getFirstName(), it.getLastName(), it.getNationalCode(),
                        it.getMobileNumber(), it.isRegistered() ? "رجیستر شده" : "رجیستر نشده", it.getExcelDetail().getCompanyName(),
                        it.getWallet().getBalance() + "", it.getWallet())));
        return result;
    }
}
