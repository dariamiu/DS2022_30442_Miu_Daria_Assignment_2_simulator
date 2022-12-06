package ds.smartmeteringdevicesimulator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomMessage{

    private String messageId;
    private Integer device;

    private Date timestamp;

    private Float consumption;
}
