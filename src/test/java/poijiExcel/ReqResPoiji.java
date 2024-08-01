package poijiExcel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poiji.annotation.*;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReqResPoiji {
	
//	@ExcelCell(0)
//	private int id;
//
//	@ExcelCell(0)
//	@JsonIgnore  // if u dont want id to be part of json
//	private String idValue;
	
	@ExcelCellName("name")
	private String name;
	@ExcelCellName("job")
	private String job;

//    private int id= Integer.parseInt(randomDataUtils.RandomDataGenerator.getRandomNumber(6));
    
//	@ExcelCellName("job")
//	private List<String> job;

//	in future , if extra cell is added , u can use this , it will automatically add extra cells at run time
//	@ExcelUnknownCells
//	private Map<String , String> extraCell;
}
