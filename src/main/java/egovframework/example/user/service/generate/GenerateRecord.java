package egovframework.example.user.service.generate;
import java.util.Random;

public class GenerateRecord {
    public static void main(String[] args) {
        StringBuilder sqlScript = new StringBuilder();
        int numRecords = 100;

        for (int i = 1; i <= numRecords; i++) {
            sqlScript.append(generateSqlInsert(i));
        }

        System.out.println(sqlScript.toString());
    }

    private static String generateSqlInsert(int index) {
        String userId = generateUserId(index);
        String userSeCode = generateUserSeCode(index);
        String userNm = generateUserNm(index);
        String email = generateEmail(index);
        String sexdstn = generateSexdstn();
        int stGrade = generateStGrade();
        int stClass = generateStClass();

        return String.format(
                "INSERT INTO COMTNMBER (" +
                        "USER_ID, USER_SE_CODE, PASSWORD, USER_NM, PHOTO_STRE_FILE_NM, " +
                        "EMAIL_ADRES, SEXDSTN, CONFM_AT, ST_GRADE, ST_CLASS, " +
                        "TEACHER_SE_CODE, SCHUL_CODE, FRST_REGIST_PNTTM, DN, BRTHDY" +
                        ") VALUES (" +
                        "'%s', '%s', 'test123', '%s', NULL, " +
                        "'%s', '%s', 'N', %d, %d, " +
                        "'N', 'test', NOW(), NULL, '20100101'" +
                        ");\n",
                userId, userSeCode, userNm, email, sexdstn, stGrade, stClass
        );
    }

    private static String generateUserId(int index) {
        return String.format("USRCNFRM_%011d", index);
    }

    private static String generateUserSeCode(int index) {
        return String.format("student%03d", index);
    }

    private static String generateUserNm(int index) {
        return String.format("테스트학생%03d", index);
    }

    private static String generateEmail(int index) {
        return String.format("gkrtod%03d@gne.co.kr", index);
    }

    private static String generateSexdstn() {
        Random random = new Random();
        return random.nextBoolean() ? "M" : "F";
    }

    private static int generateStGrade() {
        Random random = new Random();
        return random.nextInt(3) + 1; // 1, 2, 3 중 하나
    }

    private static int generateStClass() {
        Random random = new Random();
        return random.nextInt(3) + 1; // 1, 2, 3 중 하나
    }
}