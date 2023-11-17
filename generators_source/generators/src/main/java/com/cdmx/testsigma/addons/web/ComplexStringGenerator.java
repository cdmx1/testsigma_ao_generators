
/*Change Logs: AUTO-833 | Function to generate a generate a random String of the specified length with at least one lowercase letter, one uppercase letter, one number, and at least one special character from  ~`!@#$%^&*()_-+={}[]|\:';"<,>.?  and store it in a variable | 17/11/23 */


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
@Action(actionText = "Generate a random complex string with the specified length and Store the result in variable",
        description = "Generate Random string with number lower/uppercase letters and speacial characters",
        applicationType = ApplicationType.WEB)

public class ComplexStringGenerator extends WebAction 
{
	
    @TestData(reference = "length")
    private com.testsigma.sdk.TestData lengthTestData;

    @TestData(reference = "variable")
    private com.testsigma.sdk.TestData runtimeVar;

    @RunTimeData
    private com.testsigma.sdk.RunTimeData runTimeData;

    @Override
    public Result execute() throws NoSuchElementException 
    {
    	
        // Your code starts here
        logger.info("Initiating execution");

        Result result = Result.SUCCESS;

        try
        {
            int stringLength = Integer.parseInt(lengthTestData.getValue().toString());
            StringBuilder newstring = new StringBuilder();

            // Define character sets for lowercase letters, uppercase letters, numbers, and special characters
            String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
            String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String numbers = "0123456789";
            String specialCharacters = "~`!@#$%^&*()_-+={}[]|:';\"<,>.?/";

            // Ensure that the password length is at least 4 to accommodate the required characters
            if (stringLength < 4) {
                throw new IllegalArgumentException("String length must be at least 4");
            }

            // Initialize the password with one character from each category
            newstring.append(lowercaseLetters.charAt((int) (Math.random() * lowercaseLetters.length())));
            newstring.append(uppercaseLetters.charAt((int) (Math.random() * uppercaseLetters.length())));
            newstring.append(numbers.charAt((int) (Math.random() * numbers.length())));
            newstring.append(specialCharacters.charAt((int) (Math.random() * specialCharacters.length())));

            // Fill the rest of the password with random characters
            int remainingLength = stringLength - 4;
            String allCharacters = lowercaseLetters + uppercaseLetters + numbers + specialCharacters;
            for (int i = 0; i < remainingLength; i++) {
                newstring.append(allCharacters.charAt((int) (Math.random() * allCharacters.length())));
            }

            // Shuffle the password to ensure randomness
            char[] stringArray = newstring.toString().toCharArray();
            for (int i = stringArray.length - 1; i > 0; i--) {
                int index = (int) (Math.random() * (i + 1));
                char temp = stringArray[index];
                stringArray[index] = stringArray[i];
                stringArray[i] = temp;
            }

            String randomString = new String(stringArray);

            runTimeData.setKey(runtimeVar.getValue().toString());
            runTimeData.setValue(randomString);

            logger.debug("Random String: " + randomString);
            System.out.println("Random String: " + randomString);

            setSuccessMessage("Successfully generated and stored the random string in runtime variable - " + runtimeVar.getValue().toString() + " with Value " + randomString);

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage().toString());
            setErrorMessage("Operation failed, the error message is: " + e.getMessage());
            result = Result.FAILED;
        }
        return result;
    }
}
