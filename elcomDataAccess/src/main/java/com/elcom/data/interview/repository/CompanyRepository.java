package com.elcom.data.interview.repository;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.elcom.data.BaseRepository;
import com.elcom.data.interview.entity.Company;
import com.elcom.data.interview.entity.dto.CompanyLessDTO;
import com.elcom.data.repository.IUpsertRepository;
import com.elcom.model.dto.CompanyUpsertDTO;
import com.elcom.model.dto.CompanytDTO;
import com.elcom.util.security.SecurityUtil;

public class CompanyRepository extends BaseRepository implements IUpsertRepository<Company> {

    public CompanyRepository(Session session) {
        super(session);
    }

    public boolean insert(CompanyUpsertDTO item) {

        String salt = "";
        String password = "";
        try {
            salt = SecurityUtil.generateSalt();
            password = SecurityUtil.getHash(20, item.getPassword().trim(), salt);
        } catch (Exception ex) {
            System.out.println("UsersRepository.insert.ex: " + ex.toString());
            return false;
        }
        String uuid = UUID.randomUUID().toString();

        @SuppressWarnings("rawtypes")
        NativeQuery query = this.session.getNamedNativeQuery("insertCompany")
                .setParameter("name", item.getName())
                .setParameter("address", item.getAddress())
                .setParameter("email", item.getEmail())
                .setParameter("office_phone", item.getOfficePhone())
                .setParameter("hotline", item.getHotline())
                .setParameter("website", item.getWebsite())
                .setParameter("status", item.getStatus())
                .setParameter("package", item.get_package())
                .setParameter("career_id", item.getCareerId())
                .setParameter("package_start", item.getPackageStart())
                .setParameter("package_end", item.getPackageEnd())
                .setParameter("password", password)
                .setParameter("salt_value", salt)
                .setParameter("uuid", uuid);

        return query.executeUpdate() >= 1;
    }

    public boolean update(CompanyUpsertDTO item) {

        @SuppressWarnings("rawtypes")
        NativeQuery query = this.session.getNamedNativeQuery("updateCompany")
                .setParameter("name", item.getName())
                .setParameter("address", item.getAddress())
                .setParameter("office_phone", item.getOfficePhone())
                .setParameter("hotline", item.getHotline())
                .setParameter("website", item.getWebsite())
                .setParameter("status", item.getStatus())
                .setParameter("package", item.get_package())
                .setParameter("career_id", item.getCareerId())
                .setParameter("package_start", item.getPackageStart())
                .setParameter("package_end", item.getPackageEnd())
                .setParameter("id", item.getId());

        return query.executeUpdate() >= 1;
    }

