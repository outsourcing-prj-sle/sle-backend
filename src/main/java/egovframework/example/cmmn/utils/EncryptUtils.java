package egovframework.example.cmmn.utils;

import egovframework.example.cmmn.CustomException;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class EncryptUtils {
    private static final String salt = "eureka";

    public static String getSHA256(String password) {
        byte result[] = null;

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update((password + salt).getBytes());
            result = md.digest();

            StringBuilder sb = new StringBuilder();
            for(byte b : result) {
                sb.append(String.format("%02xx", b));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new CustomException("암호화에 실패하였습니다.");
        }
    }
}
