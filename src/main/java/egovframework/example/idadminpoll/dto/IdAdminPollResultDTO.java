package egovframework.example.idadminpoll.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdAdminPollResultDTO<T> {
    private int pageNo;
    private int recordCount;
    private int totalCount;
    private List<IdAdminPollDTO> pollList;
}
