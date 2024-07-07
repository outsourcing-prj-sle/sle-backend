package egovframework.example.user.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdttLTResultDTO<T> {
    ArrayList<HashMap<String, String>> userPersonality;
    ArrayList<T> classPersonality;
}
