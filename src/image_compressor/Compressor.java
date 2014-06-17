package image_compressor;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.*;
import javax.imageio.stream.ImageOutputStream;

class Compresssion {

  public static void main(String[] args) {
      
	   
	   int percent = Integer.parseInt("50");
	   String f="./pic"; 
       File file=new File(f); 
       
       System.out.println("Scanning files...");
       if(file.exists()) 
       { 
           File[] filelist=file.listFiles(); 
           for(int i=0;i<filelist.length;i++) 
           { 
        	   
        	   try{
	               String fileName=filelist[i].getName();
	               long fileSize = filelist[i].length();
	               System.out.println(fileName+": "+fileSize+" bytes");
	               if(fileSize > 2097152){
	            	   
	            	   compress(fileName,percent);
	            	   System.out.println("-Compressed");
	               }
	               else{
	            	   InputStream inStream = null;
	                   OutputStream outStream = null;
	                   
	            
	                       File file1 =new File("./pic/"+fileName);
	                       File file2 =new File("./pic_cmpressed/"+fileName);
	            
	                       inStream = new FileInputStream(file1);
	                       outStream = new FileOutputStream(file2); // for override file content
	                       //outStream = new FileOutputStream(file2,<strong>true</strong>); // for append file content
	            
	                       byte[] buffer = new byte[1024];
	            
	                       int length;
	                       while ((length = inStream.read(buffer)) > 0){
	                           outStream.write(buffer, 0, length);
	                       }
	            
	                       if (inStream != null)inStream.close();
	                       if (outStream != null)outStream.close();
	            	   System.out.println("-Nothing");
	               }
	               
        	   }
        	   catch(Exception e){
        		   System.out.println("Bad image");
           	   }
           } 
       } 

       System.out.println("Done.");
	  
   }
   
   
   public static void compress (String fileName,int percent) throws IOException {
	   
	   	File input = new File("./pic/"+fileName);
	   	
	   	
	      BufferedImage image = ImageIO.read(input);

	      File compressedImageFile = new File("./pic_cmpressed/"+fileName);
	      OutputStream os =new FileOutputStream(compressedImageFile);

	      Iterator<ImageWriter>writers = 
	      ImageIO.getImageWritersByFormatName("jpg");
	      ImageWriter writer = (ImageWriter) writers.next();

	      ImageOutputStream ios = ImageIO.createImageOutputStream(os);
	      writer.setOutput(ios);

	      ImageWriteParam param = writer.getDefaultWriteParam();
	      param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	      
	      float quality = percent / 100;
	      param.setCompressionQuality(quality);
	      writer.write(null, new IIOImage(image, null, null), param);
	      os.close();
	      ios.close();
	      writer.dispose();
	   
   }
}