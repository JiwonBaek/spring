package com.newlecture.javaweb.dao;

import com.newlecture.javaweb.entity.Member;

public interface MemberDao  {



	int insert(Member member);

	Member get(String id);

	int insert(String id, String pwd, String name, String gender, String birthday, String phone, String email);

	int insert(String id, String pwd, String name, String phone, String email);



	

}
