package com.example.demo;

public interface QrCodeService {
    byte[] generateQrCodeImage(String text, int width, int height);
}