package com.elcom.sharedbiz.aggregate;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.elcom.data.UnitOfWork;
import com.elcom.data.exception.NoRecordFoundException;
import com.elcom.data.interview.entity.dto.UserDataPaging;
import com.elcom.data.user.entity.User;
import com.elcom.model.dto.CompanytDTO;
import com.elcom.model.dto.InterviewUserDTO;
import com.elcom.model.dto.interview.EmployeeDTO;
import com.elcom.model.dto.interview.EmployeeDataPaging;
import com.elcom.model.dto.interview.EmployeeEmailDTO;
import com.elcom.model.dto.interview.InterviewUserDataPaging;
import com.elcom.sharedbiz.dto.UserDTO;
import com.elcom.sharedbiz.validation.AuthorizationException;
import com.elcom.sharedbiz.validation.ValidationException;

public class UserAggregate {

    private UnitOfWork _uok = null;
    private static ModelMapper modelMapper = new ModelMapper();

    public UserAggregate(UnitOfWork uok) {
        this._uok = uok;
    }

    public User login(String accountName, String password, String loginType) throws AuthorizationException {
        User user = null;
        try {
            user = this._uok.user.usersRepository().findUser(accountName, password, loginType);

            /*if(user!=null ) {
				if ("anhdv@elcom.com.vn".equals(accountName))
		            user.setRoles(Arrays.asList(Role.ROLE_MANAGER));
		        else if ("anhdv1@elcom.com.vn".equals(accountName))
		            user.setRoles(Arrays.asList(Role.ROLE_EMPLOYEE));
		        else if ("anhdv2@elcom.com.vn".equals(accountName))
		            user.setRoles(Arrays.asList(Role.ROLE_MANAGER, Role.ROLE_EMPLOYEE));
		        else // anhdv3@elcom.com.vn
		            user.setRoles(new ArrayList<>());
			}*/
        } catch (Throwable e) {
            throw new AuthorizationException("AccountName or Password is invalid.");
        }

        return user;
    }

    public CompanytDTO loginCompany(String email, String password) throws AuthorizationException {
        CompanytDTO company = null;
        try {
            company = this._uok.vi.companyRepository().login(email, password);
        } catch (Throwable ex) {
            throw new AuthorizationException("Email or Password is invalid.");
        }

        return company;
    }

    public User findUserByUUID(String uuid) throws AuthorizationException {
        User user = null;
        try {
            user = this._uok.user.usersRepository().findUserByUUID(uuid);

            /*if(user!=null ) {
				if ("anhdv@elcom.com.vn".equals(email))
		            user.setRoles(Arrays.asList(Role.ROLE_MANAGER));
		        else if ("anhdv1@elcom.com.vn".equals(email))
		            user.setRoles(Arrays.asList(Role.ROLE_EMPLOYEE));
		        else if ("anhdv2@elcom.com.vn".equals(email))
		            user.setRoles(Arrays.asList(Role.ROLE_MANAGER, Role.ROLE_EMPLOYEE));
		        else // anhdv3@elcom.com.vn
		            user.setRoles(new ArrayList<>());
			}*/
        } catch (Throwable ex) {
            System.out.println("UserAggregate.findUserByUUID.ex: " + ex.toString());
            throw new AuthorizationException("UUID is invalid.");
        }

        return user;
    }

    public User findDetails(String uuid) throws Exception {

        return this._uok.user.usersRepository().findUserByUUIDToUpdate(uuid);
    }

    public EmployeeDataPaging findAll(Long departmentId, String fullName, String email, int page, int rowsPerPage,
            Integer status, Integer isDeleted) throws Exception {

        return this._uok.user.usersRepository().findAll(departmentId, fullName, email, page, rowsPerPage, status, isDeleted);
    }

    public UserDataPaging findAllUserByCompanyId(Long companyId) throws Exception {

        return this._uok.user.usersRepository().findAllUserByCompanyId(companyId);
    }

    public List<EmployeeEmailDTO> findEmailLst() throws Exception {

        return this._uok.user.usersRepository().findEmailLst();
    }

    public List<EmployeeDTO> findByManagerId(Long managerId) throws Exception {

        return this._uok.user.usersRepository().findByManagerId(managerId);
    }

    public Long insert(InterviewUserDTO item) {

        return this._uok.user.usersRepository().insert(item);
    }

    public boolean update(InterviewUserDTO item) {

        return this._uok.user.usersRepository().update(item);
    }

    public boolean delete(String uuid) {

        return this._uok.user.usersRepository().delete(uuid);
    }

    public Integer checkMainInfoExists(String checkType, String info, Integer table) {
        
        return this._uok.user.usersRepository().checkMainInfoExists(checkType, info, table);
    }

    public boolean changePassword(String uuid, String newPassword, String changeType) {

        return this._uok.user.usersRepository().changePassword(uuid, newPassword, changeType);
    }

    public boolean checkCurrentPassword(String uuid, String currentPassword, String changeType) {

        return this._uok.user.usersRepository().checkCurrentPassword(uuid, currentPassword, changeType);
    }

    public UserDTO getUserInfoBy(String email) throws ValidationException, NoRecordFoundException {

        User user = this._uok.user.usersRepository().findUserInfoBy(email);

        return user != null ? modelMapper.map(user, UserDTO.class) : null;
    }

    public UserDTO isActiveUser(String loginId) throws ValidationException {
        User user = this._uok.user.usersRepository().findUserByLoginId(loginId.toLowerCase());

        return user != null ? modelMapper.map(user, UserDTO.class) : null;
    }

    public User findUserByUserId(Long userId) throws Exception {
        return this._uok.user.usersRepository().findUserById(userId);
    }

    public User findUserByLoginId(String currentUsername) {
        return this._uok.user.usersRepository().findUserByLoginId(currentUsername);
    }

    public InterviewUserDataPaging findUserByCompanyIdAndName(Long companyId, String name,
            int page, int rowsPerPage) throws Exception {
        return this._uok.user.usersRepository().findUserByCompanyIdAndName(companyId, name,
                page, rowsPerPage);
    }
    
    public User findUserDetails(Long id, Long companyId) throws Exception {
        return this._uok.user.usersRepository().findUserDetails(id, companyId);
    }
}
