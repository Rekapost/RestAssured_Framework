package poijiExcel;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.datafaker.Faker;
import randomDataUtils.RandomDataGenerator;
import randomDataUtils.RandomDataTypeNames;
import lombok.EqualsAndHashCode;
import lombok.Builder;
import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@EqualsAndHashCode
	@Builder(toBuilder = true)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class PoijiExcel {
		
//		Faker faker= new Faker();
//	    @ExcelCellName("name")
//	    private String name = RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME);
//	    @ExcelCellName("job")
//	    private String job=faker.examplify("#{Options.option 'QA','SDET', 'Tester'}");	
//        private String gender=Stream.of("male","female","others").findAny().get();    
		@ExcelCellName("name")
		private String name;
		@ExcelCellName("job")
		private String job;
}
