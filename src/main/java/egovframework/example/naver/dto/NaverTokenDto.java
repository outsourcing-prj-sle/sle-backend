package egovframework.example.naver.dto;

import lombok.Data;

@Data
public class NaverTokenDto {
    /**
     * 학생
     * {
     *       "access_token" : "rioEYMJaeXQAoBbkZ5aU4ZE4vwsuR-vNS_b_Ga50Sd-uRGDDKhRyEWugwYUXYX501X9LrNjfgQaSIInJHc0A7PWytdkvEKPiqWF2mziJ_g0UkdjQijxoRNGVk_iTu4YBjTVoh3ZvjADvu3vpvVmHhb45zC8I9JtL9-VVgY4h1HhT8-v_rSkJG6Hs-LtpjRvYe6Ajqqwrk43NdKoZvSLa9XeAlfkDe6ntxvwSIRoaKNZ_xEk9gh5XPDhuX7fty8w15P4w0Gjej-0VzwKzsoCg3SAujpLy5fu0EKWftA8LqjYXPEmuEYKgY-5X7nCbtzOax-hvUorrbSHXn5k-hm3ip0oFNNMuuWWI-R5-Ly4QCpnmVxv8hSFYCimgW9gLtMbykXGNAZfAno2kWm6Ndmqpx2VxSV7B5Z1F0cMhQ0kvjTwka_pVGpr8_ZN1Uu0HjW0wqPSuP9WnvCM8uQFBO2tple5NdBInrV6--mz3ncaK5g_OOYZJtLdSowmM47tAWAdePYs71w27LyhyG7Lg_kCVWiXrCQW5SvC7qQMkCDMYohDtWdm3M1Xe2VJdlb8d8KmHs_TPVp-OVNAOqsMWezwXfcjpNM5_r2lS3hXdprceMMhJidFcYs7uvH2q77tns6mEU-9pP9nMM8eAr1tUdzksfahaTC1tEYbUXyQ97baDtb9y_qz5T8Ln6CAn80njnr56aOquSU6h5qO6savCDL8yzSIPYG51GTdMBaU8AFzQ0dyiaCvrDVaKKyZlLp1PLpVLa26htxNB6tQFz_otIaDtM-UBOm9NQf1WGzdppwYuJhxq7cgEL9BeLpA3RztziUA67Wdl2B_57h_CJ5SN8OFhq0shyRgoH8KL84aTQzsh6O9-NWgFkPjCDb3I9KvfDg2b"
     *     , "refresh_token" : "gwT1oQMzSGTQ+aPPKIUFt/ivdf06V92e5395xtFf0Bi3993PwCuRl6xXH6aEG97YMfdmiLKwahKN4NNwHm8g4Exy00G9nUV/jdQn7++2gqSf2gXD3AW+m0ajNA/VUM6h37jGeW4TWAl5h5CG5cSEqjaZ44PM78f6jsE/QPZsNkTQmBqKQad1okdOydSuwFcoJTOYW16tjtzi0YV+bAgpdiTwrAcDXavVXViaUJk9HKYdXFDSo7L4ku3wyUguUaALmdXimvwJykJ0BQSxXjVyVw=="
     *     , "token_type" : "Bearer"
     *     , "expires_in" : "21600"
     * }
     */
    /**
     * 교사
     * {
     *       "access_token" : "5yfzwcoM4tOS1ygHIavs8o_CBpkmE4ymrLvBHR9JYqqTou65Nw23hH7Or239UhOb-XbWqVice4SToEMlIDb5NS5WYIUT-a_yheHVgkkowTLogUHJ-kysviQ4iQfVBRPUb1JSQyUUzWxQsCh1EIyo-LjkrmtXJIceFsw8YDawKv1Ci21SOcKZZ_zlPWA_UlCAtjagJ3VTUxdH6-PVeuYjTRwFE0I-w4Pgi7Q09zzQ0TriKTdYvsZfC1h0RNHHEp5Ob07tMFlgDwbYIEd4GGabkvZUwoc6BBQIuUm61kEWZUUI_F_RzW5OeTBKgkUCxhPWJD-Uq-JV2-_T6Y4lAQI2lyDZhSyaaB0bVND6ueQbGJNkL86PEbV2mKmGiTRKQOTov2QX_2eztZaYAF1CqsUy63WFldsKubJ93xUWwH1Y2MvwXmZIKrs99c6abA1icuJj-wmzBi6ugf-FrGKzJOtup4FUZMjycEZZWmCblTKemdd6NPblfsBFhwpZyTMM8U6xkWTuUQGQ-i7BK_24eJgGokR3mkbLZRLi8A5ht5SZKuo-bjc1QoSRqHSuh-kwc2r9KLZ1r-65DU0iaM_jQMg1lltRSA6vApF4aP1-317nhk0Wm4SYQckyN-KMb3GnaSQebgef4seBZsIHkoraFpLhkMYAN4S656HJ18IU0d1CoFaIfWaQxVx2rUr8LWq6gvrVNoFPPmwTWMsar6CD2km18XtsmqO_UIUqzbffxK0n-OY5CHnFW-TF5pntoMxIQqr2KwgzmeHdoueTziQ5lk-NvQDXjb3KKSngKtBFtWXYihn8lUEY9Ugz6h-ANFaUUfaIytKZfqNsXpWPeth2feJz5Y1pr7z6SdOnU1UC-lJVQPQZDICwY7tFijgWlpHQaSg_"
     *     , "refresh_token" : "8E81zbWyJUTRjUUdu+eiNfvMsam5ilpwCT6ZDwoAUOC0i7VypnIZoW0SV1nFheG6RoeHJJ376eLbI9LBq/SjeSyPFgCdFnXbHM2kqJOGan2KoYnJLcWhOL7al09m1nZ+RCEgkVLXXA+Sq751dQ0nkodxIkgILN8puhQqFsFE6UtUZQm4NQ/2rrQPjkG2nVpdiOgUTX9uIdO8Q7BrYqY1MxM5ivZlmwiGgUEKBBeKZ5bH6WrssecMXmWt1IocSiFSAg+DcW0x9ImzXfErL35Cig=="
     *     , "token_type":"Bearer"
     *     , "expires_in":"21600"
     * }
     */
    private String access_token;
    private String refresh_token;
    private int expires_in;
    private String token_type;
}

