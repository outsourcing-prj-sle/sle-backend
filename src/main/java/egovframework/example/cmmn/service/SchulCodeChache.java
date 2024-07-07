package egovframework.example.cmmn.service;

import egovframework.example.cmmn.CustomException;
import egovframework.example.naver.dto.GneInfoDto;
import egovframework.example.naver.dto.GneSchulDTO;
import egovframework.example.naver.service.NaverService;
import egovframework.example.naver.service.impl.NaverServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

@Component
public class SchulCodeChache {
    @Resource(name = "naverServiceImpl")
    private NaverService naverService;

    private GneInfoDto<List<GneSchulDTO>> cache = new GneInfoDto<>();

    public GneInfoDto<List<GneSchulDTO>> getSchulMap() {
        if(this.cache == null || !this.cache.isSuccess() || this.cache.getData() == null) {
            this.cache = naverService.procGneSchulInfo();
        }

        return this.cache;
    }
}
