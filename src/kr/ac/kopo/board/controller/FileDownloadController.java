package kr.ac.kopo.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.ac.kopo.Controller;
import kr.ac.kopo.util.KopoFileNamePolicy;

public class FileDownloadController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		   
		   String saveFolder = "C:/00Lecture/web-workspace/BankMore/WebContent/upload";
		   
		   MultipartRequest multi = new MultipartRequest(
		                                       request,
		                                       saveFolder,
		                                       1024*1024*3,   // 최대 3메가까지 저장
		                                       "utf-8",
		                                       new KopoFileNamePolicy());
		   
		    // 파일 업로드된 경로
		    String root =  request.getSession().getServletContext().getRealPath("/");
		    String savePath = root + "upload";
		 
		    // 서버에 실제 저장된 파일명
		    String fileSaveName = multi.getParameter("fileSaveName");
		     
		    // 실제 내보낼 파일명
		    String fileOriName = multi.getParameter("fileOriName");
		      
		    InputStream in = null;
		    OutputStream os = null;
		    File file = null;
		    boolean skip = false;
		    String client = "";
		    String msg = null;
		    String url = null;
		 
		    try{
		 
		        // 파일을 읽어 스트림에 담기
		        try{
		            file = new File(savePath, fileSaveName);
		            in = new FileInputStream(file);
		        }catch(FileNotFoundException fe){
		            skip = true;
		        }

		        client = request.getHeader("User-Agent");
		 
		        // 파일 다운로드 헤더 지정
		        response.reset() ;
		        response.setContentType("application/octet-stream");
		        response.setHeader("Content-Description", "JSP Generated Data");
		 
		        if(!skip){

		            // IE
		            if(client.indexOf("MSIE") != -1){
		                response.setHeader ("Content-Disposition", "attachment; filename="+new String(fileOriName.getBytes("KSC5601"),"ISO8859_1"));
		 
		            }else{
		                // 한글 파일명 처리
		                fileOriName = new String(fileOriName.getBytes("utf-8"),"iso-8859-1");
		 
		                response.setHeader("Content-Disposition", "attachment; filename=/" + fileOriName + "/");
		                response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
		            } 
		             
		            response.setHeader ("Content-Length", ""+file.length() );
		 
		            os = response.getOutputStream();
		            byte b[] = new byte[(int)file.length()];
		            int leng = 0;
		             
		            while( (leng = in.read(b)) > 0 ){
		                os.write(b,0,leng);
		            }
		            
		            url = request.getContextPath() + "/boardDetail.do?no=" + request.getParameter("no");
		 
		            in.close();
		            os.close();
		            
		        }else{
		            response.setContentType("text/html;charset=UTF-8");
		            msg = "파일을 찾을 수 없습니다";
		            url = request.getContextPath() + "/boardDetail.do";
		        }
		        
		 
		    }catch(Exception e){
		      e.printStackTrace();
		    }
		    
		    
		    request.setAttribute("msg", msg);
		    request.setAttribute("url", url);
		
		return "/board/fileDownload.jsp";
	}

}
