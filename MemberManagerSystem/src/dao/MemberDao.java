/**
 *	@author：     chuck
 *	@version：    1.0版
 *  日期：      2016-9-20下午6:57:30
 */


package dao;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.KnowToday;
import model.Member;
import view.Data;

public class MemberDao {
	static Data d = new Data();
	int p=0;
	//初始化
	public MemberDao(){
		for(int i=0;i<d.Member.length;i++){
			d.Member[i] = new Member();
		}
		initial();
		
	}
	//取出存在的会员P个
	public void initial(){
		for(int i=0;i<d.Member.length;i++){
			if(d.Member[i].getName()!=null) {
				p=i;
				p++;
			}
		}
		for(int i=0;i<p;i++){
			d.Member[i].setNumber("00"+i);
		}
	}
	//增加
	public void add(int uu){
		initial();
		//添加会员名
		Scanner in = new Scanner(System.in);
		System.out.println("骁龙二部健身会员管理系统>会员信息管理>添加会员信息");
		System.out.println("请输入会员姓名： ");
		String name;
		String aname = null;
		int n=0;
		//判断空输入
		st:while (true) {
			
			name= in.nextLine();
			if (name.equals("")) {
				System.out.println("姓名不能为空,请重新输入:");
				continue;
			}
			if(n==1){
				if(!name.equals(aname)){
					System.out.println("重置失败！请重新输入：");
					n=0;
					continue;
				}else{
					System.out.println("Ok！");
					break;
				}
			}
			
			//判断输入会员是否重复，如果重复得确认
			for(int j=0;j<d.Member.length;j++){
				while(name.equals(d.Member[j].getName())&&d.Member[j].getId()==uu){
					n++;
					System.out.println("会员的名字有重复，请重复输入的用户名：");
					aname=name;
					continue st;
				}
			}
			Pattern p_str = Pattern.compile("[\\u4e00-\\u9fa5]+");
			Matcher m = p_str.matcher(name);
			if (m.find() && m.group(0).equals(name)) {
				System.out.println("Ok！");
				break;
			}else{
				System.out.println("里面有其他字符,不符合中文，请重新输入:");
				continue st;
			}
		}
		
		//年月日判断
		KnowToday kt = new KnowToday();
		String bornth = null;
		//继续添加
		try {
			if(kt.jugdeToday()){
				bornth = kt.getYear()+"."+kt.getMon()+"."+kt.getDay();
			}
		} catch (InputMismatchException e) {
			System.out.println("输入错误的符号!系统关闭！");
		}
		
		//添加手机号
		System.out.println("请输入会员的手机号：");
		String phoneNumber = null;
		boolean f1 = true;
		while (f1) {
			phoneNumber = in.nextLine();
			if (phoneNumber.length() == 11){		// 判断手机号\t为11位
				for (int i = 0; i < 11; i++) {
					if(phoneNumber.startsWith("1")){
						char b = phoneNumber.charAt(i);
						if (b >= 48 && b <= 57){	// 之后的10位是不是0-9之间的数字
							if (i == 10) {
								System.out.println("Ok!");
								f1 = false;
							}
							continue;
						} else {
							System.out.println("手机号码不合格，只能是数字，请重新输入");
							break;
						}
					} else {
						System.out.println("首位需为1，请重新输入");
						break;
					}
				}
			} else {
				System.out.println("输入不是11位");
				continue;
			}
		}
		
		//添加地址
		System.out.println("请输入会员联系地址：");
		String add ;
		
		st:while(true){
			add= in.nextLine();
			if (add.equals("")) {
				System.out.println("会员联系地址不能为空！请重新输入会员联系地址:");
				continue;
			}
			//判断地址中文字符
			Pattern p_str = Pattern.compile("[\\u4e00-\\u9fa5]+");
			Matcher m = p_str.matcher(add);
			if (m.find() && m.group(0).equals(add)) {
				System.out.println("Ok！");
				break;
			}else{
				System.out.println("里面有其他字符,不符合中文，请重新输入:");
				continue st;
			}
		}
		
		//添加会员信息
		d.Member[p].setNumber("00"+p);
		d.Member[p].setName(name);
		d.Member[p].setBornth(bornth);
		d.Member[p].setAddress(add);
		d.Member[p].setPhoneNumber(phoneNumber);
		d.Member[p].setId(uu);
		//列表
		list(uu);
		
	}

