package com.elcom.data.user.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import com.elcom.common.DataUtils;
import com.elcom.data.BaseRepository;
import com.elcom.data.exception.NoRecordFoundException;
import com.elcom.data.interview.entity.dto.UserDataPaging;
import com.elcom.data.repository.IUpsertRepository;
import com.elcom.data.user.entity.User;
import com.elcom.model.dto.InterviewUserDTO;
import com.elcom.model.dto.interview.EmployeeDTO;
import com.elcom.model.dto.interview.EmployeeDataPaging;
import com.elcom.model.dto.interview.EmployeeEmailDTO;
import com.elcom.model.dto.interview.InterviewUserDataPaging;
import com.elcom.model.object.MenuFunction;
import com.elcom.model.object.ServicePermission;
import com.elcom.model.object.UserRole;
import com.elcom.util.StringUtils;
import com.elcom.util.security.SecurityUtil;

public class UsersRepository extends BaseRepository implements IUpsertRepository<User> {

    public UsersRepository(Session session) {
        super(session);
    }

    public void update(User item) {
        this.session.update(item);
    }

    public void upsert(User item) {
        this.session.saveOrUpdate("User", item);
    }

    public void remove(User item) {
        this.session.delete("User", item);
    }

    public List<User> findAllUsers() {
        return this.session.createQuery("from User", User.class).list();
    }

    public User findUser(String uuid) {
        return this.session.createQuery("from User where uuid = :uuid", User.class)
                .setParameter("uuid", uuid)
                .uniqueResult();
    }

    public Long insert(InterviewUserDTO item) {

        String salt = "";
        String password = "";
        try {
            salt = SecurityUtil.generateSalt();
            password = SecurityUtil.getHash(20, item.getPassword().trim(), salt);
        } catch (Exception ex) {
            System.out.println("UsersRepository.insert.ex: " + ex.toString());
            return 0L;
        }

        String uuid = UUID.randomUUID().toString();
        @SuppressWarnings("rawtypes")
        NativeQuery query = this.session.getNamedNativeQuery("insertUser")
                .setParameter("email", item.getEmail().trim())
                .setParameter("password", password)
                .setParameter("salt_value", salt)
                .setParameter("full_name", item.getFullName())
                .setParameter("mobile", item.getMobile())
                .setParameter("skype", item.getSkype())
                .setParameter("facebook", item.getFacebook())
                .setParameter("avatar", item.getAvatar())
                .setParameter("user_type", item.getUserType())
                .setParameter("company_id", item.getCompanyId())
                .setParameter("uuid", uuid)
                .setParameter("address", item.getAddress());
        Long lastId = 0L;
        if (query.executeUpdate() > 0) {
            query = this.session.createSQLQuery("SELECT LAST_INSERT_ID()");
            lastId = ((BigInteger) query.uniqueResult()).longValue();
        }
        return lastId;
    }

    public boolean update(InterviewUserDTO item) {

        @SuppressWarnings("rawtypes")
        NativeQuery query = this.session.getNamedNativeQuery("updateUser")
                .setParameter("full_name", item.getFullName())
                .setParameter("mobile", item.getMobile())
                .setParameter("skype", item.getSkype())
                .setParameter("avatar", item.getAvatar())
                .setParameter("address", item.getAddress())
                .setParameter("uuid", item.getUuid());

        return query.executeUpdate() >= 1;
    }

    public boolean delete(String uuid) {

        @SuppressWarnings("rawtypes")
        NativeQuery query = this.session.getNamedNativeQuery("deleteEmployee")
                .setParameter("uuid", uuid.trim());

        return query.executeUpdate() >= 1;
    }

    public boolean changePassword(String uuid, String newPassword, String changeType) {

        String salt = "";
        String password = "";
        try {
            salt = SecurityUtil.generateSalt();
            password = SecurityUtil.getHash(20, newPassword, salt);
        } catch (Exception ex) {
            System.out.println("changePassword.ex: " + ex.toString());
            return false;
        }

        @SuppressWarnings("rawtypes")
        NativeQuery query = this.session.getNamedNativeQuery("USER".equals(changeType) ? "changePasswordUser" : "changePasswordCompany")
                .setParameter("password", password)
                .setParameter("salt_value", salt)
                .setParameter("uuid", uuid);

        return query.executeUpdate() >= 1;
    }

