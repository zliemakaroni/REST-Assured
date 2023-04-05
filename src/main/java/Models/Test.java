package Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonPropertyOrder({"duration", "method", "name", "startTime", "endTime", "status" })
public class Test implements Comparable<Test> {

    @JsonIgnore
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.0";
    @Getter @Setter
    private String duration;

    @Getter @Setter
    private String method;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String startTime;

    @Getter @Setter
    private String endTime;

    @Getter @Setter
    private String status;

    public Test(@JsonProperty("name") String name, @JsonProperty("method") String method,
                @JsonProperty("status") String status, @JsonProperty("startTime") String startTime,
                @JsonProperty("endTime") String endTime, @JsonProperty("duration") String duration) {
        this.duration = duration;
        this.method = method;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Test g = (Test) o;
        return this.duration.equals(g.getDuration()) &&
        this.method.equals(g.getMethod()) &&
        this.name.equals(g.getName()) &&
        this.startTime.equals(g.getStartTime()) &&
        this.status.equalsIgnoreCase(g.getStatus());
    }

    public int compareTo(Test t) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        LocalDateTime d1 = LocalDateTime.parse(this.getStartTime(), formatter);
        LocalDateTime d2 = LocalDateTime.parse(t.getStartTime(), formatter);

        if(d1.isEqual(d2))
            return 0;
        else if(d1.isBefore(d2))
            return 1;
        else
            return -1;
    }
}
