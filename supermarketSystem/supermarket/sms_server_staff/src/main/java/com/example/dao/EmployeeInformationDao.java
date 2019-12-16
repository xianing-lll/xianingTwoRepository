package com.example.dao;

import com.example.domain.EmployeeAuth;
import com.example.domain.EmployeeInformation;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeInformationDao {

    /**
     * 1、注册，添加员工信息
     * @param employeeInformation  员工信息
     * @param employeeAuth  员工角色（数据库中一个员工可以有）
     * @return
     */
    @Insert("INSERT INTO employee_information ( employee_password, employee_name, employee_mobile, employee_id_card, employee_entry_time )\n" +
            "VALUES\n" +
            "\t( #{employeePassWord}, #{employeeName}, #{employeeMobile}, #{employeeIdCard}, #{employeeEntryTime} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "employeeId", keyColumn = "employee_id") //获取自增主键
    public Boolean addEmployeeInformaton(EmployeeInformation employeeInformation);

    /**
     * 2、通过员工身份证号查询员工账号
     * @param employeeIdCard
     * @return EmployeeInformation
     */
    @Select("SELECT * FROM employee_information WHERE employee_id_card=#{employeeIdCard}")
    public EmployeeInformation findeEmployeeInformationByEmployeeIdCard(String employeeIdCard);

    /**
     *3、 添加员工角色信息，和addEmployeeInformatonDao同时使用,完成员工注册
     * @param employeeAuth员工角色信息
     * @return Boolean
     */
    @Insert("INSERT INTO employee_auth(employee_id,employee_authoritie) VALUES(#{employeeId},#{employeeAuthoritie});")
    public Boolean addEmployeeAuth(EmployeeAuth employeeAuth);





    /**
     * 4、查询所有角色（和EmployeeInformation方法组和，查询所有员工信息）
     * @return EmployeeAuth
     */
    @Select("SELECT * FROM employee_auth")
    public EmployeeAuth findAllEmployeeAuth();

    /**
     * 5、通过员工号查询员工角色
     * @param employeeId
     * @return
     */
    @Select("SELECT * FROM employee_auth WHERE employee_id=#{employeeId}")
    public ArrayList<EmployeeAuth> findEmployeeAuthByEmployeeId(int employeeId);

    /**
     * 6、查找所有员工的基本信息(包括角色)
     * @return
     */
    @Select("SELECT * FROM employee_information")
    @Results({
            @Result(id = true, column = "employee_id", property = "employeeId"),
            @Result(property = "employeeAuths", column = "employee_id",
                    many = @Many(select = "com.example.dao.EmployeeInformationDao.findEmployeeAuthByEmployeeId"))
    })
    public List<EmployeeInformation> findAllEmployeeInformation();





    /**
     * 7、通过员工账号查询员工信息包括角色
     * @param employeeId
     * @return
     */
    @Select("SELECT * FROM employee_information WHERE employee_id=#{employeeId}")
    @Results({
            @Result(id = true, column = "employee_id", property = "employeeId"),
            @Result(property = "employeeAuths", column = "employee_id",
                    many = @Many(select = "com.example.dao.EmployeeInformationDao.findEmployeeAuthByEmployeeId"))
    })
    public EmployeeInformation findEmployeeInformationByEmployeeId(int employeeId);

    /**
     * 查询所有未审批的员工
     * @return
     */
    @Select("SELECT * FROM employee_information WHERE employee_regular=0")
    @Results({
            @Result(id = true, column = "employee_id", property = "employeeId"),
            @Result(property = "employeeAuths", column = "employee_id",
                    many = @Many(select = "com.example.dao.EmployeeInformationDao.findEmployeeAuthByEmployeeId"))
    })
    public List<EmployeeInformation> findEmployeeInformationByEmployeeRegular();
    /**
     * 8、员工信息搜索引擎
     * @param any
     * @return
     */
    @Select("SELECT\n" +
            "\temployee_id,employee_name,employee_mobile,employee_id_card,employee_entry_time,employee_regular \n" +
            "FROM\n" +
            "\temployee_information \n" +
            "WHERE\n" +
            "\temployee_id LIKE CONCAT('%',#{employeeId},'%')  \n" +
            "\tOR employee_name LIKE CONCAT('%',#{employeeName},'%') \n" +
            "\tOR employee_mobile LIKE CONCAT('%',#{employeeMobile},'%') \n" +
            "\tOR employee_id_card LIKE CONCAT('%',#{employeeIdCard},'%') \n" +
            "\tOR employee_entry_time LIKE CONCAT('%',#{employeeEntryTime},'%')")
    @Results({
            @Result(id = true, column = "employee_id", property = "employeeId"),
            @Result(property = "employeeAuths", column = "employee_id",
                    many = @Many(select = "com.example.dao.EmployeeInformationDao.findEmployeeAuthByEmployeeId"))
    })
    public ArrayList<EmployeeInformation> findEmployeeInformationByAnyone(String any);



    /**
     *9、 通过员工工号修改密码
     * @param employeeId
     * @param employeePassWord
     * @return
     */
    @Update("UPDATE employee_information SET employee_password=#{employeePassWord} WHERE employee_id=#{employeeId}")
    public Boolean updatEemployeePassWordByEmployeeId(int employeeId,String employeePassWord);




    /**
     * 10、修改员工为正式员工
     * @param EmployeeId
     * @return
     */
    @Update("UPDATE employee_information SET employee_regular=1 WHERE employee_id=#{employeeId}")
    public boolean updateemployeeRegularByEmployeeId(int employeeId);




    /**
     * 11、通过员工号修改职位
     * @param employeeAuthoritie  员工角色
     * @param employeeId
     * @return
     */
    @Update("UPDATE employee_auth SET employee_authoritie=#{employeeAuthoritie} WHERE auth_id=#{authId}")
    public boolean updateEmployeeAuthoritie(String employeeAuthoritie, int authId);



    /**
     * 12、通过员工号和角色删除员工的某一个角色
     * @param EmployeeAuth
     * @param employeeAuthoritie
     * @return
     */
    @Delete("DELETE FROM employee_auth WHERE employee_id=#{employeeId} AND employee_authoritie=#{employeeAuthoritie}")
    public Boolean deleteEmployeeAuth(int employeeId,String employeeAuthoritie);

    /**
     * 通过角色id删除某个角色
     * @param authId
     * @return
     */
    @Delete("DELETE FROM employee_auth WHERE auth_id=#{authId}")
    public Boolean deleteEmployeeAuthByAuthId(int authId);

    /**
     * 13、离职处理，删除员工信息(数据库中有主键，会自动删除角色信息)
     * @param employeeId
     * @return
     */
    @Delete("DELETE FROM employee_information WHERE employee_id=#{employeeId}")
    public boolean deleteEmployeeInformationByEmployeeId(int employeeId);
}
