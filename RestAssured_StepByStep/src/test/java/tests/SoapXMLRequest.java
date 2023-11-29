package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SoapXMLRequest {

    @Test
    public void validateSoapXML() throws IOException {

        File file = new File(".\\SoapRequest\\Add.xml");

        if (file.exists()) {
            System.out.println(">> File exists");
        }

        FileInputStream fileInputStream = new FileInputStream(file);

        String requestBody = IOUtils.toString(fileInputStream, "UTF-8");


        baseURI = "https://ecs.syr.edu";

        given().
                contentType("text/xml").
                accept(ContentType.XML).body(requestBody).
                when().
                post("/faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx").
                then().
                statusCode(200).log().all().and().body("*//AddResult.text()", equalTo("6"));

        //https://www.freeformatter.com/xpath-tester.html#before-output
        //https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx?op=Add
    }
}
