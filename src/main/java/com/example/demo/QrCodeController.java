package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QrCodeController {

    // الاعتماد على الواجهة (Interface) التي أنشأتها لإصدار الأوامر بشكل نظيف
    private final QrCodeService qrCodeService;

    // حقن الخدمة برمجياً عبر الـ Constructor
    public QrCodeController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    // استقبال البيانات من المتصفح وتوليد الـ QR بناءً عليها
    @GetMapping(value = "/generate-qr", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] createMyQr(
            @RequestParam String name,
            @RequestParam String job,
            @RequestParam String phone,
            @RequestParam String email
    ) {

        // تجهيز بيانات vCard العالمية لقراءة كاميرات الهواتف
        String vCardData = """
               BEGIN:VCARD
               VERSION:3.0
               FN:%s
               ORG:%s
               TEL:%s
               EMAIL:%s
               END:VCARD
               """.formatted(name, job, phone, email);

        // توليد صورة الـ QR بعرض وارتفاع 350 بكسل
        return qrCodeService.generateQrCodeImage(vCardData, 350, 350);
    }
}