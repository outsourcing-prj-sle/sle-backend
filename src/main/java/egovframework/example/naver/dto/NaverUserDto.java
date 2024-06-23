package egovframework.example.naver.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

@Data
public class NaverUserDto {
    /**
     * {
     *       "primaryEmail" : "tlqjach-stu102@gne.go.kr"
     *     , "sid" : "Ng8ggxnx2yCWYJmgm5L0fOqprybnJ4ix2aq9B72n"
     *     , "userType" : "stu"
     *     , "name" : {
     *           "givenName" : "은찬"
     *         , "familyName" : "손"
     *         , "fullName" : "은찬 손"
     *     }
     *     , "thumbnailPhotoUrl" : "https://whalespace.ssl.phinf.net/MjAyMzA3MDVfMTUz/MDAxNjg4NTI4NzE3MDE2.ylMuOcV_fyHeReD3QUxxKTFPROEFkPELqLayu6s3aEog.MV7QxJw1O7152upB5WugHuQ--sfoEGOgqROC5gCp4EMg.PNG/androidimg_03.png"
     *     , "customField" : null
     *     , "space" : {
     *           "name" : "경남시범초등학교"
     *         , "code" : "S333333"
     *     }
     *     , "customer" : {
     *           "name" : "경상남도교육청"
     *         , "uid" : "jgJDiUP2hVdsRPngqBzipB"
     *         , "type" : "EDU_OFF"
     *         , "customerDomain" : "gne.go.kr"
     *     }
     *     , "orgunit" : {
     *           "id" : "282650"
     *         , "name" : "1반"
     *         , "orgUnitPath" : "/경남시범초등학교/학생/3학년/1반"
     *     }
     *     , "userOrgunits" : [
     *         {
     *               "id" : "4476393"
     *             , "isPrimary" : true
     *             , "orgunit" : {
     *                   "id" : "282650"
     *                 , "name" : "1반"
     *                 , "orgUnitPath" : "/경남시범초등학교/학생/3학년/1반"
     *             }
     *         }
     *     ]
     *     , "relation" : [
     *         {
     *               "type" : "parent"
     *             , "relationUser" : {
     *                   "primaryEmail" : "tlqjach-par102@gne.go.kr"
     *                 , "sid" : "5JWzkAoYlgfEzbqqGY7yf30rr0oqgJt8xwmjNeOo"
     *                 , "userType" : "par"
     *                 , "name" : {
     *                       "givenName" : "부모2"
     *                     , "familyName" : "초"
     *                     , "fullName" : "부모2 초"
     *                 }
     *                 , "orgUnitPath" : "/경남시범초등학교/학부모/1학년/1반"
     *                 , "space" : {
     *                       "name" : "경남시범초등학교"
     *                     , "code" : "S333333"
     *                 }
     *                 , "sid_v1" : "ojrdizkfmfjfgotjpfwvmsdmne3emzlsofkecs3rkrxe4vq"
     *             }
     *         }
     *         , {
     *               "type" : "parent"
     *             , "relationUser" : {
     *                   "primaryEmail" : "tlqjach-par101@gne.go.kr"
     *                 , "sid" : "Bgyv00eMBptjvx7KrrWkigNEKY9D9LUxevWMxex8"
     *                 , "userType" : "par"
     *                 , "name" : {
     *                       "givenName" : "부모1"
     *                     , "familyName" : "초"
     *                     , "fullName" : "부모1 초"
     *                 }
     *                 , "orgUnitPath" : "/경남시범초등학교/학부모/1학년/1반"
     *                 , "space" : {
     *                       "name" : "경남시범초등학교"
     *                     , "code" : "S333333"
     *                 }
     *                 , "sid_v1" : "ojhheubwk5eheotjpfwvmsdmne3emzlsofkecs3rkrxe4vq"
     *             }
     *         }
     *         , {
     *               "type" : "parent"
     *             , "relationUser" : {
     *                   "primaryEmail" : "tlqjach-par106@gne.go.kr"
     *                 , "sid" : "39j1aBJBp7FBNkYJJ07wTyaGNooGZ6u4BEkWbA0Z"
     *                 , "userType" : "par"
     *                 , "name" : {
     *                       "givenName" : "부모6"
     *                     , "familyName" : "초"
     *                     , "fullName" : "부모6 초"
     *                 }
     *                 , "orgUnitPath" : "/경남시범초등학교/학부모/1학년/1반"
     *                 , "space" : {
     *                       "name" : "경남시범초등학교"
     *                     , "code" : "S333333"
     *                 }
     *                 , "sid_v1" : "g5dhs3cnkb2dmotjpfwvmsdmne3emzlsofkecs3rkrxe4vq"
     *             }
     *         }
     *     ]
     *     , "sid_v1" : "nffvez2zkrggyotjpfwvmsdmne3emzlsofkecs3rkrxe4vq"
     * }
     */
    /**
     * 선생님
     * {
     *       "primaryEmail" : "tlqjach11@gne.go.kr"
     *     , "sid" : "Jvb1NvMlkdTW3oEwad6lSnyWyB50mnuvb4aXb3Mp"
     *     , "userType" : "tea"
     *     ,"name":{"givenName":"1반 선생님","familyName":"1학년","fullName":"1반 선생님 1학년"},
     *     "thumbnailPhotoUrl":"https://whalespace.ssl.phinf.net/MjAyMzA3MjRfMTIg/MDAxNjkwMTU3NjMzNTg1.I2kikBv00DBz6Do-hfDVDsFt-v2F9C-55o6gw7Kjskog.nwdIZlwYB7LBupARoUB68IDzQu6LyH0dIfVwBsKjingg.JPEG/sonagi.jpg",
     *     "customField":{"number":null},
     *     "space":{"name":"경남시범초등학교","code":"S333333"},
     *     "customer":{
     *          "name":"경상남도교육청",
     *          "uid":"jgJDiUP2hVdsRPngqBzipB",
     *          "type":"EDU_OFF",
     *          "customerDomain":"gne.go.kr"
     *     },
     *     "orgunit":{"id":"225673","name":"1반","orgUnitPath":"/경남시범초등학교/교직원/1학년/1반"},
     *     "userOrgunits":
     *          [
     *              {
     *                  "id":"4015450",
     *                  "isPrimary":true,
     *                  "orgunit":
     *                      {
     *                          "id":"225673",
     *                          "name":"1반",
     *                          "orgUnitPath":"/경남시범초등학교/교직원/1학년/1반"
 *                          }
*                    }
*                ],
*             "relation":[],
     *        "sid_v1":"ozxxayrtm53vkotjpfwvmsdmne3emzlsofkecs3rkrxe4vq"}
     */
    private String primaryEmail;
    private String sid;
    private String userType; // 학생: stu, 선생님: tea
    private String thumbnailPhotoUrl;

    public void setUserType(String userType) {
        if(userType.equals("stu")) this.userType = "N";
        if(userType.equals("tea")) this.userType = "Y";
    }
}

