package org.kangspace.common.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
  
public class ImageZipUtil extends Object{
    private File inputFile = null; // 输入文件对象   
    private File outputFile = null; // 输出文件对象   
    private Integer outputWidth; // 默认输出图片宽  
    private Integer outputHeight; // 默认输出图片高  
    private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)  
    public ImageZipUtil() {  
    }   
       
    public File getInputFile() {
		return inputFile;
	}

	public void setInputFile(File inputFile) {
		isExistsFile(inputFile);
		this.inputFile = inputFile;
	}

	public File getOutputFile() {
		
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public boolean isProportion() {
		return proportion;
	}

	public void setProportion(boolean proportion) {
		this.proportion = proportion;
	}

	public Integer getOutputWidth() {
		return outputWidth;
	}

	public Integer getOutputHeight() {
		return outputHeight;
	}

	public void setOutputWidth(int outputWidth) {  
        this.outputWidth = outputWidth;   
    }   
    public void setOutputHeight(int outputHeight) {   
        this.outputHeight = outputHeight;   
    }   
    public void setWidthAndHeight(Integer width, Integer height) {   
        this.outputWidth = width;  
        this.outputHeight = height;   
    }   
    /**
     * 检查文件是否存在,不存在则抛出异常
     * @param file
     */
    private void isExistsFile(File file){
    	if(!(file !=null && file.exists() && file.isFile()))
			throw new RuntimeException(new FileNotFoundException(file!=null?file.getAbsolutePath():null +"has not found !"));
    }

    /*
     * 获得图片大小  
     * 传入参数 String path ：图片路径  
     */   
    public long getPicSize(String path) {   
        File tfile = new File(path);   
        return tfile.length();   
    }  
      
    // 图片处理   
    private void compressPic() {
    	FileOutputStream out = null;
    	try {
    		//获得源文件  
	        Image img = ImageIO.read(inputFile);   
	        // 判断图片格式是否正确   
	        if (img.getWidth(null) == -1)  
	            throw new RuntimeException(" can't read,retry!"); 
	        if(getOutputWidth() == null || getOutputHeight() == null)
	        	setWidthAndHeight(img.getWidth(null), img.getHeight(null));
	        int newWidth; int newHeight;
	        // 判断是否是等比缩放   
	        if (isProportion()) {   
	            // 为等比缩放计算输出的图片宽度及高度   
	            double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;
	            double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;
	            // 根据缩放比率大的进行缩放控制
	            double rate = rate1 > rate2 ? rate1 : rate2;
	            newWidth = (int) (((double) img.getWidth(null)) / rate);
	            newHeight = (int) (((double) img.getHeight(null)) / rate);
	        } else {
	            newWidth = outputWidth; // 输出的图片宽度
	            newHeight = outputHeight; // 输出的图片高度
	        }
        	BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
           /* 
            * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 
            * 优先级比速度高 生成的图片质量比较好 但速度慢 
            */   
           tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);  
           out = new FileOutputStream(outputFile);  
           // JPEGImageEncoder可适用于其他图片类型的转换   
	           JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
	           encoder.encode(tag);
	        } catch (IOException ex) {
	            throw new RuntimeException(ex);
	        }finally{
				try {
					if(out != null)
						out.close();
				} catch (IOException e) {;}
	        }
    }   
    
    /**
     * 压缩图片
     * @param inputFile
     * @param outputFile
     * @param width
     * @param height
     * @param gp
     */
    public void compressPic(String inputFile, String outputFile, Integer width, Integer height, Boolean gp) {
       setInputFile(new File(inputFile));
       setOutputFile(new File(outputFile));
       // 设置图片长宽  
       setWidthAndHeight(width, height);   
       // 是否是等比缩放 标记   
       setProportion(gp!=null&&gp?gp:false);   
       compressPic();   
   }
   /**
	* 压缩图片
    * @param inputFile
	* @param outputFile
    * @Author kango2gler@gmail.com
    * @Date 2017/3/24 13:15
    * @return
    */
    public void compressPic(String inputFile, String outputFile) {
        compressPic(inputFile, outputFile, null, null, null);
    }

	/**
	 * 生成文件夹,若文件夹不存在,则生成
	 * @param file 文件或文件夹
	 * @Author kango2gler@gmail.com
	 * @Date 2017/3/24 13:10
	 * @return
	 */
	private void mkdirs(File file){
		if(file == null)
			throw new IllegalArgumentException("file is null");
		try {
			File floderPth = file.getCanonicalFile().getParentFile();
			floderPth.mkdirs();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
     
   // main测试   
   // compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))  
   public static void main(String[] arg) throws IOException {
	   File file = new File("D:/Canonical/1.jpg");
	   ImageZipUtil mypic = new ImageZipUtil();
	   mypic.mkdirs(file);
	   System.out.println(file.getPath());
	   System.out.println(file.getAbsolutePath());
	   System.out.println(file.getCanonicalPath());
	   /*

       System.out.println("输入的图片大小：" + mypic.getPicSize("d:\\1.jpg")/1024 + "KB");   
       int count = 0; // 记录全部图片压缩所用时间  
       for (int i = 0; i < 1; i++) {   
           int start = (int) System.currentTimeMillis();   // 开始时间   
           mypic.compressPic("d:\\1.jpg", "d:\\test\\r1"+i+".jpg", 520, 520, true);
           mypic.compressPic("d:\\1.jpg", "d:\\test\\r1.jpg");
           int end = (int) System.currentTimeMillis(); // 结束时间   
           int re = end-start; // 但图片生成处理时间   
           count += re; System.out.println("第" + (i+1) + "张图片压缩处理使用了: " + re + "毫秒");   
           System.out.println("输出的图片大小：" + mypic.getPicSize("e:\\test\\r1"+i+".jpg")/1024 + "KB");   
       }  
       System.out.println("总共用了：" + count + "毫秒");
          */
   }   
}