package egovframework.example.cmmn.service;

import java.util.HashMap;

public final class Constant {
    private Constant() {}

    public static final HashMap<String, String> schulGradeCode = new HashMap<String, String>() {{
        put("SCH_01", "유치원");
        put("SCH_02", "초등학교");
        put("SCH_03", "중학교");
        put("SCH_04", "고등학교");
        put("SCH_05", "특수학교");
        put("SCH_99", "예외");
    }};

    public static final HashMap<String, String> gradeCode = new HashMap<String, String>() {{
        put("GRADE01", "1학년");
        put("GRADE02", "2학년");
        put("GRADE03", "3학년");
        put("GRADE04", "4학년");
        put("GRADE05", "5학년");
        put("GRADE06", "6학년");
    }};

    public static final HashMap<String, String> grade = new HashMap<String, String>() {{
        put("1", "GRADE01");
        put("2", "GRADE02");
        put("3", "GRADE03");
        put("4", "GRADE04");
        put("5", "GRADE05");
        put("6", "GRADE06");
    }};
}