	//删除
	public void delete(int uu){
		initial();
		System.out.println("请输入要删除的会员编号：");
		Scanner in = new Scanner(System.in);
		String number = in.nextLine();
		for(int i=0;i<p;i++){
			if(d.Member[i].getNumber().equals(number)&&d.Member[i].getId()==uu){
				d.Member[i].rush();
				System.out.println("删除会员成功！");
				return;
			}
		}
		System.out.println("要删除的会员不存在！系统返回！");
	}
	//修改
	public void updata(int uu){
		initial();
		Scanner sc = new Scanner(System.in);
		System.out.println("骁龙二部健身会员管理系统>会员信息管理>请输入要修改会员编号");
		String number = sc.nextLine();
		for(int i=0;i<p;i++){
      		if(d.Member[i].getNumber().equals(number)&&d.Member[i].getId()==uu){
      			System.out.println("   "+d.Member[i].getNumber()+"        "+d.Member[i].getName()+"                "+d.Member[i].getBornth()+"                    "+d.Member[i].getAddress()+"                      "+d.Member[i].getPhoneNumber());
      			
      			System.out.println("骁龙二部健身会员管理系统>会员信息管理>修改手机号码为：");
      			String phoneNumber = null;
      			//写手机号码判断
      			boolean f1 = true;
      			while (f1) {
      				phoneNumber = sc.nextLine();
      				if (phoneNumber.length() == 11){		// 判断手机号\t为11位
      					for (int j = 0; j < 11; j++) {
      						if(phoneNumber.startsWith("1")){
      							char b = phoneNumber.charAt(j);
      							if (b >= 48 && b <= 57){	// 之后的10位是不是0-9之间的数字
      								if (j == 10) {
      									System.out.println("Ok!");
      									f1 = false;
      								}
      								continue;
      							} else {
      								System.out.println("手机号码不合格，只能是数字，请重新输入");
      								break;
      							}
      						} else {
      							System.out.println("首位需为1，请重新输入");
      							break;
      						}
      					}
      				} else {
      					System.out.println("输入不是11位");
      					continue;
      				}
      			}
      			System.out.println("骁龙二部健身会员管理系统>会员信息管理>修改地址为：");
      			String address;
      			//判断修改地址
      			st:while(true){
      				address= sc.nextLine();
      				if (address.equals("")) {
      					System.out.println("会员联系地址不能为空！请重新输入会员联系地址:");
      					continue;
      				}
      				//判断地址中文字符
      				Pattern p_str = Pattern.compile("[\\u4e00-\\u9fa5]+");
      				Matcher m = p_str.matcher(address);
      				if (m.find() && m.group(0).equals(address)) {
      					System.out.println("Ok！");
      					break;
      				}else{
      					System.out.println("里面有其他字符,不符合中文，请重新输入:");
      					continue st;
      				}
      			}
      			//调用修改地址和电话方法
      			addThis(phoneNumber, address, i);
      			break;
      		}
      	}
      		System.out.println("用户不存在，修改失败！系统返回！");
	
	}
	//修改地址和电话
	public void addThis(String phoneNumber,String address,int i){
		d.Member[i].setPhoneNumber(phoneNumber);
		d.Member[i].setAddress(address);
		System.out.println("修改成功！");
	}
	//查找
	public void select(int uu){
		initial();
		Scanner sc = new Scanner(System.in);
		st:while(true){
			System.out.println("---------------------------------------");
			System.out.println("       1.通过会员编号查询");
			System.out.println("       2.通过会员名查询");
			System.out.println("       3.通过会员生日查询");
			System.out.println("       4.通过会员手机号查询");
			System.out.println("       5.通过会员地址查询");
			System.out.println("请选择，输入数字或按'n'返回上级菜单：");
	        String number,name,bornth,phoneNumber,address,choice;
	        //获取选择
        	choice = sc.nextLine();
        	switch(choice){
        	//判断会员编号
              case "1":
            	System.out.println("请输如要查询的会员编号：");
            	boolean e1=false;
              	number = sc.nextLine();
              	//判断存在否
            	for(int i=0;i<p;i++){
              		if(d.Member[i].getId()==uu&&d.Member[i].getNumber().equals(number)){
              			e1=true;
              		}
              	}
             	if(!e1){
  					System.out.println("用户不存在或您的查询方式可能有问题。");
  					System.out.println("是否继续（y/n）");
  					choice = sc.nextLine();
  					if(choice.equals("y")){
  						continue;
  					}else if(choice.equals("n")){
  						break st;
  					}else{
  						System.out.println("输入有误");
  						break st;
  					}
  				}
             	//输出
             	System.out.println("        会员号\t	                                姓名\t\t                      生日\t\t                  地址\t\t                手机号\t\t");
        		System.out.println("-------------|------------------------|-----------------------|-----------------------|-----------------------");
  				for(int i=0;i<p;i++){
  					if(d.Member[i].getId()==uu&&d.Member[i].getNumber().equals(number)&&d.Member[i].getName()!=null){
  						//System.out.println("      "+d.Member[i].getNumber()+"\t                  "+d.Member[i].getName()+"\t                 "+d.Member[i].getBornth()+"\t                 "+d.Member[i].getAddress()+"\t                   "+d.Member[i].getPhoneNumber());
  						System.out.println("   "+d.Member[i].getNumber()+"\t\t"+"                               "+d.Member[i].getName()+"\t\t"+"     "+d.Member[i].getBornth()+"\t\t"+"                 "+d.Member[i].getAddress()+"\t\t"+"   "+d.Member[i].getPhoneNumber());
  						continue;
  					}
  				}
             
              	break st;
              	
              	//会员姓名
              case "2":
  				System.out.println("请输如要查询的会员姓名\t：");
  				boolean e2=false;
  				int k=0;
  				name = sc.nextLine();
  				//判断姓名为空
  				while("".equals(name)){
  					k++;
  					if(k>2){
  						System.out.println("输入次数太多，系统返回！");
  						break st;
  					}
  					System.out.println("输入不能为空！请重新输入：");
  					name = sc.nextLine();
  				}
  				//如果存在就让e2为true
  				for(int i=0;i<p;i++){
  					if(d.Member[i].getName().equals(name)){
  						e2=true;
  					}
  				}
  				if(!e2){
  					System.out.println("用户不存在或您的查询方式可能有问题。");
  					System.out.println("是否继续（y/n）");
  					choice = sc.nextLine();
  					if(choice.equals("y")){
  						continue;
  					}else if(choice.equals("n")){
  						break st;
  					}else{
  						System.out.println("输入有误");
  						break st;
  					}
  				}
  				
  				System.out.println("        会员号\t	                                姓名\t\t                      生日\t\t                  地址\t\t                手机号\t\t");
  				System.out.println("-------------|------------------------|-----------------------|-----------------------|-----------------------");
  				for(int i=0;i<p;i++){
  					if(d.Member[i].getId()==uu&&d.Member[i].getName().equals(name)&&d.Member[i].getName()!=null){
  						//System.out.println("   "+d.Member[i].getNumber()+"        "+d.Member[i].getName()+"                "+d.Member[i].getBornth()+"                    "+d.Member[i].getAddress()+"                      "+d.Member[i].getPhoneNumber());
  						System.out.println("   "+d.Member[i].getNumber()+"\t\t"+"                               "+d.Member[i].getName()+"\t\t"+"     "+d.Member[i].getBornth()+"\t\t"+"                 "+d.Member[i].getAddress()+"\t\t"+"   "+d.Member[i].getPhoneNumber());
  						continue;
  					}
  				}
  				
				break st;
			//生日
              case "3":			
            	System.out.println("请输如要查询的会员生日\t（严格查询格式(年.月.日)如，1999.12.12）：");
				boolean e3 = false;
            	bornth = sc.nextLine();
            	for(int i=0;i<p;i++){
					if(d.Member[i].getId()==uu&&d.Member[i].getBornth().equals(bornth)){
						e3=true;
					}
				}
            	
            	if(!e3){
  					System.out.println("用户不存在或您的查询方式可能有问题。");
  					System.out.println("是否继续（y/n）");
  					choice = sc.nextLine();
  					if(choice.equals("y")){
  						continue;
  					}else if(choice.equals("n")){
  						break st;
  					}else{
  						System.out.println("输入有误");
  						break st;
  					}
  				}
            	System.out.println("        会员号\t	                                姓名\t\t                      生日\t\t                  地址\t\t                手机号\t\t");
        		System.out.println("-------------|------------------------|-----------------------|-----------------------|-----------------------");
				for(int i=0;i<p;i++){
					if(d.Member[i].getId()==uu&&d.Member[i].getBornth().equals(bornth)){
						//System.out.println("   "+d.Member[i].getNumber()+"        "+d.Member[i].getName()+"                "+d.Member[i].getBornth()+"                    "+d.Member[i].getAddress()+"                      "+d.Member[i].getPhoneNumber());
						System.out.println("   "+d.Member[i].getNumber()+"\t\t"+"                               "+d.Member[i].getName()+"\t\t"+"     "+d.Member[i].getBornth()+"\t\t"+"                 "+d.Member[i].getAddress()+"\t\t"+"   "+d.Member[i].getPhoneNumber());
						continue;
					}
				}
              	break st;
              	//手机号判断
              case "4":            	
            	System.out.println("请输如要查询的会员的手机号\t：");
            	boolean e4 = false;
				phoneNumber = sc.nextLine();
				for(int i=0;i<p;i++){
					if(d.Member[i].getId()==uu&&d.Member[i].getPhoneNumber().equals(phoneNumber)){
						e4=true;
					}
				}
				if(!e4){
  					System.out.println("用户不存在或您的查询方式可能有问题。");
  					System.out.println("是否继续（y/n）");
  					choice = sc.nextLine();
  					if(choice.equals("y")){
  						continue;
  					}else if(choice.equals("n")){
  						break st;
  					}else{
  						System.out.println("输入有误");
  						break st;
  					}
  				}
				System.out.println("        会员号\t	                                姓名\t\t                      生日\t\t                  地址\t\t                手机号\t\t");
				System.out.println("-------------|------------------------|-----------------------|-----------------------|-----------------------");
				for(int i=0;i<p;i++){
					if(d.Member[i].getId()==uu&&d.Member[i].getPhoneNumber().equals(phoneNumber)){
						//System.out.println("   "+d.Member[i].getNumber()+"        "+d.Member[i].getName()+"                "+d.Member[i].getBornth()+"                    "+d.Member[i].getAddress()+"                      "+d.Member[i].getPhoneNumber());
						System.out.println("   "+d.Member[i].getNumber()+"\t\t"+"                               "+d.Member[i].getName()+"\t\t"+"     "+d.Member[i].getBornth()+"\t\t"+"                 "+d.Member[i].getAddress()+"\t\t"+"   "+d.Member[i].getPhoneNumber());
						continue;
					}
				}
			
            	break st;
            	//会员地址判断
              case "5":      	
            	System.out.println("请输如要查询的会员的地址\t如，重庆：");
            	boolean e5 =false;
				address = sc.nextLine();
				//
				for(int i=0;i<p;i++){
					if(d.Member[i].getId()==uu&&d.Member[i].getAddress().equals(address)){
						e5=true;
					}
				}
				if(!e5){
  					System.out.println("用户不存在或您的查询方式可能有问题。");
  					System.out.println("是否继续（y/n）");
  					choice = sc.nextLine();
  					if(choice.equals("y")){
  						continue;
  					}else if(choice.equals("n")){
  						break st;
  					}else{
  						System.out.println("输入有误");
  						break st;
  					}
  				}
				System.out.println("        会员号\t	                                姓名\t\t                      生日\t\t                  地址\t\t                手机号\t\t");
				System.out.println("-------------|------------------------|-----------------------|-----------------------|-----------------------");
				for(int i=0;i<p;i++){
					if(d.Member[i].getId()==uu&&d.Member[i].getAddress().equals(address)){
						//System.out.println("   "+d.Member[i].getNumber()+"        "+d.Member[i].getName()+"                "+d.Member[i].getBornth()+"                    "+d.Member[i].getAddress()+"                      "+d.Member[i].getPhoneNumber());
						System.out.println("   "+d.Member[i].getNumber()+"\t\t"+"                               "+d.Member[i].getName()+"\t\t"+"     "+d.Member[i].getBornth()+"\t\t"+"                 "+d.Member[i].getAddress()+"\t\t"+"   "+d.Member[i].getPhoneNumber());
						continue;
					}
				}
              	break st;
              case "n":
              	break st;
              }
        	
        }
      
	}	
	


	//列出所有的Member
	public void list(int uu){
		System.out.println("        会员号\t	                                姓名\t\t                      生日\t\t                  地址\t\t                手机号\t\t");
		System.out.println("-------------|------------------------|-----------------------|-----------------------|-----------------------");
		for(int i=0;i<d.Member.length ;i++){
			if(d.Member[i].getId()==uu&&d.Member[i].getName()!=null){
				System.out.println("   "+d.Member[i].getNumber()+"\t\t"+"                               "+d.Member[i].getName()+"\t\t"+"     "+d.Member[i].getBornth()+"\t\t"+"                 "+d.Member[i].getAddress()+"\t\t"+"   "+d.Member[i].getPhoneNumber());
			}
		}
		initial();
	}
	
		
}
