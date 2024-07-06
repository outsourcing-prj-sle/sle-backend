package egovframework.example.cmmn.service;

import egovframework.example.admin.system.model.SiteManageDTO;
import lombok.*;

@Getter
@Setter
public class PaginationVO {
    /**
     * 현재 페이지 넘버
     */
    private int pageNo = 1;

    /**
     * 한 페이지에 보여줄 데이터 수
     */
    private int recordCount = 10;

    /**
     * 전체 데이터 수
     */
    private int totalCount;

    /**
     * 현재 페이지 첫번쨰 수
     */
    private int firstIndex;

    public int getFirstIndex() {

        return (this.pageNo - 1) * this.recordCount;
    }
}
