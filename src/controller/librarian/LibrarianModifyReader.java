package controller.librarian;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReaderDAO;
import entity.Reader;

/**
 *	该Servlet的doGet方法将请求转发到Reader信息的修改界面
 *	该Servlet的doPost方法处理Reader修改后的信息，并加入数据库
 *@author zengyaoNPU
 */
public class LibrarianModifyReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LibrarianModifyReader() {
        super();
    }

    /**
     * @author zengyaoNPU
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--LibrarianModifyReader--doGet()");
		int readerId=Integer.parseInt(request.getParameter("reader_id"));//获取请求参数
		ReaderDAO readerDAO=new ReaderDAO();
		Reader reader=readerDAO.getReaderById(readerId);//实例化Reader
		//设置属性
		request.setAttribute("readerEntity", reader);//新的request，request属性与之前的request对象相同不会影响
		RequestDispatcher dispatcher=request.getRequestDispatcher("librarianModifyReader.jsp");
		dispatcher.forward(request, response);//转发
	}
	/**
	 * @author zengyaoNPU
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--LibrarianModifyReader--doPost()");
		request.setCharacterEncoding("utf-8");
		//获取参数
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String state=request.getParameter("state");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		ReaderDAO readerDAO=new ReaderDAO();
		if(readerDAO.updateData(id, name, password, email,state)) {
			response.sendRedirect("SearchReaderBeforeEdit?reader_id="+id);//转发到该页面
		}else {
			System.out.println("修改Reader的信息失败");
		}
		
		
		
		
	}

}