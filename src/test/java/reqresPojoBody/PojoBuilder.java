package reqresPojoBody;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import groovy.transform.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import randomDataUtils.RandomDataGenerator;
import randomDataUtils.RandomDataTypeNames;

//	@Builder(toBuilder=true) // if not set true it will take default values for miising fields 0, null
//  and we cannot use thismethod new ReqResPojo( ReqResPojo payload= new ReqResPojo().toBuilder().name("Reka").build();   
	@Getter
	@Data
	@NoArgsConstructor 
	@EqualsAndHashCode
	@Builder(toBuilder=true)
	@JsonIgnoreProperties(ignoreUnknown=true)  //json to java ignore unknown fields
	public class PojoBuilder {

	 private String name;
	 private String job;
	 
	 @Builder
		public PojoBuilder(String name,String job) {
			this.name = name;
			this.job = job;
		}
	 
//	 Faker faker= new Faker();
//	 private String name = RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME);
//	 private String job = RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME);
//   private int id = Integer.parseInt(RandomDataGenerator.getRandomNumber(6));
//	 private String name=Integer.parseInt(RandomDataGenerator.getRandomNumber(6)))
//   private String name=RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME));
//   private String gender=Stream.of("male","female","others").findAny().get();
//	 private String gender=Arrays.asList("male","female","others").get(RandomDataGenerator.getRandomNumber(0,3));
//   private Gender gender;    //Gender.male
//   private Gender gender Arrays.stream(Gender.values()).findAny().get();
	 
	}
