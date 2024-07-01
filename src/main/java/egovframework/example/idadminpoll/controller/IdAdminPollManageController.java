package egovframework.example.idadminpoll.controller;

import egovframework.example.idadminidtt.service.IdAdminIdttManageService;
import egovframework.example.idadminpoll.service.IdAdminPollManageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/id-admin")
public class IdAdminPollManageController {
    @Resource(name = "idAdminPollManageService")
    private IdAdminPollManageService pollManageService;

    /**
     * 1. 설문관리
     *     1. Select
     *         1. pollNm (마음알기설문1, 마음알기설문2, …)
     *         2. 대상 (학교, 학년, 전체)
     *         3. date(startDate, endDate)
     * 	2. Insert
     * 		1. 학교, 학교급, 학년
     * 	2. Update
     * 		1. 학교, 학교급, 학년
     */
}
