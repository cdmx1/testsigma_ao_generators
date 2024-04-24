
/*Change Logs: General Task | Function to take runtime variables as max and min and generate a number between them and store it | 16/11/23 */


package com.cdmx.testsigma.addons.web;

import com.testsigma.sdk.ApplicationType;
import com.testsigma.sdk.Result;
import com.testsigma.sdk.WebAction;
import com.testsigma.sdk.annotation.Action;
import com.testsigma.sdk.annotation.RunTimeData;
import com.testsigma.sdk.annotation.TestData;
import lombok.Data;
import org.openqa.selenium.NoSuchElementException;

@Data
@Action(actionText = "Generate random number between (Min, Max) and store it in runtime variable",
        description = "This addon will generate a random number between the minimum and maximum values and store it in a runtime variable",
        applicationType = ApplicationType.WEB)

public class GetNumberBetweenMaxMIn extends WebAction 
{
	
    @TestData(reference = "Min")
    private com.testsigma.sdk.TestData testData1;
 
    @TestData(reference = "Max")
    private com.testsigma.sdk.TestData testData2;

    @TestData(reference = "variable")
    private com.testsigma.sdk.TestData runtimeVar;

    @RunTimeData
    private com.testsigma.sdk.RunTimeData runTimeData;

    @Override
    public Result execute() throws NoSuchElementException 
    {
    	
        //Your Awesome code starts here
        logger.info("Initiating execution");

        Result result = Result.SUCCESS;

        try
        {
        	logger.debug("Min => " + testData1.getValue().toString());
            logger.debug("Max => " + testData2.getValue().toString());
            System.out.println("Min => " + testData1.getValue().toString());
            System.out.println("Max => " + testData2.getValue().toString());
        	
            int Minimun = Integer.parseInt(testData1.getValue().toString());
            int Maximun = Integer.parseInt(testData2.getValue().toString());
            
            int min = Minimun;
            int max = Maximun; 
            int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);

            System.out.println("Random number is => " + random_int);
            logger.debug("Random number is => " + random_int);
            
            runTimeData.setKey(runtimeVar.getValue().toString());
            runTimeData.setValue(String.valueOf(random_int));

            System.out.println("Successfully stored the random number " + String.valueOf(random_int) + " in runtime variable => " + runtimeVar.getValue().toString());
            setSuccessMessage("Successfully stored the random number " + String.valueOf(random_int) +" in runtime variable => " + runtimeVar.getValue().toString());

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage().toString());
            setErrorMessage("Operation failed , the error message is ::::"+e.getMessage());
            result = Result.FAILED;
        }
        return result;
    }
}

