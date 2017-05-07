import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.util.Date;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class WebpageProcess
{
  public static void printURLinfo(URLConnection uc, PrintWriter output) throws IOException
  {
    // Display the URL address, and information about it.
    output.println(uc.getURL().toExternalForm() + ":");
    output.println("  Content Type: " + uc.getContentType());
    output.println("  Content Length: " + uc.getContentLength());
    output.println("  Last Modified: " + new Date(uc.getLastModified()));
    output.println("  Expiration: " + uc.getExpiration());
    output.println("  Content Encoding: " + uc.getContentEncoding());
        
    if(uc.getContentType().equals("text/html") || uc.getContentType().startsWith("text/plain"))
    {
    	String url = uc.getURL().toExternalForm(); //Get URL to extract filename
    	String fileNameWithExtn = url.substring(url.lastIndexOf('/') + 1, url.length()); //Extract filename 
    	String fileName = fileNameWithExtn.substring(0, fileNameWithExtn.lastIndexOf('.')); //Remove extension from filename
    	PrintWriter file = new PrintWriter(fileName + ".txt");
    	
    	int lineCount = 0;
    	BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        while(in.ready())
        {
    		  String line = in.readLine();
    		  if (line == null) break;
    		  file.println(line);
    		  lineCount++;
        }
        file.close();
        
        output.println("Lines read: " + lineCount);
        output.println("File Name: " + fileName);
        output.println("");
    }
    
    else if(uc.getContentType().equals("image/jpeg"))
    {
    	String url = uc.getURL().toExternalForm();
    	String fileNameWithExtn = url.substring(url.lastIndexOf('/') + 1, url.length());
    	String fileName = fileNameWithExtn.substring(0, fileNameWithExtn.lastIndexOf('.'));
    	File imageFile = new File(fileName + ".jpeg");
    	
    	BufferedImage image = ImageIO.read(uc.getURL());
    	ImageIO.write(image, "jpeg", imageFile);
    	
    	output.println("File Name: " + fileName);
    	output.println("");
    }
  }

	// Create a URL from the specified address, open a connection to it,
	// and then display information about the URL.
	public static void main(String[] args) throws IOException
	{
		Scanner inputFile = new Scanner(new File(args[0]));
		PrintWriter outputFile = new PrintWriter(args[1]);
		URL url;
		
		while(inputFile.hasNextLine())
		{
			url = new URL(inputFile.nextLine()); //read next URL
			URLConnection connection = url.openConnection();
			printURLinfo(connection, outputFile);
		}
		
		inputFile.close();
		outputFile.close();
	}
}