    /*@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public DepartmentDataPaging findAll(String departmentName, Integer status, int page, int rowsPerPage) {
		
		DepartmentDataPaging result = new DepartmentDataPaging();
		
		if( page == 0 ) page = 1;
		if( rowsPerPage == 0 ) rowsPerPage = 20; //Default
			
		try {
			
			String condition = "";
			if( !StringUtils.isNullOrEmpty(departmentName) )
				condition += " AND department_name LIKE :department_name ";
			if( status != null )
				condition += " AND department_status = :status ";
			
			String tables = " departments ";
			
			String sql = " SELECT COUNT(id) FROM " + tables + " WHERE 1 = 1 " + condition;
			NativeQuery query = this.session.createNativeQuery(sql);
			
			if( !StringUtils.isNullOrEmpty(departmentName) )
				query.setParameter("department_name", '%' + departmentName + '%');
			if( status != null )
				query.setParameter("status", status);
			
			result.setTotalRows((BigInteger) query.uniqueResult());
			
			sql = " SELECT id, department_name as departmentName, department_desc as departmentDesc, parent_id as parentId " 
						+ " , department_status as departmentStatus, manager "
						+ "  FROM " + tables + " WHERE 1 = 1 " + condition;
			
			query = this.session.createNativeQuery(sql);
			
			query.addScalar("id", StandardBasicTypes.LONG);
			query.addScalar("departmentName", StandardBasicTypes.STRING);
			query.addScalar("departmentDesc", StandardBasicTypes.STRING);
			query.addScalar("parentId", StandardBasicTypes.LONG);
			query.addScalar("departmentStatus", StandardBasicTypes.INTEGER);
			query.addScalar("manager", StandardBasicTypes.LONG);
			query.setResultTransformer(Transformers.aliasToBean(Company.class));
			query.setFirstResult((page - 1) * rowsPerPage);
			query.setMaxResults(rowsPerPage);
			
			if( !StringUtils.isNullOrEmpty(departmentName) )
				query.setParameter("department_name", '%' + departmentName + '%');
			if( status != null )
				query.setParameter("status", status);
						
			Object lst = query.list();
			if( lst != null )
				result.setDataRows((List<Company>) lst);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}*/
    public Company findDetails(Long id) {
        return this.session.createQuery("from Company where id = :id", Company.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    @SuppressWarnings("deprecation")
    public CompanytDTO login(String email, String password) {

        CompanytDTO company = null;

        try {
            @SuppressWarnings("rawtypes")
            NativeQuery query = this.session.getNamedNativeQuery("loginCompany");
            query.setParameter("email", email.trim());
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("name", StandardBasicTypes.STRING);
            query.addScalar("address", StandardBasicTypes.STRING);
            query.addScalar("email", StandardBasicTypes.STRING);
            query.addScalar("officePhone", StandardBasicTypes.STRING);
            query.addScalar("hotline", StandardBasicTypes.STRING);
            query.addScalar("website", StandardBasicTypes.STRING);
            query.addScalar("status", StandardBasicTypes.INTEGER);
            query.addScalar("_package", StandardBasicTypes.INTEGER);
            query.addScalar("packageStart", StandardBasicTypes.DATE);
            query.addScalar("packageEnd", StandardBasicTypes.DATE);
            query.addScalar("createdAt", StandardBasicTypes.TIMESTAMP);
            query.addScalar("password", StandardBasicTypes.STRING);
            query.addScalar("saltValue", StandardBasicTypes.STRING);
            query.addScalar("uuid", StandardBasicTypes.STRING);
            query.addScalar("logo", StandardBasicTypes.STRING);
            query.addScalar("careerId", StandardBasicTypes.LONG);
            query.addScalar("userCount", StandardBasicTypes.LONG);
            query.setResultTransformer(Transformers.aliasToBean(CompanytDTO.class));

            company = (CompanytDTO) query.uniqueResult();

            if (company != null && SecurityUtil.isMatched(password.trim(), company.getPassword(), company.getSaltValue(), 20)) {
                return company;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    public List<CompanyLessDTO> loadFromLetter(Long userTo) {
        List<CompanyLessDTO> result = null;
        try {
            @SuppressWarnings("rawtypes")
            NativeQuery query = this.session.getNamedNativeQuery("loadFromLetter");
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("name", StandardBasicTypes.STRING);
//            query.addScalar("address", StandardBasicTypes.STRING);
//            query.addScalar("email", StandardBasicTypes.STRING);
            query.addScalar("officePhone", StandardBasicTypes.STRING);
            query.addScalar("hotline", StandardBasicTypes.STRING);
//            query.addScalar("website", StandardBasicTypes.STRING);
//            query.addScalar("status", StandardBasicTypes.INTEGER);
//            query.addScalar("_package", StandardBasicTypes.INTEGER);
//            query.addScalar("packageStart", StandardBasicTypes.DATE);
//            query.addScalar("packageEnd", StandardBasicTypes.DATE);
//            query.addScalar("createdAt", StandardBasicTypes.TIMESTAMP);
//            query.addScalar("password", StandardBasicTypes.STRING);
//            query.addScalar("saltValue", StandardBasicTypes.STRING);
//            query.addScalar("uuid", StandardBasicTypes.STRING);
//            query.addScalar("logo", StandardBasicTypes.STRING);
//            query.addScalar("careerId", StandardBasicTypes.LONG);

            query.setParameter("userTo", userTo);
            query.setResultTransformer(Transformers.aliasToBean(CompanyLessDTO.class));

            result = (List<CompanyLessDTO>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void upsert(Company item) {
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    public boolean update(Company item) {
        String sql = "UPDATE company SET name = :name, address = :address, office_phone = :office_phone, "
                + " hotline = :hotline, website = :website, logo = :logo WHERE id = :id";
        NativeQuery query = this.session.createNativeQuery(sql)
                .setParameter("name", item.getName())
                .setParameter("address", item.getAddress())
                .setParameter("office_phone", item.getOffice_phone())
                .setParameter("hotline", item.getHotline())
                .setParameter("website", item.getWebsite())
                .setParameter("logo", item.getLogo())
                .setParameter("id", item.getId());
        return query.executeUpdate() >= 1;
    }
}
