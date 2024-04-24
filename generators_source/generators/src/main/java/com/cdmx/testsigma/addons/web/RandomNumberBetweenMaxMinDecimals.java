
/*Change Logs: AUTO-1104 | Function to take runtime variables as max and min and generate a number between them and store it | 24/04/24 */


package com.cdmx.testsigma.addons.web;

import com.testsigma.sdk.ApplicationType;
import com.testsigma.sdk.Result;
import com.testsigma.sdk.WebAction;
import com.testsigma.sdk.annotation.Action;
import com.testsigma.sdk.annotation.RunTimeData;
import com.testsigma.sdk.annotation.TestData;
import lombok.Data;
import org.openqa.selenium.NoSuchElementException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.math.RoundingMode;



@Data
@Action(actionText = "Generate random number between (Min, Max) considering numbers decimal digits and store it in runtime variable",
        description = "This addon will generate a random number between the minimum and maximum values and store it in a runtime variable",
        applicationType = ApplicationType.WEB)

public class RandomNumberBetweenMaxMinDecimals extends WebAction 
{
	
    @TestData(reference = "Min")
    private com.testsigma.sdk.TestData testData1;
 
    @TestData(reference = "Max")
    private com.testsigma.sdk.TestData testData2;

    @TestData(reference = "numbers")
    private com.testsigma.sdk.TestData testDecimal;

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
        	
            double min = Double.parseDouble(testData1.getValue().toString());
            double max = Double.parseDouble(testData2.getValue().toString());
        
            double random_double = min + Math.random() * (max - min);

            System.out.println("Random number is => " + random_double);
            logger.debug("Random number is => " + random_double);
            
            runTimeData.setKey(runtimeVar.getValue().toString());
            runTimeData.setValue(String.valueOf(random_double));

            System.out.println("Successfully stored the random number " + String.valueOf(random_double) + " in runtime variable => " + runtimeVar.getValue().toString());
            setSuccessMessage("Successfully stored the random number " + String.valueOf(random_double) +" in runtime variable => " + runtimeVar.getValue().toString());




    //Converting the randomly generated value to desired decimal digits
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            String testDecimalDigits = testDecimal.getValue().toString();
            int decimalDigit = 0;
            try {
                    decimalDigit = numberFormat.parse(testDecimalDigits).intValue();
                } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
            numberFormat.setMaximumFractionDigits(decimalDigit);
            numberFormat.setMinimumFractionDigits(decimalDigit);
            numberFormat.setRoundingMode(RoundingMode.DOWN);
            // Set the formatting to use commas
            numberFormat.setGroupingUsed(true);


            try {
            String random_double_string = Double.toString(random_double);
            double number = numberFormat.parse(random_double_string).doubleValue();
            // Format and display the number with commas
            String formattedAmount = numberFormat.format(number);
            runTimeData.setKey(runtimeVar.getValue().toString());
		runTimeData.setValue(formattedAmount);
       
       result = Result.SUCCESS;
       setSuccessMessage("Successfully generated the amount value  as " + formattedAmount + " and stored in runtime variable " + runtimeVar.getValue().toString());
    }
    catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }


        }
        catch(Exception e)
        {
            System.out.println(e.getMessage().toString());
            setErrorMessage("Operation failed , the error message is ::::"+ e.getMessage());
            result = Result.FAILED;
        }
        
        return result;
    }
}

