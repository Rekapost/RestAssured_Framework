package reusableMethods;
import java.util.Map;
//import org.instancio.internal.random.RandomDataGenerator;
import net.datafaker.Faker;
import java.util.HashMap;
import java.util.LinkedHashMap;

import randomDataUtils.RandomDataGenerator;
import randomDataUtils.RandomDataTypeNames;
import reqresPojoBody.PojoLombok;
import reqresPojoBody.PojoBuilder;
import lombok.*;
public class Payloads {
	/*
		{
			"name": "morpheus",
			"job": "leader"
		}
	*/
	
	public static String CreatePayloadString(String name, String job) {	 
		String payload="{\r\n"
				+ "    \"name\": \""+name+"\",\r\n"
				+ "    \"job\": \""+job+"\"\r\n"
				+ "}";
		return payload;		
			}
		
	public static Map<String,Object> CreatePayloadMap(String name, String job) {	
		///U can add String id 
		Map<String,Object> mapPayload= new HashMap<>();
		mapPayload.put("name", name);
		mapPayload.put("job", job);
		return mapPayload;		
			}
	
	public static Map<String,Object> CreatePayloadMapDataFaker() {	
		///U can add String id 
		Faker faker= new Faker();
		Map<String,Object> mapPayload= new HashMap<>();
		//mapPayload.put("name", faker.name().firstName());
		mapPayload.put("name", randomDataUtils.RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME));
		mapPayload.put("job", faker.expression("QA"));
		return mapPayload;	
		//faker.number().digits(10)
		}
	/*
	public static ReqResPojo getPayloadFromPojoBuilder() {  
		//ReqResPojo is java object
		//.id(Integer.parseInt(RandomDataGenerator.getRandomNumber(6)))
		//.name(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME))
		    Faker faker= new Faker();

			      return ReqResPojo.builder()
						.name(randomDataUtils.RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME))
						.job(randomDataUtils.RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME))
						.build();
	}
	*/
	public static PojoBuilder getPayloadFromPojoBuilder() {
		PojoBuilder pojoBuilder= PojoBuilder
					                .builder()           
					                .name(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME))
					                .job(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME))
					                .build();
       					return pojoBuilder;   		
    }
	
}
