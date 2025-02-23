package com.example.demo.service.impl;

import com.example.demo.dto.DataBinRequest;
import com.example.demo.entity.threshold;
import com.example.demo.repository.DataBinRepository;
import com.example.demo.repository.WarningRepository;
import com.example.demo.service.DataBinService;
import com.example.demo.service.ThresholdService;
import com.example.demo.entity.databin;
import com.example.demo.entity.warning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.*;

@Service
public class DataBinServiceImpl implements DataBinService {

    private final DataBinRepository dataBinRepository;



    @Autowired
    private ThresholdService thresholdService;

    @Value("${spring.mail.username}") // Lấy giá trị từ cấu hình
    private String fromEmail;

    @Autowired
    private JavaMailSender mailSender;

    private final WarningRepository warningRepository;

    public DataBinServiceImpl(DataBinRepository dataBinRepository, WarningRepository warningRepository) {
        this.dataBinRepository = dataBinRepository;
        this.warningRepository = warningRepository;
    }
    // Lấy toàn bộ giá trị
    public List<databin> getAllData() {
        return dataBinRepository.findAll();
    }

    // Thêm một giá trị mới
    public databin addDataBin(DataBinRequest dataBinRequest) {
        databin dataBin = new databin();

        // Map fields from DataBinRequest to DataBin
        dataBin.setName(dataBinRequest.getName());
        dataBin.setIdbin(dataBinRequest.getIdbin());
        dataBin.setStatus(dataBinRequest.getStatus());
        dataBin.setStorage1(dataBinRequest.getStorage1());
        dataBin.setStorage2(dataBinRequest.getStorage2());
        dataBin.setStorage3(dataBinRequest.getStorage3());
        dataBin.setStorage4(dataBinRequest.getStorage4());
        dataBin.setCpu(dataBinRequest.getCpu());
        dataBin.setBattery(dataBinRequest.getBattery());
        dataBin.setLastupdate(dataBinRequest.getLastupdate());

        // Tìm threshold dựa trên idbin
        Optional<threshold> optionalThreshold = thresholdService.getThresholdByIdbin(dataBinRequest.getIdbin());

        // Nếu tìm thấy threshold, thực hiện kiểm tra cảnh báo
        if (optionalThreshold.isPresent()) {
            checkAndSendAlerts(dataBin, optionalThreshold.get());
        } else {
            System.out.println("No threshold found for idbin: " + dataBinRequest.getIdbin());
        }


        // Save the new DataBin instance (ID will be auto-generated)
        return dataBinRepository.save(dataBin);
    }

    private void checkAndSendAlerts(databin dataBin, threshold thresholds) {
        String emailTo = "21522446@gm.uit.edu.vn"; // Thay bằng email người nhận
        String warnings = ""; // Sử dụng String thay cho StringBuilder

        // Kiểm tra các cảnh báo về Storage
        if (dataBin.getStorage1() > thresholds.getStorage1()) {
            warnings += "Warning: Storage1 is almost full, currently at " + dataBin.getStorage1() + "% capacity.\n";
        }
        if (dataBin.getStorage2() > thresholds.getStorage2()) {
            warnings += "Warning: Storage2 is almost full, currently at " + dataBin.getStorage2() + "% capacity.\n";
        }
        if (dataBin.getStorage3() > thresholds.getStorage3()) {
            warnings += "Warning: Storage3 is almost full, currently at " + dataBin.getStorage3() + "% capacity.\n";
        }
        if (dataBin.getStorage4() > thresholds.getStorage4()) {
            warnings += "Warning: Storage4 is almost full, currently at " + dataBin.getStorage4() + "% capacity.\n";
        }

        // Kiểm tra cảnh báo về Battery
        if (dataBin.getBattery() < thresholds.getBattery()) {
            warnings += "Warning: Battery is running low, currently at " + dataBin.getBattery() + "%.\n";
        }




        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        // Nếu có cảnh báo, gửi email
        if (!warnings.isEmpty()) {
            // Tạo tiêu đề cho email
            String subject = "SmartBin in " + dataBin.getName() + " with id: " + dataBin.getIdbin() + " Warning Notifications";

            // Gửi email thông báo tất cả cảnh báo
            sendEmail(emailTo, subject, warnings);

            // Lưu lại cảnh báo
            saveWarning(dataBin.getName(),warnings,formattedDateTime,dataBin.getIdbin());

            // Log các cảnh báo (tuỳ chọn)
            System.out.println("Warnings detected:\n" + warnings);
        }
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(fromEmail);
        mailSender.send(message);
    }

    private void saveWarning(String name, String message , String time , Integer idbin ) {
        warning WarningData = new warning();
        WarningData.setIdbin(idbin);
        WarningData.setTime(time);
        WarningData.setMessage(message);
        WarningData.setNamebin(name);

        warningRepository.save(WarningData);
    }

//    private void saveEmailToDatabase(String to, String subject, String text) {
//        // Tạo đối tượng email để lưu vào database
//        email emailEntity = new email();
//        emailEntity.setTo(to);
//        emailEntity.setSubject(subject);
//        emailEntity.setText(text);
//
//
//        // Lưu vào database thông qua repository
//        EmailRepository.save(emailEntity);
//    }

    // Lấy giá trị gần đây nhất
    public databin getLatestData() {

        return dataBinRepository.findLatestData();
    }
}
