package egovframework.example.user.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdttLTResultDTO {
    ArrayList<HashMap<String, Object>> userPersonality;
    ArrayList<HashMap<String, Object>> classPersonality;
}
