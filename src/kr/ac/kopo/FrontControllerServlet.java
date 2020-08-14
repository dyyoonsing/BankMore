package kr.ac.kopo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontControllerServlet extends HttpServlet {
	
	private HandlerMapping mappings;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		String propLoc = config.getInitParameter("propLocation");
		mappings = new HandlerMapping(propLoc);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String context = req.getContextPath();
		String uri = req.getRequestURI();
		uri = uri.substring(context.length());
		req.setAttribute("contextPath", context);
		
		try {
			Controller control = mappings.getController(uri);
			if(control != null) {
				String callPage = control.handleRequest(req, resp);
				if(callPage.startsWith("redirect:")) {
					resp.sendRedirect(callPage.substring("redirect:".length()));
					System.out.println("callpage : " + callPage.substring("redirect:".length()));
				}else {
					RequestDispatcher dis = req.getRequestDispatcher(callPage);
					dis.forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

	

}