    public Integer checkMainInfoExists(String checkType, String info, Integer table) {
        String queryName = "";
        String paramName = "";
        if ("mobile".equals(checkType)) {
            queryName = "checkMobileExists";
            paramName = "mobile";
        } else if ("email".equals(checkType)) {
            queryName = "checkEmailExists";
            if (table != null && table == 1) {
                queryName = "checkEmailExistsUser";
            } else if (table != null && table == 2) {
                queryName = "checkEmailExistsCompany";
            }
            paramName = "email";
        }
        @SuppressWarnings("rawtypes")
        NativeQuery query = this.session.getNamedNativeQuery(queryName)
                .setParameter(paramName, info.trim());
        return (Integer) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<User> findActiveEmployee() {

        Query<?> query = this.session.getNamedNativeQuery("findActiveEmployee");

        List<?> lst = query.list();

        if (lst != null && !lst.isEmpty()) {
            return transformList(lst, User.class);
        }

        return null;
    }

    @SuppressWarnings("deprecation")
    public User findUser(String accountName, String password, String loginType) {

        User user = null;

        try {
            @SuppressWarnings("rawtypes")
            NativeQuery query = this.session.getNamedNativeQuery("login");
            query.setParameter("accountName", accountName.trim());
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("email", StandardBasicTypes.STRING);
            query.addScalar("password", StandardBasicTypes.STRING);
            query.addScalar("saltValue", StandardBasicTypes.STRING);
            query.addScalar("fullName", StandardBasicTypes.STRING);
            query.addScalar("mobile", StandardBasicTypes.STRING);
            query.addScalar("skype", StandardBasicTypes.STRING);
            query.addScalar("facebook", StandardBasicTypes.STRING);
            query.addScalar("avatar", StandardBasicTypes.STRING);
            query.addScalar("userType", StandardBasicTypes.INTEGER);
            query.addScalar("status", StandardBasicTypes.INTEGER);
            query.addScalar("createdAt", StandardBasicTypes.TIMESTAMP);
            query.addScalar("lastLogin", StandardBasicTypes.TIMESTAMP);
            query.addScalar("uuid", StandardBasicTypes.STRING);
            query.addScalar("address", StandardBasicTypes.STRING);
            query.addScalar("companyId", StandardBasicTypes.LONG);
            query.addScalar("companyName", StandardBasicTypes.STRING);
            query.addScalar("careerId", StandardBasicTypes.LONG);
            query.setResultTransformer(Transformers.aliasToBean(User.class));

            user = (User) query.uniqueResult();

            if (user != null && SecurityUtil.isMatched(password.trim(), user.getPassword(), user.getSaltValue(), 20)) {
                if ("CONTINUE-CHECK".equals(loginType)) {
                    //Neu dang nhap cty hoac nha tuyen dung ==> Bo qua user
                    if (user.getUserType() == 3) {
                        return null;
                    }
                } else {
                    //Neu dang nhap user (NORMAL) ==> Bo qua nha tuyen dung
                    if(user.getUserType() == 2){
                        return null;
                    }
                }

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("deprecation")
    public boolean checkCurrentPassword(String uuid, String currentPassword, String changeType) {

        try {
            @SuppressWarnings("rawtypes")
            NativeQuery query = this.session.getNamedNativeQuery("USER".equals(changeType) ? "checkCurrentPasswordUser" : "checkCurrentPasswordCompany");
            query.setParameter("uuid", uuid.trim());
            query.addScalar("password", StandardBasicTypes.STRING);
            query.addScalar("saltValue", StandardBasicTypes.STRING);
            query.setResultTransformer(Transformers.aliasToBean(User.class));

            User user = (User) query.uniqueResult();

            if (user != null && SecurityUtil.isMatched(currentPassword.trim(), user.getPassword(), user.getSaltValue(), 20)) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @SuppressWarnings("deprecation")
    public User findUserByUUID(String uuid) {

        User user = null;

        try {
            @SuppressWarnings("rawtypes")
            NativeQuery query = this.session.getNamedNativeQuery("findUserByUUID");
            query.setParameter("uuid", uuid.trim());
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("email", StandardBasicTypes.STRING);
            query.addScalar("password", StandardBasicTypes.STRING);
            query.addScalar("saltValue", StandardBasicTypes.STRING);
            query.addScalar("fullName", StandardBasicTypes.STRING);
            query.addScalar("mobile", StandardBasicTypes.STRING);
            query.addScalar("skype", StandardBasicTypes.STRING);
            query.addScalar("facebook", StandardBasicTypes.STRING);
            query.addScalar("avatar", StandardBasicTypes.STRING);
            query.addScalar("userType", StandardBasicTypes.INTEGER);
            query.addScalar("status", StandardBasicTypes.INTEGER);
            query.addScalar("createdAt", StandardBasicTypes.TIMESTAMP);
            query.addScalar("lastLogin", StandardBasicTypes.TIMESTAMP);
            query.addScalar("uuid", StandardBasicTypes.STRING);
            query.addScalar("address", StandardBasicTypes.STRING);
            query.addScalar("companyName", StandardBasicTypes.STRING);
            query.setResultTransformer(Transformers.aliasToBean(User.class));

            user = (User) query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @SuppressWarnings("deprecation")
    public User findUserByUUIDToUpdate(String uuid) {

        User user = null;

        try {
            @SuppressWarnings("rawtypes")
            NativeQuery query = this.session.getNamedNativeQuery("findUserByUUIDToUpdate");
            query.setParameter("uuid", uuid.trim());
            query.addScalar("email", StandardBasicTypes.STRING);
            query.addScalar("fullName", StandardBasicTypes.STRING);
            query.addScalar("mobile", StandardBasicTypes.STRING);
            query.addScalar("skype", StandardBasicTypes.STRING);
            query.addScalar("facebook", StandardBasicTypes.STRING);
            query.addScalar("avatar", StandardBasicTypes.STRING);
            query.addScalar("uuid", StandardBasicTypes.STRING);
            query.addScalar("address", StandardBasicTypes.STRING);
            query.addScalar("companyName", StandardBasicTypes.STRING);
            query.setResultTransformer(Transformers.aliasToBean(User.class));

            user = (User) query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    public UserDataPaging findAllUserByCompanyId(Long companyId) {

        UserDataPaging result = new UserDataPaging();

        try {

            String sql = " SELECT u.id, u.email, u.avatar, u.full_name as fullName "
                    + " FROM user u INNER JOIN company c ON c.id = u.company_id WHERE c.status = 1 and u.company_id = :company_id"
                    + " ORDER BY u.email ";

            NativeQuery query = this.session.createNativeQuery(sql);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("email", StandardBasicTypes.STRING);
            query.addScalar("avatar", StandardBasicTypes.STRING);
            query.addScalar("fullName", StandardBasicTypes.STRING);
            query.setResultTransformer(Transformers.aliasToBean(User.class));
            query.setParameter("company_id", companyId);

            Object lst = query.list();
            if (lst != null) {
                result.setDataRows((List<User>) lst);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    public EmployeeDataPaging findAll(Long departmentId, String fullName, String email, int page, int rowsPerPage,
            Integer status, Integer isDeleted) {

        EmployeeDataPaging result = new EmployeeDataPaging();

        if (page == 0) {
            page = 1;
        }
        if (rowsPerPage == 0) {
            rowsPerPage = 20; //Default
        }
        try {

            String condition = "";
            if (departmentId != null && !departmentId.equals(0L)) {
                condition += " AND e.department_id = :department_id ";
            }
            if (!StringUtils.isNullOrEmpty(fullName)) {
                condition += " AND e.full_name LIKE :full_name ";
            }
            if (!StringUtils.isNullOrEmpty(email)) {
                condition += " AND e.email LIKE :email ";
            }
            if (status != null) {
                condition += " AND e.status = :status ";
            }
            if (isDeleted != null) {
                condition += " AND e.is_deleted = :is_deleted ";
            }

            String tables = " employee e INNER JOIN departments d ON d.ID = e.department_id ";

            String sql = " SELECT COUNT(e.id) FROM " + tables + " WHERE 1 = 1 " + condition;
            NativeQuery query = this.session.createNativeQuery(sql);

            if (departmentId != null && !departmentId.equals(0L)) {
                query.setParameter("department_id", departmentId);
            }
            if (!StringUtils.isNullOrEmpty(fullName)) {
                query.setParameter("full_name", '%' + fullName + '%');
            }
            if (!StringUtils.isNullOrEmpty(email)) {
                query.setParameter("email", '%' + email + '%');
            }
            if (status != null) {
                query.setParameter("status", status);
            }
            if (isDeleted != null) {
                query.setParameter("is_deleted", isDeleted);
            }

            result.setTotalRows((BigInteger) query.uniqueResult());

            sql = " SELECT e.id, e.uuid, e.email, e.full_name as fullName, e.card_number as cardNumber, e.ext_number as extNumber, e.address, e.avatar, e.status "
                    + " , e.leave_days AS leaveDay, e.leave_time_apply AS leaveTimeApply, e.description "
                    + " , e.is_deleted as isDeleted, e.type, e.updated_at as updatedAt "
                    + " , d.department_name as departmentName FROM " + tables + " WHERE 1 = 1 " + condition;

            query = this.session.createNativeQuery(sql);

            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("uuid", StandardBasicTypes.STRING);
            query.addScalar("email", StandardBasicTypes.STRING);
            query.addScalar("fullName", StandardBasicTypes.STRING);
            query.addScalar("cardNumber", StandardBasicTypes.STRING);
            query.addScalar("extNumber", StandardBasicTypes.STRING);
            query.addScalar("address", StandardBasicTypes.STRING);
            query.addScalar("avatar", StandardBasicTypes.STRING);
            query.addScalar("status", StandardBasicTypes.INTEGER);
            query.addScalar("leaveDay", StandardBasicTypes.BIG_DECIMAL);
            query.addScalar("leaveTimeApply", StandardBasicTypes.DATE);
            query.addScalar("description", StandardBasicTypes.STRING);
            query.addScalar("isDeleted", StandardBasicTypes.INTEGER);
            query.addScalar("type", StandardBasicTypes.INTEGER);
            query.addScalar("updatedAt", StandardBasicTypes.LONG);
            query.addScalar("cardNumber", StandardBasicTypes.STRING);
            query.addScalar("departmentName", StandardBasicTypes.STRING);
            query.setResultTransformer(Transformers.aliasToBean(EmployeeDTO.class));
            query.setFirstResult((page - 1) * rowsPerPage);
            query.setMaxResults(rowsPerPage);

            if (departmentId != null && !departmentId.equals(0L)) {
                query.setParameter("department_id", departmentId);
            }
            if (!StringUtils.isNullOrEmpty(fullName)) {
                query.setParameter("full_name", '%' + fullName + '%');
            }
            if (!StringUtils.isNullOrEmpty(email)) {
                query.setParameter("email", '%' + email + '%');
            }
            if (status != null) {
                query.setParameter("status", status);
            }
            if (isDeleted != null) {
                query.setParameter("is_deleted", isDeleted);
            }

            Object lst = query.list();
            if (lst != null) {
                result.setDataRows((List<EmployeeDTO>) lst);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    public List<EmployeeEmailDTO> findEmailLst() {

        try {

            String sql = " SELECT id, email FROM employee WHERE status = 1 AND is_deleted = 0 ";

            @SuppressWarnings("rawtypes")
            NativeQuery query = this.session.createNativeQuery(sql);

            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("email", StandardBasicTypes.STRING);
            query.setResultTransformer(Transformers.aliasToBean(EmployeeEmailDTO.class));

            Object lst = query.list();
            if (lst != null) {
                return (List<EmployeeEmailDTO>) lst;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    public List<EmployeeDTO> findByManagerId(Long managerId) {

        try {

            String sql = " SELECT e.id, e.uuid, e.full_name as fullName, e.ext_number as extNumber, e.address, e.avatar, e.status "
                    + " , e.is_deleted as isDeleted, e.type, e.updated_at as updatedAt "
                    + " , d.department_name as departmentName FROM employee e INNER JOIN departments d ON d.ID = e.department_id "
                    + " WHERE d.manager = :managerId ";

            @SuppressWarnings("rawtypes")
            NativeQuery query = this.session.createNativeQuery(sql);
            query.setParameter("managerId", managerId);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("uuid", StandardBasicTypes.STRING);
            query.addScalar("fullName", StandardBasicTypes.STRING);
            query.addScalar("extNumber", StandardBasicTypes.STRING);
            query.addScalar("address", StandardBasicTypes.STRING);
            query.addScalar("avatar", StandardBasicTypes.STRING);
            query.addScalar("status", StandardBasicTypes.INTEGER);
            query.addScalar("isDeleted", StandardBasicTypes.INTEGER);
            query.addScalar("type", StandardBasicTypes.INTEGER);
            query.addScalar("updatedAt", StandardBasicTypes.LONG);
            query.addScalar("departmentName", StandardBasicTypes.STRING);
            query.setResultTransformer(Transformers.aliasToBean(EmployeeDTO.class));

            Object lst = query.list();
            if (lst != null) {
                return (List<EmployeeDTO>) lst;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @SuppressWarnings("deprecation")
    public User findUserInfoBy(String email) throws NoRecordFoundException {

        @SuppressWarnings("rawtypes")
        NativeQuery query = this.session.createNativeQuery(" SELECT id, login_id as loginId, email, full_name as fullName, status, company_id as companyId FROM users WHERE email = :emailAddress ");
        query.setParameter("emailAddress", email.trim());
        query.addScalar("id", StandardBasicTypes.LONG);
        query.addScalar("loginId", StandardBasicTypes.STRING);
        query.addScalar("email", StandardBasicTypes.STRING);
        query.addScalar("fullName", StandardBasicTypes.STRING);
        query.addScalar("status", StandardBasicTypes.INTEGER);
        query.addScalar("companyId", StandardBasicTypes.LONG);
        query.setResultTransformer(Transformers.aliasToBean(User.class));

        Object obj = null;
        try {
            obj = query.getSingleResult();
        } catch (NoResultException ex) {
            throw new NoRecordFoundException("User not found!");
        }

        return obj != null ? (User) obj : null;
    }

    public User findUserById(Long userId) {
        return this.session.createQuery("from User where id= :id", User.class).setParameter("id", userId).getSingleResult();
    }

    public User getUsersBySaleCode(String saleCode) {
        User item = null;
        List<?> lst = this.session.createQuery("from User where saleCode = :saleCode", User.class).setParameter("saleCode", saleCode != null ? saleCode.trim() : "").list();
        if (lst != null && !lst.isEmpty()) {
            item = (User) lst.get(0);
        }
        return item;
    }

    public User findUserByLoginId(String currentUsername) {

        List<?> ojb = this.session.getNamedQuery("findUserByLoginId").setParameter("currentUsername", currentUsername).getResultList();
        if (ojb != null && !ojb.isEmpty()) {
            return (User) ojb.get(0);
        }
        return null;
    }

    public List<MenuFunction> findUserMenu(String loginId) {
        @SuppressWarnings("unchecked")
        List<Object[]> objList = this.session.getNamedNativeQuery("findUserMenu")
                .setParameter("loginId", loginId)
                .getResultList();
        List<MenuFunction> retList = new ArrayList<MenuFunction>();
        for (Object[] objects : objList) {
            MenuFunction mf = new MenuFunction();
            mf = (MenuFunction) DataUtils.bindingData(mf, objects);
            retList.add(mf);
        }
        return retList;
    }

    public List<ServicePermission> findUserService(String loginId) {
        @SuppressWarnings("unchecked")
        List<Object[]> objList = this.session.getNamedNativeQuery("findUserService")
                .setParameter("loginId", loginId)
                .getResultList();
        List<ServicePermission> retList = new ArrayList<ServicePermission>();
        for (Object[] objects : objList) {
            ServicePermission sp = new ServicePermission();
            sp = (ServicePermission) DataUtils.bindingData(sp, objects);
            retList.add(sp);
        }
        return retList;
    }

    public List<UserRole> getUserRoles() {

        List<UserRole> result = new ArrayList<>();

        @SuppressWarnings("unchecked")
        List<Object[]> lst = this.session.getNamedNativeQuery("getUserRoles").list();

        if (lst != null && !lst.isEmpty()) {
            for (Object[] objs : lst) {
                result.add(new UserRole(String.valueOf(objs[0]), String.valueOf(objs[1])));
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public List<User> findUserByLoginId(String[] loginId) {
        return (List<User>) this.session.getNamedNativeQuery("findUserById")
                .setParameterList("loginId", loginId)
                .addEntity(User.class)
                .list();
    }

    public int changeStatusUser(Long userId, boolean isActive) {
        String status = isActive ? "A" : "C";
        String sqlString = "UPDATE users SET record_status = :status WHERE id = :userId";

        @SuppressWarnings("rawtypes")
        NativeQuery query = this.session.createNativeQuery(sqlString).setParameter("status", status).setParameter("userId", userId);

        return query.executeUpdate();

    }

    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    public InterviewUserDataPaging findUserByCompanyIdAndName(Long companyId, String name, int page, int rowsPerPage) {
        InterviewUserDataPaging result = new InterviewUserDataPaging();
        if (page == 0) {
            page = 1;
        }
        if (rowsPerPage == 0) {
            rowsPerPage = 20; //Default
        }
        try {
            String condition = "";
            if (companyId != null && !companyId.equals(0L)) {
                condition += " AND c.id = :id ";
            }
            if (!StringUtils.isNullOrEmpty(name)) {
                condition += " AND upper(c.name) LIKE upper(:name) ";
            }

            String tables = " user u INNER JOIN company c ON u.company_id = c.id ";
            String sql = " SELECT COUNT(u.id) FROM " + tables + " WHERE 1 = 1 " + condition;
            NativeQuery query = this.session.createNativeQuery(sql);
            if (companyId != null && !companyId.equals(0L)) {
                query.setParameter("id", companyId);
            }
            if (!StringUtils.isNullOrEmpty(name)) {
                query.setParameter("name", '%' + name + '%');
            }

            result.setTotalRows((BigInteger) query.uniqueResult());
            sql = " SELECT u.id, u.uuid, u.email, u.full_name as fullName, u.mobile, "
                    + " u.skype, u.facebook, u.avatar, u.user_type as userType, u.status, "
                    + " u.created_at AS createdAt, u.last_login as lastLogin, "
                    + " u.company_id as companyId, u.address FROM " + tables + " WHERE 1 = 1 "
                    + condition;
            query = this.session.createNativeQuery(sql);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("uuid", StandardBasicTypes.STRING);
            query.addScalar("email", StandardBasicTypes.STRING);
            query.addScalar("fullName", StandardBasicTypes.STRING);
            query.addScalar("mobile", StandardBasicTypes.STRING);
            query.addScalar("skype", StandardBasicTypes.STRING);
            query.addScalar("facebook", StandardBasicTypes.STRING);
            query.addScalar("avatar", StandardBasicTypes.STRING);
            query.addScalar("userType", StandardBasicTypes.INTEGER);
            query.addScalar("status", StandardBasicTypes.INTEGER);
            query.addScalar("createdAt", StandardBasicTypes.TIMESTAMP);
            query.addScalar("lastLogin", StandardBasicTypes.TIMESTAMP);
            query.addScalar("companyId", StandardBasicTypes.LONG);
            query.addScalar("address", StandardBasicTypes.STRING);
            query.setResultTransformer(Transformers.aliasToBean(User.class));
            query.setFirstResult((page - 1) * rowsPerPage);
            query.setMaxResults(rowsPerPage);

            if (companyId != null && !companyId.equals(0L)) {
                query.setParameter("id", companyId);
            }
            if (!StringUtils.isNullOrEmpty(name)) {
                query.setParameter("name", '%' + name + '%');
            }

            Object lst = query.list();
            if (lst != null) {
                result.setDataRows((List<InterviewUserDTO>) lst);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    public User findUserDetails(Long id, Long companyId) {
        User result = new User();
        try {
            String sql = " SELECT distinct(u.id), u.email, u.avatar, u.full_name as fullName, "
                    + " u.mobile, u.skype, u.facebook, u.address, c.id as cId, l.company_id as lCompanyId "
                    + " FROM user u LEFT JOIN company c ON c.id = u.company_id "
                    + " LEFT JOIN letter l ON u.email = l.email "
                    + " WHERE u.id = :id and (c.id = :cId or l.company_id = :lCompanyId) ";
            NativeQuery query = this.session.createNativeQuery(sql);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("email", StandardBasicTypes.STRING);
            query.addScalar("avatar", StandardBasicTypes.STRING);
            query.addScalar("fullName", StandardBasicTypes.STRING);
            query.addScalar("mobile", StandardBasicTypes.STRING);
            query.addScalar("skype", StandardBasicTypes.STRING);
            query.addScalar("facebook", StandardBasicTypes.STRING);
            query.addScalar("address", StandardBasicTypes.STRING);

            query.setResultTransformer(Transformers.aliasToBean(User.class));
            query.setParameter("id", id);
            query.setParameter("cId", companyId);
            query.setParameter("lCompanyId", companyId);

            Object lst = query.uniqueResult();
            if (lst != null) {
                result = (User) lst;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
