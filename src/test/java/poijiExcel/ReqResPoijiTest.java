package poijiExcel;
import java.io.File;
import com.poiji.bind.Poiji;
import java.util.List;
import com.poiji.option.PoijiOptions;

public class ReqResPoijiTest {
	
	public static void main(String[] args) {
// To check whether this method is able to read data from excel and print in excel  		
//	a column can have array of strings , default delimiter is , if u change to colon ; , u should inform poiji 
//	column value= QA, Tester, SDET
//	output=[QA, Tester, SDET]
//	PoijiOptions options=	PoijiOptions.PoijiOptionsBuilder.settings().addListDelimiter(";").build();
//	List<ReqResPoiji> data= Poiji.fromExcel(new File("src/test/resources/testData/reqresPoiji.xlsx"), ReqResPoiji.class,options);	
	List<ReqResPoiji> data= Poiji.fromExcel(new File("src/test/resources/testData/reqresPoiji.xlsx"), ReqResPoiji.class);	
	System.out.println(data);
	}

}
