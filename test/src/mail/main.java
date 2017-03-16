package mail;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.yuqi.web.entity.User;
public class main {
	public static  Multipart multipart ;
	public static String myEmailAccount = "borolin@163.com";
    public static String myEmailPassword = "lbj0304";
    private HSSFWorkbook workbook = null;  
    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    public static String myEmailSMTPHost = "smtp.163.com";

    // 收件人邮箱（替换为自己知道的有效邮箱）
    public static String receiveMailAccount = "315551268@qq.com";

    public static void main(String[] args) throws Exception {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.host", myEmailSMTPHost);        // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 请求认证，参数名称与具体实现有关

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器
        //    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
        transport.connect(myEmailAccount, myEmailPassword);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();
    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
    	// 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "记账官方", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "", "UTF-8"));
        Multipart multipart = new MimeMultipart();//附件传输格式
        // 4. Subject: 邮件主题
        message.setSubject("记账APP明细", "UTF-8");
        String mailBody = "尊敬的用户，请查收您的明细！";
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(mailBody, "text/html;charset=utf-8");
        multipart.addBodyPart(bodyPart);
        
        /**生成excel*/
        ExcelManage em = new ExcelManage();  
        //判断文件是否存在  
        System.out.println(em.fileExist("E:/小说.xls"));  
        //创建文件  
        String columnNames = "姓名,密码";
        String array[]={"名字","密码"}; 
		String[] columnHeaderNames = new String [array.length+1];// {"序号","统计名称","数量"};
		columnHeaderNames[0]="序号";
		String[] excelDataColumn = new String [array.length];
		int i=0;
		for(String str:array){
			columnHeaderNames[i+1]=str;
			excelDataColumn[i]=str.split(":")[0];
			i++;
		}
		/*UploadExcelUtils uploadExcelUtil = new UploadExcelUtils();
        uploadExcelUtil.createNormalHead("支付明细", "sheet1",
				columnHeaderNames);*/
        String title[] = {"id","name","password"};  
        em.createExcel("E:/小说.xls","sheet1",columnHeaderNames);  
        //判断sheet是否存在  
        System.out.println(em.sheetExist("E:/小说.xls","sheet1"));  
        //写入到excel  
        User user = new User();  
        user.setId(5);  
        user.setName("qwer");  
        user.setPassword("zxcv");  
        User user3 = new User();  
        user3.setId(6);  
        user3.setName("qwerwww");  
        user3.setPassword("zxcvwww");  
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user3);
      
        em.writeToExcel("E:/小说.xls","sheet1",list);  
//        em.writeToExcel("E:/小说.xls","sheet1",user3);  
        //读取excel  
       /* User user2 = new User();  
        List list = em.readFromExcel("E:/test2.xls","sheet1", user2);  
        for (int i = 0; i < list.size(); i++) {  
            User newUser = (User) list.get(i);  
            System.out.println(newUser.getId() + " " + newUser.getName() + " "  
                    + newUser.getPassword());  
        }  */
        //删除文件  
        //System.out.println(em.deleteExcel("E:/test2.xls"));  
        String filename = "E:/小说.xls";
//        String filename = request.getRealPath("F:/小说.xls");
//        String filename = "d:/aa/text.excel";
        BodyPart bodyPart1 = new MimeBodyPart();
        FileDataSource fileDataSource = new FileDataSource(
                filename);
        bodyPart1
                .setDataHandler(new DataHandler(fileDataSource));
        bodyPart1.setFileName(MimeUtility.encodeText(fileDataSource.getName()));
//       Multipart multipart = new MimeMultipart();//附件传输格式
        multipart.addBodyPart(bodyPart1);
        message.setContent(multipart);
        // 5. Content: 邮件正文（可以使用html标签）
//        message.setContent("XX用户你好, 今天全场5折, 快来抢购, 错过今天再等一年。。。", "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

}
