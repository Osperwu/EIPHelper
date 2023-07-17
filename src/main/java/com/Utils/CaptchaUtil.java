package com.Utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;

public class CaptchaUtil {
    public static String dataPath;

    public static String getContent(File f) {
        ITesseract inst = new Tesseract();
        inst.setDatapath(dataPath);
        inst.setLanguage("eng");
        try {
            String content = inst.doOCR(f);
            System.out.println("驗證碼辨識結果 : " + content);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
