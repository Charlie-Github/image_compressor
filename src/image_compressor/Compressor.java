package image_compressor;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.*;
import javax.imageio.stream.ImageOutputStream;

class Compresssion {

  public static void main(String[] args) throws IOException {
      
	   
	   int percent = Integer.parseInt(args[0]);
	   String f="./pic"; 
       File file=new File(f); 
       
       System.out.println("Scanning files...");
       if(file.exists()) 
       { 
           File[] filelist=file.listFiles(); 
           for(int i=0;i<filelist.length;i++) 
           { 
               String fileName=filelist[i].getName(); 
               System.out.println(fileName);
               compress(fileName,percent);
           } 
       } 

       System.out.println("Done.");
	  
   }
   
   
   public static void compress (String fileName,int percent) throws IOException {
	   
	   	File input = new File("./pic/"+fileName);
	   	
	   	
	      BufferedImage image = ImageIO.read(input);

	      File compressedImageFile = new File("./pic_cp/"+fileName+"_cp.jpg");
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