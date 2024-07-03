package egovframework.example.cmmn.service;

import egovframework.example.cmmn.CustomException;
import egovframework.example.naver.dto.GneInfoDto;
import egovframework.example.naver.dto.GneSchulDTO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

@Component
public class SchulCodeChache {
    private GneInfoDto<List<GneSchulDTO>> cache = new GneInfoDto<>();

    public GneInfoDto<List<GneSchulDTO>> getSchulMap() {

        return this.cache;
    }

    public void setSchulMap(GneInfoDto<List<GneSchulDTO>> dto) {
        try {
            BeanUtils.copyProperties(this.cache, dto);
        } catch (Exception e) {
            throw new CustomException("DTO 복사에 실패하였습니다.");
        }
    }
}
