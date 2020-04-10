package com.elcom.data.interview;

import org.hibernate.Session;

import com.elcom.data.BaseUnitOfWork;
import com.elcom.data.HibernateBase;
import com.elcom.data.interview.repository.CommonRepository;
import com.elcom.data.interview.repository.CompanyRepository;
import com.elcom.data.interview.repository.JobRepository;
import com.elcom.data.interview.repository.LetterRepository;
import com.elcom.data.interview.repository.ProceduresRepository;
import com.elcom.data.interview.repository.QuestionRepository;
import com.elcom.data.interview.repository.RatingRepository;
import com.elcom.data.interview.repository.ResultCriteriaRepository;
import com.elcom.data.interview.repository.ResultTestRepository;
import com.elcom.data.repository.IRepository;
import com.elcom.data.user.repository.UsersRepository;

public class UnitOfWorkInterview extends BaseUnitOfWork {

    public UnitOfWorkInterview(HibernateBase hibernateBase, Session session) {
        super(hibernateBase, session);
    }

    public UnitOfWorkInterview() {
        super();
    }

    @SuppressWarnings("rawtypes")
    private IRepository _epUserRepository = null;

    @SuppressWarnings("rawtypes")
    private IRepository _companyRepository = null;

    @SuppressWarnings("rawtypes")
    private IRepository _commonRepository = null;

    @SuppressWarnings("rawtypes")
    private IRepository _jobRepository = null;

    @SuppressWarnings("rawtypes")
    private IRepository _questionRepository = null;

    @SuppressWarnings("rawtypes")
    private IRepository _proceduresRepository = null;

    @SuppressWarnings("rawtypes")
    private IRepository _letterRepository = null;

    @SuppressWarnings("rawtypes")
    private IRepository _ratingRepository = null;

    @SuppressWarnings("rawtypes")
    private IRepository _resultCriteriaRepository = null;

    @SuppressWarnings("rawtypes")
    private IRepository _resultTestRepository = null;

    public QuestionRepository questionRepository() {

        if (_questionRepository == null) {
            _questionRepository = new QuestionRepository(this.session);
        }

        return (QuestionRepository) _questionRepository;
    }

    public UsersRepository epUserRepository() {

        if (_epUserRepository == null) {
            _epUserRepository = new UsersRepository(this.session);
        }

        return (UsersRepository) _epUserRepository;
    }

    public CompanyRepository companyRepository() {

        if (_companyRepository == null) {
            _companyRepository = new CompanyRepository(this.session);
        }

        return (CompanyRepository) _companyRepository;
    }

    public CommonRepository commonRepository() {

        if (_commonRepository == null) {
            _commonRepository = new CommonRepository(this.session);
        }

        return (CommonRepository) _commonRepository;
    }

    public JobRepository jobRepository() {

        if (_jobRepository == null) {
            _jobRepository = new JobRepository(this.session);
        }

        return (JobRepository) _jobRepository;
    }

    public ProceduresRepository proceduresRepository() {

        if (_proceduresRepository == null) {
            _proceduresRepository = new ProceduresRepository(this.session);
        }

        return (ProceduresRepository) _proceduresRepository;
    }

    public LetterRepository letterRepository() {

        if (_letterRepository == null) {
            _letterRepository = new LetterRepository(this.session);
        }

        return (LetterRepository) _letterRepository;
    }

    public RatingRepository ratingRepository() {

        if (_ratingRepository == null) {
            _ratingRepository = new RatingRepository(this.session);
        }

        return (RatingRepository) _ratingRepository;
    }

    public ResultCriteriaRepository resultCriteriaRepository() {

        if (_resultCriteriaRepository == null) {
            _resultCriteriaRepository = new ResultCriteriaRepository(this.session);
        }

        return (ResultCriteriaRepository) _resultCriteriaRepository;
    }

    public ResultTestRepository resultTestRepository() {

        if (_resultTestRepository == null) {
            _resultTestRepository = new ResultTestRepository(this.session);
        }

        return (ResultTestRepository) _resultTestRepository;
    }

    @Override
    public void reset() {

        super.reset();

        _proceduresRepository = null;
        _proceduresRepository = new ProceduresRepository(this.session);

        _questionRepository = null;
        _questionRepository = new QuestionRepository(this.session);

        _jobRepository = null;
        _jobRepository = new JobRepository(this.session);

        _epUserRepository = null;
        _epUserRepository = new UsersRepository(this.session);

        _companyRepository = null;
        _companyRepository = new CompanyRepository(this.session);

        _commonRepository = null;
        _commonRepository = new CommonRepository(this.session);

        _letterRepository = null;
        _letterRepository = new LetterRepository(this.session);

        _ratingRepository = null;
        _ratingRepository = new RatingRepository(this.session);

        _resultCriteriaRepository = null;
        _resultCriteriaRepository = new ResultCriteriaRepository(this.session);

        _resultTestRepository = null;
        _resultTestRepository = new ResultTestRepository(this.session);
    }
}
