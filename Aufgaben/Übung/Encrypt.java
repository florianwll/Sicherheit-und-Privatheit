import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.Writer;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.spec.*;
import java.security.SecureRandom;
import java.math.BigInteger;

public class Encrypt{
	public static void main(String[] args)
	{
 
		try{
			
			System.out.println("Username: ");
			String username = System.console().readLine();
	
			System.out.println("Password: ");
			
			//dont show characters of pwd
			char[] pwd = System.console().readPassword();
			
			String password=String.valueOf(pwd);
			

			Writer fileWriter = new FileWriter("shadow.txt", true);
			File file = new File("shadow.txt");
			if(!file.exists()) //Create new file, if it doesn't already exist
				fileWriter = new FileWriter("shadow.txt", false);			
			
			//Begin Password hashing
			byte[] salt=makeSalt();
			byte[] hash = makeHash(pwd, salt); //will throw error if not yet implemented
			String encrypted_password=toHex(hash); 
			
			//Write username:password:salt to file
			fileWriter.write(username + ":" + encrypted_password + ":" + toHex(salt)+"\n");
			fileWriter.flush();
			
			fileWriter.close();
				
		}catch(IOException e){
			System.out.println("Error creating file");
			e.printStackTrace();
		}

		catch(Exception e){
			System.out.println("Error");
		}

	}
	
	/****************************/
	/*IMPLEMENT SALTING FUNCTION*/
	/****************************/	
	public static byte[] makeSalt(){
		//TODO
		return null;
	}
	
	/****************************/
	/*IMPLEMENT HASHING FUNCTION*/
	/****************************/
	public static byte[] makeHash(char[] pwd, byte[] salt){
		//TODO
		return null;
	}
	
		
	public static String toHex(byte[] bytes){
		BigInteger big = new BigInteger(1, bytes);
		String hex = big.toString(16);
		int padding = (bytes.length * 2) - hex.length();
		if(padding > 0)
		{
			return String.format("%0"  +padding + "d", 0) + hex;
		}else{
			return hex;
		}

	}
	
	public static byte[] toByte(String hex){
		try{
			byte[] bytes = new byte[hex.length() / 2];
			for(int i = 0; i<bytes.length; i++)
			{
				bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
			}
			return bytes;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}