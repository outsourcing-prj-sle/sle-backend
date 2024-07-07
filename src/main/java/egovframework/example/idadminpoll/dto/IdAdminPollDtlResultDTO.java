package egovframework.example.idadminpoll.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdAdminPollDtlResultDTO<T> {
    private int pageNo;
    private int recordCount;
    private int totalCount;
    private List<T> pollDtlList;
}
