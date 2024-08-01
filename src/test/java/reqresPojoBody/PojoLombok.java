package reqresPojoBody;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true) 
public class PojoLombok {

	String name;
	String job;
	
}
