package egovframework.example.idadminidtt.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdAdminIdttResultDTO<T> {
    private int pageNo;
    private int limit;
    private int totalCount;
    private ArrayList<T> idttList;
}
