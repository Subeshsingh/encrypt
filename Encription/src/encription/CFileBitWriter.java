package encription;

/* 
 * This code may be freely distributed
 * and used for any non-commericial purpose, as long as its copyright 
 * notice is retained.  The author assumes absolutely no responsibility 
 * for any harm caused by bugs in the code.
 **/ 

/* 
 * CFileBitWriter.java - coded By st0le [st0le'n'stuff softwarez!] 
 * Website : http://st0lenc0des.googlepages.com 
 * Copyright (c) st0le 2007 
 */ 



import java.io.*;

//File Bit Writer - Write to Files BitWise

public class CFileBitWriter {
	
	private String fileName;
	
	private File outputFile;
	private FileOutputStream fout;
	private BufferedOutputStream outf;
	private String currentByte;
	
	
	public CFileBitWriter() throws Exception{
		try{
			fileName = "";
			//loadFile(fileName);
		}catch(Exception e){
			throw e;
		}
		
	}
	
	public CFileBitWriter(String txt) throws Exception{
		try{
			fileName = txt;
			loadFile(fileName);
		}catch(Exception e){
			throw e;
		}
		
	}
	
	public boolean loadFile(String txt) throws Exception{
		fileName = txt;
		
		try
		{
			outputFile = new File(fileName);
			fout = new FileOutputStream(outputFile);
			outf = new BufferedOutputStream(fout);

			currentByte = "";
			return true;

		}catch(Exception e){
			throw e;
		}
		
		//return true;
	}
	
	public void putBit(int bit) throws Exception{
		
		try{
			bit = bit % 2;
			currentByte = currentByte + Integer.toString(bit);
			
			if(currentByte.length() >= 8){
				
				int byteval = Integer.parseInt(currentByte.substring(0,8),2);
				outf.write(byteval);
				currentByte = ""; //reset
			}
			
		}catch(Exception e){throw e;}
		}
	
	public void putBits(String bits) throws Exception{
		
		try{
			while(bits.length() > 0){
			int bit = Integer.parseInt(bits.substring(0,1));
			putBit(bit);
			bits = bits.substring(1);
			}
		}catch(Exception e){throw e;}
		}

	public void putString(String txt) throws Exception{
		
		try{
			while(txt.length() > 0){
			String binstring = Integer.toString(txt.charAt(0),2);
			binstring = leftPad8(binstring );
			
			putBits(binstring);
			txt = txt.substring(1);
			}
		}catch(Exception e){throw e;}
		}
		
	 String leftPad8(String txt){
		while(txt.length() < 8 )
			txt =  "0" + txt;
		return txt;
		}
	 String rightPad8(String txt){
		while(txt.length() < 8 )
			txt +=  "0";
		return txt;
		}
		
	public void closeFile() throws Exception{
		
		try{
		
		//check if incomplete byte exists
		while(currentByte.length() > 0){
			putBit(1);
			}
		outf.close();
		
		}catch(Exception e){ throw e;}
		}
		
	
}


