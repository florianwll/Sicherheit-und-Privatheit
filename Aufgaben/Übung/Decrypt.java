import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.spec.*;
import java.security.SecureRandom;
import java.math.BigInteger;

public class Decrypt{
	public static void main(String[] args)
	{
 
		try{
			
			System.out.println("Username: ");
			String username = System.console().readLine();
	
			System.out.println("Password: ");
			
			//dont show characters of pwd
			char[] pwd = System.console().readPassword();
			
			String password=String.valueOf(pwd);
			
			String UPS;
			
			FileReader fileReader = new FileReader("shadow.txt");
			BufferedReader instr = new BufferedReader(fileReader);
			
			char sep=':';
			String u="";
			String p="";
			String s="";
			
			int cnt=0;
			
			while((UPS=instr.readLine())!=null){

				for(int i=0;i<UPS.length();i++){
					if(cnt==0 && UPS.charAt(i)!=sep){
						u+=UPS.charAt(i);
					}else if(cnt==0){
						cnt=1;
					}else if(cnt==1 && UPS.charAt(i)!=sep){
						p+=UPS.charAt(i);
					}else if(cnt==1){
						cnt=2;
					}else if(cnt==2 && UPS.charAt(i)!=sep){
						s+=UPS.charAt(i);
					}
				}

				if(u.equals(username)){
					
					/****************************/
					/* IMPLEMENT PASSWORD CHECK */
					/****************************/
					//TODO
					break;
				}else{
					u="";
					p="";
					s="";
					cnt=0;
				}
			}		
			
			instr.close();
			fileReader.close();

		}catch(IOException e){
			System.out.println("Error opening file");
			e.printStackTrace();
		}

		catch(Exception e){
			System.out.println("Error");
		}

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